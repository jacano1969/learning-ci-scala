#!/usr/bin/env python

from math import sqrt

# Euclidean distance
euclidean_distance = sqrt(pow(5-4,2)+pow(4-1,2))
print euclidean_distance

# avoid zero divide
inversed_ed = 1/(1+sqrt(pow(5-4,2)+pow(4-1,2)))
print inversed_ed

import recommendations

sim_distance_result = recommendations.sim_distance(
  recommendations.critics, 
  'Lisa Rose', 
  'Gene Seymour'
)
print sim_distance_result

