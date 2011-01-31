package learningci.chapter02

import collection.mutable.HashSet

class EuclideanDistanceRecommender extends Recommender {

  override def getSimilarity(critics: Map[String, Map[String, Double]],
                             p1: Person,
                             p2: Person): Double = {
    var sumOfValues: Double = 0.0D
    critics.get(p1.name) match {
      case Some(critics1) => {
        critics1 foreach {
          case (title, rating1) => {
            critics.get(p2.name) match {
              case Some(critics2) => {
                critics2.get(title) match {
                  case Some(rating2) => {
                    sumOfValues += math.pow(rating1 - rating2, 2)
                  }
                  case None =>
                }
              }
              case None =>
            }
          }
        }
      }
      case None =>
    }
    1 / (1 + sumOfValues)
  }

}