package learningci.chapter06.datastore

import collection.mutable.HashMap
import learningci.chapter06._

class InMemoryDatastore extends Datastore {

  protected val tagCountForWordsMap = new HashMap[Word, HashMap[JudgeTag, Int]]

  protected val tagCountMap = new HashMap[JudgeTag, Int]

  override def addToTagCountForWords(word: Word,
                                     tag: JudgeTag): Unit = {
    val eachTagCountMap = tagCountForWordsMap.get(word) match {
      case Some(alreadyExists) => alreadyExists
      case _ => new HashMap[JudgeTag, Int]
    }
    eachTagCountMap.update(tag, eachTagCountMap.getOrElse(tag, 0) + 1)
    tagCountForWordsMap.update(word, eachTagCountMap)
  }

  override def addToTagCount(tag: JudgeTag): Unit = {
    tagCountMap.update(tag, tagCountMap.getOrElse(tag, 0) + 1)
  }

  override def getWordCountPerTag(word: Word,
                                  tag: JudgeTag): Double = {
    if (tagCountForWordsMap.contains(word) && tagCountMap.contains(tag)) {
      tagCountForWordsMap.get(word) match {
        case Some(tagCountForTheWord) => tagCountForTheWord.get(tag) match {
          case Some(count) => count.toDouble
          case _ => 0.0D
        }
        case _ => 0.0D
      }
    } else 0.0D
  }

  override def getCountPerTag(tag: JudgeTag): Double = {
    if (tagCountMap.contains(tag)) {
      tagCountMap.get(tag) match {
        case Some(value) => value.toDouble
        case _ => 0.0D
      }
    } else 0.0D
  }

  override def getSumOfTagCounts(): Double = {
    tagCountMap.values.sum
  }

  override def getAllTags(): List[JudgeTag] = {
    tagCountMap.keys.toList
  }

}
