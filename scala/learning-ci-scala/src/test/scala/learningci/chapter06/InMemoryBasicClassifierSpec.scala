package learningci.chapter06

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class InMemoryBasicClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.3 : after training 2 documents, 'quick' in 'good' " should "return 1.0" in {
    val classifier = new BasicClassifier
    classifier.train(Document("the quick brown fox jumps over the lazy dog"), Tag.Good)
    classifier.train(Document("make quick money in the online casino"), Tag.Bad)
    val result = classifier.getDatastore.getWordCountPerTag(Word("quick"), Tag.Good)
    result should equal(1.0D)
  }

  "Chapter 6.3 : after training 2 documents, 'quick' in 'bad' " should "return 1.0" in {
    val classifier = new BasicClassifier
    classifier.train(Document("the quick brown fox jumps over the lazy dog"), Tag.Good)
    classifier.train(Document("make quick money in the online casino"), Tag.Bad)
    val result = classifier.getDatastore.getWordCountPerTag(Word("quick"), Tag.Bad)
    result should equal(1.0D)
  }

  "Chapter 6.4 : get basic word probability for 'quick' in 'good' " should "return 0.666..." in {
    val classifier = new BasicClassifier
    Documents.all foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val result = classifier.getTagProbabilityForWord(Word("quick"), Tag.Good)
    result should equal(0.6666666666666666D)
  }

  "Chapter 6.4.1 : get weighted word probability for 'money' in 'good' " should "return 0.25, 0.166..." in {
    val classifier = new BasicClassifier
    Documents.all foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val first = classifier.getWeightedWordProbability(Word("money"), Tag.Good)
    first should equal(0.25D)
    Documents.all foreach {
      case (document, tag) => {
        classifier.train(document, tag)
      }
    }
    val second = classifier.getWeightedWordProbability(Word("money"), Tag.Good)
    second should equal(0.16666666666666666D)
  }

}