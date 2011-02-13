package learningci.chapter06

import learningci.chapter06.datastore._

trait Classifier {

  def getDistinctWords(document: Document): List[Word]

  def train(document: Document, tag: JudgeTag): Unit

  def getWeightedWordProbability(word: Word,
                                 tag: JudgeTag,
                                 weight: Double = 1.0D,
                                 assumedProbability: Double = 0.5D): Double

  def getTagProbabilityForWord(word: Word, tag: JudgeTag): Double

  def getTagProbabilityForDocument(document: Document, tag: JudgeTag): Double

  def getClassifiedTag(document: Document, default: JudgeTag): JudgeTag

}

