#!/usr/bin/env python

import docclass

classifier = docclass.naive_bayes_classifier()
print classifier.get_doc_prb('Nobody owns the water', 'good')

