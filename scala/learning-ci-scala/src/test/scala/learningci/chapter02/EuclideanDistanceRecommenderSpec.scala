package learningci.chapter02

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class EuclideanDistanceRecommenderSpec extends FlatSpec with ShouldMatchers {

  "EuclideanDistanceRecommender#getSimilarPersons" should "return expected values for Toby" in {
    val recommender = new EuclideanDistanceRecommender
    val similarPersons: List[Tuple2[Person, Double]] = recommender.getSimilarPersons(SampleData.critics, new Person("Toby"), 3)
    similarPersons.foreach({
      case (person, value) => {
        println(person.name + " => " + value)
      }
    })
    similarPersons.size should equal(3)
    similarPersons(0)._1.name should equal("Mick LaSalle")
    similarPersons(1)._1.name should equal("Michael Phillips")
    similarPersons(2)._1.name should equal("Claudia Puig")
    similarPersons(0)._2 should equal(0.3076923076923077D)
    similarPersons(1)._2 should equal(0.2857142857142857D)
    similarPersons(2)._2 should equal(0.23529411764705882D)
  }

  "EuclideanDistanceRecommender#getRecommendations" should "return expected values for Toby" in {
    val recommender = new EuclideanDistanceRecommender
    val recommendations: List[Tuple2[String, Double]] = recommender.getRecommendations(SampleData.critics, new Person("Toby"))
    recommendations.foreach({
      case (name, value) => {
        println(name + " => " + value)
      }
    })
    recommendations.size should equal(3)
    recommendations(0)._1 should equal("The Night Listener")
    recommendations(1)._1 should equal("Lady in the Water")
    recommendations(2)._1 should equal("Just My Luck")
    recommendations(0)._2 should equal(3.3883502969520225D)
    recommendations(1)._2 should equal(2.7561242939959363D)
    recommendations(2)._2 should equal(2.461988486074374D)
  }

}