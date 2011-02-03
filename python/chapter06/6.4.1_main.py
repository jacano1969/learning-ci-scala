#!/usr/bin/python

import docclass

classifier = docclass.classifier()
classifier.train_sample_documents()
print classifier.get_prb('money', 'good')
print classifier.get_weighted_prb('money', 'good')
classifier.train_sample_documents()
print classifier.get_weighted_prb('money', 'good')

