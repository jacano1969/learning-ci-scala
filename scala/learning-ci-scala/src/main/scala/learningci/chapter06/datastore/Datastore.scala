package learningci.chapter06.datastore

import learningci.chapter06._

trait Datastore {

  def addToTagCountForWords(word: Word, tag: JudgeTag): Unit

  def addToTagCount(tag: JudgeTag): Unit

  def getWordCountPerTag(word: Word, tag: JudgeTag): Double

  def getCountPerTag(tag: JudgeTag): Double

  def getSumOfTagCounts(): Double

  def getAllTags(): List[JudgeTag]

}