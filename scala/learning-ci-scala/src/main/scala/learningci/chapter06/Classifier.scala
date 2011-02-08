package learningci.chapter06

import datastore.Datastore

trait Classifier {

  def getDistinctWords(document: Document): List[Word]

  def train(document: Document, tag: Tag): Unit

  def getWeightedWordProbability(word: Word,
                                 tag: Tag,
                                 weight: Double = 1.0D,
                                 assumedProbability: Double = 0.5D): Double

  def getTagProbabilityForWord(word: Word, tag: Tag): Double

  def getTagProbabilityForDocument(document: Document, tag: Tag): Double

  def getClassifiedTag(document: Document, default: Tag): Tag

}

abstract class AbstractClassifier extends Classifier with Datastore {

  override def getDistinctWords(document: Document): List[Word] = {
    val words = """\s+""".r.split(document.value).toList
    words filter {
      word => word.length > 2 && word.length < 20
    } map {
      word => Word(word.toLowerCase)
    }
  }

  override def getTagProbabilityForWord(word: Word, tag: Tag): Double = {
    val countPerTag = getCountPerTag(tag)
    if (countPerTag == 0) 0.0D else getWordCountPerTag(word, tag) / countPerTag
  }

  override def getTagProbabilityForDocument(document: Document, tag: Tag): Double = {
    throw new UnsupportedOperationException
  }

  override def getClassifiedTag(document: Document, default: Tag): Tag = {
    throw new UnsupportedOperationException
  }

  override def train(document: Document,
                     tag: Tag): Unit = {
    val words = getDistinctWords(document)
    words foreach {
      word => addToTagCountForWords(word, tag)
    }
    addToTagCount(tag)
  }

 override  def getWeightedWordProbability(word: Word,
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

}

