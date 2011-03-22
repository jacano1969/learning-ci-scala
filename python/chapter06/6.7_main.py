#!/usr/bin/env python

import docclass_with_sqlite

fisher = docclass_with_sqlite.fisher_classifier()
fisher.set_db('test1.db')
fisher.train_sample_documents()
#fisher.print_db()

bayes = docclass_with_sqlite.naive_bayes_classifier()
bayes.set_db('test1.db')
print bayes.classify('quick money')

