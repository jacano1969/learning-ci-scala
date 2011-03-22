#!/usr/bin/env python

import docclass

classifier = docclass.naive_bayes_classifier()
classifier.train_sample_documents()
print classifier.get_tag_prb('quick rabbit', 'good')
print classifier.get_tag_prb('quick rabbit', 'bad')

