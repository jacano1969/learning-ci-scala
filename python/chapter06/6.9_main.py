#!/usr/bin/python

import docclass_with_sqlite
import feedfilter

fisher = docclass_with_sqlite.fisher_classifier(
           get_distinct_words_func = feedfilter.get_distinct_words
         )
fisher.set_db('python_feed.db')
feedfilter.read_and_train_entries2('python_search.xml',fisher)

