#!/usr/bin/python

import feedparser
import re

def read_and_train_entries(feed, classifier):
  parsed_feed = feedparser.parse(feed)
  for entry in parsed_feed['entries']:
    print 
    print '-----'
    print 'Title: ' + entry['title'].encode('utf-8')
    print 'Publisher: ' + entry['publisher'].encode('utf-8')
    print 
    print entry['summary'].encode('utf-8')
    full_text = '%s\n%s\n%s' % (entry['title'], entry['publisher'], entry['summary'])
    print 'Guess: ' + classifier.classify(full_text)
    tag = raw_input('Enter tag: ')
    classifier.train(full_text, tag)

def read_and_train_entries2(feed, classifier):
  parsed_feed = feedparser.parse(feed)
  for entry in parsed_feed['entries']:
    print
    print '-----'
    print 'Title: ' + entry['title'].encode('utf-8')
    print 'Publisher: ' + entry['publisher'].encode('utf-8')
    print
    print entry['summary'].encode('utf-8')
    print 'Guess: ' + classifier.classify(entry)
    tag = raw_input('Enter tag: ')
    classifier.train(entry, tag)

def get_distinct_words(entry):
  splitter = re.compile('\\W*')
  word_count_map = {}
  title_words = [s.lower() for s in splitter.split(entry['title'])]
  for word in title_words: 
    word_count_map['Title:'+word] = 1
  summary_words = [
    s.lower() 
      for s in splitter.split(entry['summary']) 
        if len(s) > 2 and len(s) < 20
  ]
  upper_count = 0
  for i in range(len(summary_words)):
    word = summary_words[i]
    word_count_map[word] = 1
    if word.isupper(): 
      upper_count += 1
  if i < len(summary_words)-1:
    two_words = ' '.join(summary_words[i:i+1])
    word_count_map[two_words] = 1
  word_count_map['Publisher:'+entry['publisher']] = 1
  if float(upper_count)/len(summary_words)>0.3:
    word_count_map['UPPERCASE'] = 1
  return word_count_map

