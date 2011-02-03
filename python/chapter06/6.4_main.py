#!/usr/bin/python

import docclass

classifier = docclass.classifier()
classifier.train_sample_documents()

print classifier.get_prb('quick', 'good')

