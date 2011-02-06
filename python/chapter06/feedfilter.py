#!/usr/bin/python

import feedparser
import re

def read(feed, classifier):
  parsed_feed = feedparser.parse(feed)
  for entry in parsed_feed['entries']:
    print 
    print '-----'
    print 'Title: ' + entry['title'].encode('utf-8')
    print 'Publisher: ' + entry['publisher'].encode('utf-8')
    print 
    print entry['summary'].encode('utf-8')
    full_text = '%s\n%s\n%s' % (entry['title'], entry['publisher'], entry['summary'])
    print 'Guess: ' + str(classifier.classify(full_text))
    tag = raw_input('Enter category: ')
    classifier.train(full_text, tag)


