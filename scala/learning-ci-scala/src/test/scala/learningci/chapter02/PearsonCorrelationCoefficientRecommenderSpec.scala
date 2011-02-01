package learningci.chapter02

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class PearsonCorrelationCoefficientRecommenderSpec extends FlatSpec with ShouldMatchers {

  "PearsonCorrelationCoefficientRecommender#getSimilarPersons" should "return expected values for Toby" in {
    val recommender = new PearsonCorrelationCoefficientRecommendar
    val similarPersons: List[Tuple2[Person, Double]] = recommender.getSimilarPersons(Critics.all, Person.toby, 3)
    similarPersons foreach {
      case (Person(name), value) => {
        println(name + " => " + value)
      }
    }
    similarPersons.size should equal(3)
    similarPersons(0)._1.name should equal("Mick LaSalle")
    similarPersons(1)._1.name should equal("Claudia Puig")
    similarPersons(2)._1.name should equal("Lisa Rose")
    similarPersons(0)._2 should equal(0.1154752909313282D)
    similarPersons(1)._2 should equal(0.07594208041337897D)
    similarPersons(2)._2 should equal(0.06682766880808848D)
  }

  "PearsonCorrelationCoefficientRecommender#getRecommendations" should "return expected values for Toby" in {
    val recommender = new PearsonCorrelationCoefficientRecommendar
    val recommendations: List[Tuple2[Movie, Double]] = recommender.getRecommendations(Critics.all, Person.toby)
    recommendations foreach {
      case (Movie(title), value) => {
        println(title + " => " + value)
      }
    }
    recommendations.size should equal(3)
    recommendations(0)._1.title should equal("The Night Listener")
    recommendations(1)._1.title should equal("Lady in the Water")
    recommendations(2)._1.title should equal("Just My Luck")
    recommendations(0)._2 should equal(3.338175971394646D)
    recommendations(1)._2 should equal(2.8798748976946D)
    recommendations(2)._2 should equal(2.4413324446514006D)
  }

}