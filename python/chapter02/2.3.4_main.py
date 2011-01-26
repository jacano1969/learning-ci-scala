#!/usr/bin/python

import recommendations

result = recommendations.get_similar_persons(
  recommendations.critics, 
  'Toby', 
  n = 3
)
print result

