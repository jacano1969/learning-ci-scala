package learningci.chapter06

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class NaiveBayesClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.5.1 : get basic probability " should "return 0.0625" in {
    val classifier = new NaiveBayesClassifier
    classifier.getBasicProbabilityForDocument(Document("Nobody owns the water"), Tag.Good) should equal(0.0625D)
  }

  "Chapter 6.5.2 : After training all, get tag probability " should "return 0.15624, 0.05" in {
    val classifier = new NaiveBayesClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getTagProbabilityForDocument(Document("quick rabbit"), Tag.Good) should equal(0.15624999999999997D)
    classifier.getTagProbabilityForDocument(Document("quick rabbit"), Tag.Bad) should equal(0.05D)
  }

  "Chapter 6.5.3 : After training all, get classified tag " should "return expected values" in {
    val classifier = new NaiveBayesClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick rabbit"), Tag.Unknown) should equal(Tag.Good)
    classifier.getClassifiedTag(Document("quick money"), Tag.Unknown) should equal(Tag.Bad)

    classifier.setThreshold(Tag.Bad, 3.0D)
    classifier.getClassifiedTag(Document("quick money"), Tag.Unknown) should equal(Tag.Unknown)

    for (i <- 1 to 10) {
      Documents.all foreach {
        case (document, tag) => classifier.train(document, tag)
      }
    }
    classifier.getClassifiedTag(Document("quick money"), Tag.Unknown) should equal(Tag.Bad)
  }

}