package learningci.chapter06

object Documents {
  def all(): Map[Document, Tag] = {
    Map(
      Document("Nobody owns the water") -> Tag.Good
      , Document("the quick rabbit jumps fences") -> Tag.Good
      , Document("buy pharmaceuticals now") -> Tag.Bad
      , Document("make quick money at the online casino") -> Tag.Bad
      , Document("the quick brown fox jumps") -> Tag.Good
    )
  }
}

case class Document(val value: String)

case class Word(val value: String)

case class Tag(val value: String)

object Tag {
  val Good = Tag("good")
  val Bad = Tag("bad")
  val Unknown = Tag("unknown")
}