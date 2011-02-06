#!/usr/bin/python

import sys
import akismet_client

argvs = sys.argv
if len(argvs) <= 1:
  print "Usage: api_key required"
  quit()
api_key = argvs[1]
message = "Make money fast! Online Casino!"
result = akismet_client.is_spam(message, 'spammer@spam.com', '127.0.0.1', api_key = api_key)
print "Result: " + str(result)

