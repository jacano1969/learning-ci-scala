package learningci.chapter02

class PearsonCorrelationCoefficientRecommendar extends Recommender {

  override def getSimilarity(critics: Map[String, Map[String, Double]],
                             p1: Person,
                             p2: Person): Double = {
    var bothContained: Int = 0
    var sumOfCriticsByP1: Double = 0.0D
    var sumOfCriticsByP2: Double = 0.0D
    var sumOfPowedCriticsByP1: Double = 0.0D
    var sumOfPowedCriticsByP2: Double = 0.0D
    var sumOfProducts: Double = 0.0D
    critics.get(p1.name) match {
      case Some(critics1) => {
        critics1 foreach {
          case (title1, rating1) => {
            critics.get(p2.name) match {
              case Some(critics2) => {
                critics2 foreach {
                  case (title2, rating2) => {
                    if (title1 == title2) {
                      bothContained += 1
                      sumOfCriticsByP1 += rating1
                      sumOfCriticsByP2 += rating2
                      sumOfPowedCriticsByP1 += math.pow(rating1, 2)
                      sumOfPowedCriticsByP2 += math.pow(rating2, 2)
                      sumOfProducts += rating1 * rating2
                    }
                  }
                }
              }
              case None =>
            }
          }
        }
      }
      case None =>
    }
    val numerator: Double = sumOfProducts - (sumOfCriticsByP1 * sumOfCriticsByP2 / bothContained)
    val denominator: Double = math.sqrt(
      (sumOfPowedCriticsByP1 - math.pow(sumOfCriticsByP1, 2))
        * (sumOfPowedCriticsByP2 - math.pow(sumOfCriticsByP2, 2))
        / bothContained
    )
    denominator match {
      case 0.0D => {
        0.0D
      }
      case _ => {
        numerator / denominator
      }
    }
  }

}