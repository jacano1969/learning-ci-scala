#!/usr/bin/python

import docclass

classifier = docclass.fisher_classifier()
classifier.train_sample_documents()

print classifier.classify('quick rabbit')
print classifier.classify('quick money')

classifier.set_minimum('bad', 0.8)
print classifier.classify('quick money')

classifier.set_minimum('good', 0.4)
print classifier.classify('quick money')

