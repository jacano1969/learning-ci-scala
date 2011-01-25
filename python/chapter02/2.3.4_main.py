#!/usr/bin/python

import recommendations

top_matches_result = recommendations.top_matches(
  recommendations.critics, 
  'Toby', 
  n = 3
)
print top_matches_result

