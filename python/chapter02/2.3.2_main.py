#!/usr/bin/env python

import recommendations

sim_pearson_result = recommendations.sim_pearson(
  recommendations.critics, 
  'Lisa Rose', 
  'Gene Seymour'
)
print sim_pearson_result

