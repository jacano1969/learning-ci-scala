package learningci.chapter02

class PearsonCorrelationCoefficientRecommendar extends Recommender {

  override def getSimilarity(critics: List[Critic],
                             p1: Person,
                             p2: Person): Double = {
    var bothContainedCount: Int = 0
    var sumOfRatingsByP1 = 0.0D
    var sumOfRatingsByP2 = 0.0D
    var sumOfRatingSquaresByP1 = 0.0D
    var sumOfRatingSquaresByP2 = 0.0D
    var sumOfProducts = 0.0D
    val criticsByPerson1 = critics filter {
      case Critic(p, _, _) => p == p1
    }
    val criticsByPerson2 = critics filter {
      case Critic(p, _, _) => p == p2
    }
    criticsByPerson2 foreach {
      case Critic(_, m2, r2) => {
        criticsByPerson1 foreach {
          case Critic(_, m1, r1) if m1 == m2 => {
            bothContainedCount += 1
            sumOfRatingsByP1 += r1
            sumOfRatingsByP2 += r2
            sumOfRatingSquaresByP1 += math.pow(r1, 2)
            sumOfRatingSquaresByP2 += math.pow(r2, 2)
            sumOfProducts += r1 * r2
          }
          case _ =>
        }
      }
    }
    val numerator: Double = sumOfProducts - (sumOfRatingsByP1 * sumOfRatingsByP2 / bothContainedCount)
    val denominator: Double = math.sqrt(
      (sumOfRatingSquaresByP1 - math.pow(sumOfRatingsByP1, 2))
        * (sumOfRatingSquaresByP2 - math.pow(sumOfRatingsByP2, 2))
        / bothContainedCount
    )
    denominator match {
      case 0.0D => 0.0D
      case _ => numerator / denominator
    }
  }

}