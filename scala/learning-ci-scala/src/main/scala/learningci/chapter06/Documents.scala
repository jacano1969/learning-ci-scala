package learningci.chapter06

object Documents {
  def all(): Map[Document, Tag] = {
    Map(
      Document("Nobody owns the water") -> JudgeTag.Good
      , Document("the quick rabbit jumps fences") -> JudgeTag.Good
      , Document("buy pharmaceuticals now") -> JudgeTag.Bad
      , Document("make quick money at the online casino") -> JudgeTag.Bad
      , Document("the quick brown fox jumps") -> JudgeTag.Good
    )
  }
}

case class Document(val value: String)

case class Word(val value: String)

case class Tag(val value: String)

object JudgeTag {
  val Good = Tag("good")
  val Bad = Tag("bad")
  val Unknown = Tag("unknown")
}

object PythonTag {
  val Snake = Tag("snake")
  val Programming = Tag("programming")
  val Monty = Tag("monty")
}
