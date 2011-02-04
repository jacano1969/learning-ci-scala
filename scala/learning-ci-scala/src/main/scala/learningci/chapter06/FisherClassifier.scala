package learningci.chapter06

import collection.mutable.HashMap

class FisherClassifier extends BasicClassifier {

  private var tagMimimumValueMap = new HashMap[Tag, Double]

  def getMinimumValue(tag: Tag): Double = {
    tagMimimumValueMap.getOrElse(tag, 0.0D)
  }

  def setMinimumValue(tag: Tag, minimumValue: Double): Unit = {
    tagMimimumValueMap.update(tag, minimumValue)
  }

  override def getTagProbabilityForWord(word: Word, tag: Tag): Double = {
    val wordProb = super.getTagProbabilityForWord(word, tag)
    wordProb match {
      case 0.0D => 0.0D
      case _ => {
        val wordProbList = getAllTags map {
          eachTag => super.getTagProbabilityForWord(word, eachTag)
        }
        val frequencySum = wordProbList.sum
        wordProb / frequencySum
      }
    }
  }

  def getFisherProbabily(document: Document, tag: Tag): Double = {
    var prob = 1.0D
    val words = getDistinctWords(document)
    words foreach {
      word => prob *= getWeightedWordProbability(word, tag)
    }
    val wordScore = -2 * math.log(prob)
    getChiSquareDistribution(wordScore, words.length * 2)
  }

  def getChiSquareDistribution(chi: Double, df: Int): Double = {
    val halfOfChi = chi / 2.0
    var term = math.exp(-halfOfChi)
    var sumValue = term
    for (i <- 1 to math.floor(df / 2).toInt - 1) {
      term *= halfOfChi / i
      sumValue += term
    }
    math.min(sumValue, 1.0).toDouble
  }

  override def getClassifiedTag(document: Document,
                                default: Tag = Tag.Unknown): Tag = {
    var bestTag = default
    var maxValue = 0.0D
    getAllTags foreach {
      eachTag => {
        val prob = getFisherProbabily(document, eachTag)
        if (prob > getMinimumValue(eachTag) && prob > maxValue) {
          bestTag = eachTag
          maxValue = prob
        }
      }
    }
    bestTag
  }

}