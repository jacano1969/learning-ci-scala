#!/bin/sh

wget http://feedparser.googlecode.com/files/feedparser-5.0.zip
unzip feedparser-5.0.zip
cd feedparser-5.0
python setup.py install
cd -
rm -rf feedparser-5.0*

