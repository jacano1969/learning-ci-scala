package learningci.chapter06

class BasicClassifier extends Classifier {

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

}