package learningci.chapter06

import collection.mutable.HashMap
import datastore._

class NaiveBayesClassifier(override val datastore: Datastore) extends AbstractClassifier(datastore) {

  def this() = {
    this (new InMemoryDatastore)
  }

  private val tagThresholdMap = new HashMap[Tag, Double]

  private val ThresholdDefaultValue = 1.0D

  def getThreshold(tag: Tag): Double = {
    tagThresholdMap.getOrElse(tag, ThresholdDefaultValue)
  }

  def setThreshold(tag: Tag, threshold: Double): Unit = {
    tagThresholdMap.update(tag, threshold)
  }

  def getBasicProbabilityForDocument(document: Document, tag: Tag): Double = {
    val words = getDistinctWords(document)
    var probability = 1.0D
    words foreach {
      word => probability *= getWeightedWordProbability(word, tag)
    }
    probability
  }

  override def getTagProbabilityForDocument(document: Document, tag: Tag): Double = {
    val basicProb = getBasicProbabilityForDocument(document, tag)
    (datastore.getCountPerTag(tag) / datastore.getSumOfTagCounts) * basicProb
  }

  override def getClassifiedTag(document: Document,
                                default: Tag = Tag.Unknown): Tag = {
    val tagProbMap = new HashMap[Tag, Double]
    var maxValue = 0.0D
    var bestTag = default
    datastore.getAllTags foreach {
      tag => {
        tagProbMap.update(tag, getTagProbabilityForDocument(document, tag))
        tagProbMap.get(tag) match {
          case Some(value) if value > maxValue => {
            maxValue = value
            bestTag = tag
          }
          case _ =>
        }
      }
    }
    val bestTagValue = tagProbMap.getOrElse(bestTag, ThresholdDefaultValue)
    tagProbMap foreach {
      case (tag, _) if tag != bestTag => {
        tagProbMap.get(tag) match {
          case Some(value) => {
            if (value * getThreshold(bestTag) > bestTagValue) {
              return default
            }
          }
          case _ =>
        }
      }
      case _ =>
    }
    bestTag
  }

}
