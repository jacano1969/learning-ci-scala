#!/usr/bin/env python

import recommendations

result = recommendations.get_similar_persons(
  recommendations.critics, 
  'Toby', 
  max_count = 3
)
print result

