package learningci.chapter06

import collection.mutable.HashMap

class Classifier {

  private val tagCountForWordsMap = new HashMap[Word, HashMap[Tag, Int]]

  private val tagCountMap = new HashMap[Tag, Int]

  def getDistinctWords(document: Document): List[Word] = {
    val words = """\s+""".r.split(document.value).toList
    words filter {
      word => word.length > 2 && word.length < 20
    } map {
      word => Word(word.toLowerCase)
    }
  }

  def addToTagCountForWordsMap(word: Word,
                               tag: Tag): Unit = {
    val eachTagCountMap = tagCountForWordsMap.get(word) match {
      case Some(alreadyExists) => alreadyExists
      case None => new HashMap[Tag, Int]
    }
    eachTagCountMap.update(tag, eachTagCountMap.getOrElse(tag, 0) + 1)
    tagCountForWordsMap.update(word, eachTagCountMap)
  }

  def addToTagCountMap(tag: Tag): Unit = {
    tagCountMap.update(tag, tagCountMap.getOrElse(tag, 0) + 1)
  }

  def getWordCountPerTag(word: Word,
                         tag: Tag): Double = {
    if (tagCountForWordsMap.contains(word) && tagCountMap.contains(tag)) {
      tagCountForWordsMap.get(word) match {
        case Some(tagCountForTheWord) => tagCountForTheWord.get(tag) match {
          case Some(count) => count.toDouble
          case None => 0.0D
        }
        case None => 0.0D
      }
    } else 0.0D
  }

  def getCountPerTag(tag: Tag): Double = {
    if (tagCountMap.contains(tag)) {
      tagCountMap.get(tag) match {
        case Some(value) => value.toDouble
        case None => 0.0D
      }
    } else 0.0D
  }

  def getSumOfTagCounts(): Double = {
    tagCountMap.values.sum
  }

  def getAllTags(): List[Tag] = {
    tagCountMap.keys.toList
  }

  def train(document: Document,
            tag: Tag): Unit = {
    val words = getDistinctWords(document)
    words foreach {
      word => addToTagCountForWordsMap(word, tag)
    }
    addToTagCountMap(tag)
  }

  def getProbability(word: Word,
                     tag: Tag): Double = {
    val countPerTag = getCountPerTag(tag)
    if (countPerTag == 0) 0.0D else getWordCountPerTag(word, tag) / countPerTag
  }

  def getWeightedProbability(word: Word,
                             tag: Tag,
                             weight: Double = 1.0D,
                             assumedProbability: Double = 0.5D): Double = {
    val basicProbability = getProbability(word, tag)
    val sumOfWordCounts =
      getAllTags map {
        eachTag => getWordCountPerTag(word, eachTag)
      } sum;
    ((weight * assumedProbability) + (sumOfWordCounts * basicProbability)
      ) / (weight + sumOfWordCounts)
  }

}