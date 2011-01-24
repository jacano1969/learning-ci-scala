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
  # get items judged by both of person1 and person2
  si = {}
  for item in prefs[person1]:
    if item in prefs[person2]:
      si[item] = 1
  # not found
  if len(si) == 0: 
    return 0
  # sum all square root of items
  sum_of_squares = sum(
    [ 
      pow(prefs[person1][item]-prefs[person2][item],2) 
        for item in prefs[person1] if item in prefs[person2] 
    ]
  )
  return 1/(1+sum_of_squares)

# ---
# Chapter 2.3.2 Pearson correlation coefficient
# ---
def sim_pearson(prefs,p1,p2):
  # get items judged by both
  si = {}
  for item in prefs[p1]:
    if item in prefs[p2]: 
      si[item] = 1
  n = len(si)
  # not found
  if n == 0:
    return 0
  # sum all
  sum1 = sum([prefs[p1][it] for it in si])
  sum2 = sum([prefs[p2][it] for it in si])
  # sum all square root
  sum1sq = sum([pow(prefs[p1][it],2) for it in si])
  sum2sq = sum([pow(prefs[p2][it],2) for it in si])
  # sum products
  sum_products = sum([prefs[p1][it] * prefs[p2][it] for it in si])
  num = sum_products - (sum1*sum2/n)
  den = sqrt((sum1sq-pow(sum1,2)/n)*(sum2sq-pow(sum2,2)/n))
  if den == 0:
    return 0
  return num/den


