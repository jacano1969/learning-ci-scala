package learningci.chapter06.datastore

import learningci.chapter06._

trait Datastore {

  def addToTagCountForWords(word: Word, tag: Tag): Unit

  def addToTagCount(tag: Tag): Unit

  def getWordCountPerTag(word: Word, tag: Tag): Double

  def getCountPerTag(tag: Tag): Double

  def getSumOfTagCounts(): Double

  def getAllTags(): List[Tag]

}