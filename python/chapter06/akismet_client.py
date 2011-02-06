#!/usr/bin/python

import sys
sys.path.append('../lib/')
import akismet

default_api_key = ""
page_url = "http://www.google.com/"

default_user_agent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.7) "
default_user_agent += "Gecko/20060909 Firefox/1.5.0.7"

def is_spam(comment_content, 
            author_email, 
            ip_address,
            user_agent = default_user_agent,
            api_key = default_api_key):
  try:
    is_valid = akismet.verify_key(api_key, page_url)
    if is_valid:
      return akismet.comment_check(
               api_key, 
               page_url,
               ip_address, 
               user_agent, 
               comment_content = comment_content,
               comment_auther_email = author_email,
               comment_type = "comment"
      )
    else:
      print "Invalid api key"
      return False
  except akismet.AkismetError, e:
    print e.response, e.statuscode
    return False

