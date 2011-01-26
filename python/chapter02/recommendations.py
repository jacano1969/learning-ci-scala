#!/usr/bin/python

critics = {
    'Lisa Rose'        : {'Lady in the Water':2.5, 'Snakes on a Plane':3.5, 'Just My Luck':3.0, 'Superman Returns':3.5, 'You, Me and Dupree':2.5, 'The Night Listener':3.0},
    'Gene Seymour'     : {'Lady in the Water':3.0, 'Snakes on a Plane':3.5, 'Just My Luck':1.5, 'Superman Returns':5.0, 'You, Me and Dupree':3.5, 'The Night Listener':3.0},
    'Michael Phillips' : {'Lady in the Water':2.5, 'Snakes on a Plane':3.0,                     'Superman Returns':3.5,                           'The Night Listener':3.5},
    'Claudia Puig'     : {                         'Snakes on a Plane':3.5, 'Just My Luck':3.0, 'Superman Returns':4.0, 'You, Me and Dupree':2.5, 'The Night Listener':4.5},
    'Mick LaSalle'     : {'Lady in the Water':3.0, 'Snakes on a Plane':4.0, 'Just My Luck':2.0, 'Superman Returns':3.0, 'You, Me and Dupree':2.0, 'The Night Listener':3.0},
    'Jack Matthews'    : {'Lady in the Water':3.0, 'Snakes on a Plane':4.0,                     'Superman Returns':5.0, 'You, Me and Dupree':3.5, 'The Night Listener':3.0},
    'Toby'             : {                         'Snakes on a Plane':4.5,                     'Superman Returns':4.0, 'You, Me and Dupree':1.0,                         },
}

from math import sqrt

# ---
# Chapter 2.3.1 Euclidean distance
# ---
def sim_distance(prefs,person1,person2):
  both_contains = {}
  for title in prefs[person1]:
    if title in prefs[person2]:
      both_contains[title] = 1
  if len(both_contains) == 0: 
    return 0
  sum_of_values = sum([ 
    pow(prefs[person1][title]-prefs[person2][title],2) 
      for title in prefs[person1] if title in prefs[person2] 
  ])
  return 1/(1+sum_of_values)

# ---
# Chapter 2.3.2 Pearson correlation coefficient
# ---
def sim_pearson(prefs,p1,p2):
  # get titles judged by both
  both_contains = {}
  for title in prefs[p1]:
    if title in prefs[p2]: 
      both_contains[title] = 1
  both_contains_len = len(both_contains)
  if both_contains_len == 0: 
    return 0
  sum_of_p1 = sum([prefs[p1][title] for title in both_contains])
  sum_of_p2 = sum([prefs[p2][title] for title in both_contains])
  sum_of_pow_p1 = sum([
    pow(prefs[p1][title],2) for title in both_contains
  ])
  sum_of_pow_p2 = sum([
    pow(prefs[p2][title],2) for title in both_contains
  ])
  sum_products = sum([
    prefs[p1][title]*prefs[p2][title] for title in both_contains
  ])
  numerator = sum_products - sum_of_p1*sum_of_p2/both_contains_len
  denominator = sqrt(
    (sum_of_pow_p1-pow(sum_of_p1,2))*(sum_of_pow_p2-pow(sum_of_p2,2))
    /both_contains_len
  )
  if denominator == 0: 
    return 0
  return numerator/denominator

# ---
# Chapter 2.3.4 Similarity top matches
# ---
def get_similar_persons(prefs,person,n=5,sim_func=sim_pearson):
  sim_and_person_tuples = [ 
    (sim_func(prefs,person,other),other)
      for other in prefs if other != person 
  ]
  sim_and_person_tuples.sort()
  sim_and_person_tuples.reverse()
  return sim_and_person_tuples[0:n]
 
# ---
# Chapter 2.4 Recommendation
# ---
def get_recommendations(prefs,person,sim_func=sim_pearson):
  weighted_critics = {}
  sums_of_similarity = {}
  for other in prefs:
    if other == person: 
      continue
    similarity = sim_func(prefs,person,other)
    if similarity <= 0: 
      continue
    for title in prefs[other]:
      if title not in prefs[person] or prefs[person][title] == 0:
        weighted_critics.setdefault(title,0)
        weighted_critics[title] += prefs[other][title]*similarity
        sums_of_similarity.setdefault(title,0)
        sums_of_similarity[title] += similarity
  sim_and_title_tuples = [ 
    (weighted_critic/sums_of_similarity[title],title) 
      for title,weighted_critic in weighted_critics.items() 
  ]
  sim_and_title_tuples.sort()
  sim_and_title_tuples.reverse()
  return sim_and_title_tuples

 
