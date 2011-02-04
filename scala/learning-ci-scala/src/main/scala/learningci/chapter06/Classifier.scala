package learningci.chapter06

import collection.mutable.HashMap

trait Classifier {

  protected val tagCountForWordsMap = new HashMap[Word, HashMap[Tag, Int]]

  protected val tagCountMap = new HashMap[Tag, Int]

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
      case _ => new HashMap[Tag, Int]
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
          case _ => 0.0D
        }
        case _ => 0.0D
      }
    } else 0.0D
  }

  def getCountPerTag(tag: Tag): Double = {
    if (tagCountMap.contains(tag)) {
      tagCountMap.get(tag) match {
        case Some(value) => value.toDouble
        case _ => 0.0D
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

  def getWeightedWordProbability(word: Word,
                                 tag: Tag,
                                 weight: Double = 1.0D,
                                 assumedProbability: Double = 0.5D): Double = {
    val basicProbability = getTagProbabilityForWord(word, tag)
    val sumOfWordCounts =
      getAllTags map {
        eachTag => getWordCountPerTag(word, eachTag)
      } sum;
    ((weight * assumedProbability) + (sumOfWordCounts * basicProbability)
      ) / (weight + sumOfWordCounts)
  }

  def getTagProbabilityForWord(word: Word, tag: Tag): Double

  def getTagProbabilityForDocument(document: Document, tag: Tag): Double

  def getClassifiedTag(document: Document, default: Tag): Tag

}