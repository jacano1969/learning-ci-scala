package learningci.chapter06

import learningci.chapter06.datastore._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SqliteFisherClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.6.2 : get fisher probability " should "return expected values" in {
    DatabaseTool.initialize()
    val classifier = new FisherClassifier(new SqliteDatastore)
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getTagProbabilityForWord(Word("quick"), JudgeTag.Good) should equal(0.5714285714285715D)
    classifier.getFisherProbability(Document("quick rabbit"), JudgeTag.Good) should equal(0.78013986588958D)
    classifier.getFisherProbability(Document("quick rabbit"), JudgeTag.Bad) should equal(0.3563359628333526D)
  }

  "Chapter 6.6.3 : get classified tag " should "return good,bad,good,good" in {
    DatabaseTool.initialize()
    val classifier = new FisherClassifier(new SqliteDatastore)
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick rabbit")) should equal(JudgeTag.Good)
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Bad)
    classifier.setMinimumValue(JudgeTag.Bad, 0.8D)
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Good)
    classifier.setMinimumValue(JudgeTag.Good, 0.4D)
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Good)
  }

}