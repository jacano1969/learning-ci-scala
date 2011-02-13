package learningci.chapter06

import learningci.chapter06.datastore._

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

