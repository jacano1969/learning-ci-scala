#!/usr/bin/python

import docclass

classifier = docclass.classifier()
classifier.load_sample_documents()

print classifier.get_prb('quick', 'good')

