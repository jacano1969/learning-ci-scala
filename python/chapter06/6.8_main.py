#!/usr/bin/python

import docclass_with_sqlite
import feedfilter

fisher = docclass_with_sqlite.fisher_classifier()
fisher.set_db('python_feed.db')
feedfilter.read_and_train_entries('python_search.xml',fisher)


