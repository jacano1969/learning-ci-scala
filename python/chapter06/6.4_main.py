#!/usr/bin/env python

import docclass

classifier = docclass.classifier()
classifier.train_sample_documents()

print classifier.get_word_prb('quick', 'good')

