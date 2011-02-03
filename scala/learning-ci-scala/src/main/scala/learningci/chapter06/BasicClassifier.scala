package learningci.chapter06

class BasicClassifier extends Classifier {

  override def getDocumentProbability(document: Document, tag: Tag): Double = {
    throw new UnsupportedOperationException
  }

  override def getTagProbability(document: Document, tag: Tag): Double = {
    throw new UnsupportedOperationException
  }

  override def getClassifiedTag(document: Document, default: Tag): Tag = {
    throw new UnsupportedOperationException
  }

}