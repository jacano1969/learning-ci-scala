#!/usr/bin/python

import docclass

classifier = docclass.classifier()
classifier.load_sample_documents()
print classifier.get_prb('money', 'good')
print classifier.get_weighted_prb('money', 'good')
classifier.load_sample_documents()
print classifier.get_weighted_prb('money', 'good')

