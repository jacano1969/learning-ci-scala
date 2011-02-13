package learningci.chapter06

object Documents {
  def all(): Map[Document, JudgeTag] = {
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

case class JudgeTag(val value: String)

object JudgeTag {
  val Good = JudgeTag("good")
  val Bad = JudgeTag("bad")
  val Unknown = JudgeTag("unknown")
}

object PythonTag {
  val Snake = JudgeTag("snake")
  val Programming = JudgeTag("programming")
  val Monty = JudgeTag("monty")
}
