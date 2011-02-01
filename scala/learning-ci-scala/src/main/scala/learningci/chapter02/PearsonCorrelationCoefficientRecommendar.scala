package learningci.chapter02

class PearsonCorrelationCoefficientRecommendar extends Recommender {

  override def getSimilarity(critics: List[Critic],
                             p1: Person,
                             p2: Person): Double = {
    var bothContainedCount: Int = 0
    var sumOfCriticsByP1 = 0.0D
    var sumOfCriticsByP2 = 0.0D
    var sumOfPowedCriticsByP1 = 0.0D
    var sumOfPowedCriticsByP2 = 0.0D
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
            sumOfCriticsByP1 += r1
            sumOfCriticsByP2 += r2
            sumOfPowedCriticsByP1 += math.pow(r1, 2)
            sumOfPowedCriticsByP2 += math.pow(r2, 2)
            sumOfProducts += r1 * r2
          }
          case _ =>
        }
      }
    }
    val numerator: Double = sumOfProducts - (sumOfCriticsByP1 * sumOfCriticsByP2 / bothContainedCount)
    val denominator: Double = math.sqrt(
      (sumOfPowedCriticsByP1 - math.pow(sumOfCriticsByP1, 2))
        * (sumOfPowedCriticsByP2 - math.pow(sumOfCriticsByP2, 2))
        / bothContainedCount
    )
    denominator match {
      case 0.0D => 0.0D
      case _ => numerator / denominator
    }
  }

}