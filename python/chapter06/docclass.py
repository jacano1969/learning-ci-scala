#!/usr/bin/python

import re
import math

class classifier:

  def __init__(self, get_distinct_words_func=None, filename=None):
    # feature : python, the
    # tag : good, bad
    self.tag_count_for_words_map = {}
    self.tag_count_map  = {}
    if get_distinct_words_func:
      self.get_distinct_words = get_distinct_words_func 

  # ---
  # Chapter 6.3 Classifier training
  # ---
  # original: getwords(doc)
  # ---
  def get_distinct_words(self,doc):
    splitter = re.compile('\\W*')
    words = [
      word.lower() 
        for word in splitter.split(doc) 
          if len(word) > 2 and len(word) < 20
    ]
    return dict([(word,1) for word in words])

  # original: incf(self,f,cat)
  # ---
  def add_per_word(self, word, tag):
    self.tag_count_for_words_map.setdefault(word,{})
    self.tag_count_for_words_map[word].setdefault(tag,0)
    self.tag_count_for_words_map[word][tag] += 1

  # original: incc(self,cat)
  # ---
  def add(self, tag):
    self.tag_count_map.setdefault(tag,0)
    self.tag_count_map[tag] += 1

  # original: fcount(self,f,cat)
  # ---
  def get_word_count_per_tag(self, word, tag):
    if word in self.tag_count_for_words_map and tag in self.tag_count_for_words_map[word]:
      return float(self.tag_count_for_words_map[word][tag])
    else:
      return 0.0

  # original: catcount(self,cat)
  # ---
  def get_count_per_tag(self, tag):
    if tag in self.tag_count_map:
      return float(self.tag_count_map[tag])
    else:
      return 0.0

  # original: totalcount(self)
  # ---
  def get_sum_of_tag_count(self):
    return sum(self.tag_count_map.values())

  # original: categories(self)
  # ---
  def get_all_tags(self):
    return self.tag_count_map.keys()

  # original: train(self,item,cat)
  # ---
  def train(self, doc, tag):
    words = self.get_distinct_words(doc)
    for word in words:
      self.add_per_word(word, tag)
    self.add(tag)

  # original: sampletrain(cl)
  # ---
  def train_sample_documents(self):
    self.train('Nobody owns the water', 'good')
    self.train('the quick rabbit jumps fences', 'good')
    self.train('buy pharmaceuticals now', 'bad')
    self.train('make quick money at the online casino', 'bad')
    self.train('the quick brown fox jumps', 'good')

  # ---
  # Chapter 6.4 Probability
  # ---

  # original: fprob(self,f,cat)
  # ---
  def get_word_prb(self, word, tag):
    if self.get_count_per_tag(tag) == 0:
      return 0
    else:
      return self.get_word_count_per_tag(word, tag) / self.get_count_per_tag(tag)

  # ---
  # Chapter 6.4.1 Weighted probability
  # --- 

  # original: weightedprob(self,f,cat,prf,weight=1.0,ap=0.5)
  # ---
  def get_weighted_prb(self, word, tag, 
    get_prb_func=None, weight=1.0, assumed_prb=0.5):
    if get_prb_func != None:
      basic_prb = get_prb_func(word, tag)
    else:
      basic_prb = self.get_word_prb(word, tag)
    sum_of_word_count = sum([
      self.get_word_count_per_tag(word, tag) 
        for tag in self.get_all_tags()
    ])
    average_of_values = ((weight * assumed_prb) + (sum_of_word_count * basic_prb)) / (weight + sum_of_word_count)
    return average_of_values

# ---
# Chapter 6.5.1 Naive Bayes classifier
# ---

class naive_bayes_classifier(classifier):

  # original: docprob(self,item,cat)
  # ---
  def get_doc_prb(self, doc, tag):
    words = self.get_distinct_words(doc)
    prb = 1
    for word in words: 
      prb *= self.get_weighted_prb(word, tag)
    return prb

  # ---
  # Chapter 6.5.2 Introduction of Bayes' theore
  # ---
  # Pr(A|B) = Pr(B|A) x Pr(A)/Pr(B)
  # -> Pr(tag|document) = Pr(document|tag) x Pr(tag)/Pr(document)

  # original: prob(self,item,cat)
  # ---
  def get_tag_prb(self, doc, tag):
    tag_prb = self.get_count_per_tag(tag) / self.get_sum_of_tag_count()
    doc_prb = self.get_doc_prb(doc, tag)
    return doc_prb * tag_prb 

  # ---
  # Chapter 6.5.3 Threshold
  # ---

  def __init__(self, get_distinct_words_func=None):
    classifier.__init__(self, get_distinct_words_func)
    self.thresholds = {}

  # original: setthreshold(self,cat,t)
  # ---
  def set_threshold(self, tag, threshold):
    self.thresholds[tag] = threshold

  # original: getthreshold(self,cat)
  # ---
  def get_threshold(self, tag):
    if tag not in self.thresholds: return 1.0
    return self.thresholds[tag]

  # original: classify(self,item,default=None)
  # ---
  def classify(self, doc, default=None):
    tag_prbs = {}
    max = 0.0
    for tag in self.get_all_tags():
      tag_prbs[tag] = self.get_tag_prb(doc, tag)
      if tag_prbs[tag] > max:
        max = tag_prbs[tag]
        best_tag = tag
    for tag in tag_prbs:
      if tag == best_tag: 
        continue
      if (tag_prbs[tag] * self.get_threshold(best_tag)) > tag_prbs[best_tag]:
        return default
    return best_tag

# ---
# Chapter 6.6 Fisher classifier
# ---

class fisher_classifier(classifier):

  # ---
  # Chapter 6.6.1 
  # ---

  # original: cprob(self,f,cat)
  # ---
  def get_tag_prb(self, word, tag):
    clf = self.get_word_prb(word, tag)
    if clf == 0: return 0
    freq_sum = sum([ 
      self.get_word_prb(word,each_tag) 
        for each_tag in self.get_all_tags() 
    ])
    prb = clf / (freq_sum)
    return prb

  # ---
  # Chapter 6.6.2
  # ---

  # original: fisherprob(self,item,cat)
  # ---
  def get_fisher_prb(self, doc, tag):
    prb = 1
    words = self.get_distinct_words(doc)
    for word in words:
      prb *= (self.get_weighted_prb(word, tag, get_prb_func=self.get_tag_prb))
    word_score = -2 * math.log(prb)
    return self.get_chi_square_dist(word_score, len(words) * 2)

  # chi-square distribution
  # original: invchi2(self,chi,df)
  # ---
  def get_chi_square_dist(self, chi, df):
    m = chi / 2.0
    sum = term = math.exp(-m)
    for i in range(1, df//2):
      term *= m / i
      sum += term
    return min(sum, 1.0)

  # ---
  # Chapter 6.6.3
  # ---

  def __init__(self,get_words_func=None):
    classifier.__init__(self, get_words_func)
    self.minimums = {}

  # original: setminimum(self,cat,min)
  # ---
  def set_minimum(self, tag, min):
    self.minimums[tag] = min

  # original: getminumum(self,cat)
  # ---
  def get_minimum(self, tag):
    if tag not in self.minimums: return 0
    return self.minimums[tag]

  # original: classify(self,item,default=None)
  # ---
  def classify(self, doc, default=None):
    best_tag = default
    max = 0.0
    for tag in self.get_all_tags():
      prb = self.get_fisher_prb(doc, tag)
      if prb > self.get_minimum(tag) and prb > max:
        best_tag = tag
        max = prb
    return best_tag

 
