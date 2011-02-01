#!/usr/bin/python

import re
import math

class classifier:

  def __init__(self, get_distinct_words_func=None, filename=None):
    # feature : python, the
    # tag : good, bad
    self.tag_count_per_word = {}
    self.tag_count  = {}
    if get_distinct_words_func != None:
      self.get_distinct_words = get_distinct_words_func 

  # ---
  # Chapter 6.3 Classifier training
  # ---

  def get_distinct_words(self,doc):
    splitter = re.compile('\\W*')
    words = [
      word.lower() 
        for word in splitter.split(doc) 
          if len(word) > 2 and len(word) < 20
    ]
    return dict([(word,1) for word in words])

  def add_per_word(self, word, tag):
    self.tag_count_per_word.setdefault(word,{})
    self.tag_count_per_word[word].setdefault(tag,0)
    self.tag_count_per_word[word][tag] += 1

  def add(self, tag):
    self.tag_count.setdefault(tag,0)
    self.tag_count[tag] += 1

  def get_word_count_per_tag(self, word, tag):
    if word in self.tag_count_per_word and tag in self.tag_count_per_word[word]:
      return float(self.tag_count_per_word[word][tag])
    else:
      return 0.0

  def get_count_per_tag(self, tag):
    if tag in self.tag_count:
      return float(self.tag_count[tag])
    else:
      return 0.0

  def get_sum_of_tag_count(self):
    return sum(self.tag_count.values())

  def get_all_tags(self):
    return self.tag_count.keys()

  def train(self, doc, tag):
    words = self.get_distinct_words(doc)
    for word in words:
      self.add_per_word(word, tag)
    self.add(tag)

  def load_sample_documents(self):
    self.train('Nobody owns the water', 'good')
    self.train('the quick rabbit jumps fences', 'good')
    self.train('buy pharmaceuticals now', 'bad')
    self.train('make quick money at the online casino', 'bad')
    self.train('the quick brown fox jumps', 'good')

  # ---
  # Chapter 6.4 Probability
  # ---

  def get_prb(self, word, tag):
    if self.get_count_per_tag(tag) == 0:
      return 0
    else:
      return self.get_word_count_per_tag(word, tag) / self.get_count_per_tag(tag)

  # ---
  # Chapter 6.4.1 Weighted probability
  # --- 

  def get_weighted_prb(self, word, tag, 
    get_prb_func=None, weight=1.0, assumed_prb=0.5):
    if get_prb_func != None:
      basic_prb = get_prb_func(word, tag)
    else:
      basic_prb = self.get_prb(word, tag)
    sum_of_word_count = sum([
      self.get_word_count_per_tag(word, tag) 
        for tag in self.get_all_tags()
    ])
    average_of_values = ((weight * assumed_prb) + (sum_of_word_count * basic_prb)) / (weight + sum_of_word_count)
    return average_of_values

