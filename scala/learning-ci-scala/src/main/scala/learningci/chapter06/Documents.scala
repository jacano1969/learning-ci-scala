package learningci.chapter06

object Documents {
  def getSampleTrainingData(): Map[Document, Tag] = {
    Map(
      Document("Nobody owns the water") -> Tag("good")
      , Document("the quick rabbit jumps fences") -> Tag("good")
      , Document("buy pharmaceuticals now") -> Tag("bad")
      , Document("make quick money at the online casino") -> Tag("bad")
      , Document("the quick brown fox jumps") -> Tag("good")
    )
  }
}

case class Document(val value: String)

case class Word(val value: String)

case class Tag(val value: String)
