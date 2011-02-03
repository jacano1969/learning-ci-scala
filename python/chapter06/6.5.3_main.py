#!/usr/bin/python

import docclass

classifier = docclass.naive_bayes_classifier()
classifier.train_sample_documents()

print classifier.classify('quick rabbit', default='unknown')
print classifier.classify('quick money', default='unknown')

classifier.set_threshold('bad', 3.0)
print classifier.classify('quick money', default='unknown')

for i in range(10):
  classifier.train_sample_documents()
print classifier.classify('quick money', default='unknown')

