#!/usr/bin/python

import recommendations

pearson_result = recommendations.get_recommendations(
  recommendations.critics, 
  'Toby'
)
print "sim_pearson :", pearson_result

distance_result = recommendations.get_recommendations(
  recommendations.critics, 
  'Toby',
  sim_func = recommendations.sim_distance
)
print "sim_distance:", distance_result

