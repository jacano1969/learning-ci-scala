#!/usr/bin/env python

import docclass

classifier = docclass.classifier()

classifier.train('the quick brown fox jumps over the lazy dog', 'good')
classifier.train('make quick money in the online casino', 'bad')

print classifier.get_word_count_per_tag('quick', 'good')
print classifier.get_word_count_per_tag('quick', 'bad')

