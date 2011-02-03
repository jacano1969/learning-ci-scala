#!/usr/bin/python

import docclass

classifier = docclass.fisher_classifier()
classifier.train_sample_documents()
print classifier.get_tag_prb('quick', 'good')
print classifier.get_fisher_prb('quick rabbit', 'good')
print classifier.get_fisher_prb('quick rabbit', 'bad')

