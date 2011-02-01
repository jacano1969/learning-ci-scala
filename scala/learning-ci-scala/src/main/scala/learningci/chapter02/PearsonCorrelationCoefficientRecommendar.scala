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
      case Critic(person, movie, rating) => person == p1
    }
    val criticsByPerson2 = critics filter {
      case Critic(person, movie, rating) => person == p2
    }
    criticsByPerson2 foreach {
      case Critic(_, movie2, rating2) => {
        val bothContains = criticsByPerson1 foreach {
          case Critic(_, movie1, rating1) if movie1 == movie2 => {
            bothContainedCount += 1
            sumOfCriticsByP1 += rating1
            sumOfCriticsByP2 += rating2
            sumOfPowedCriticsByP1 += math.pow(rating1, 2)
            sumOfPowedCriticsByP2 += math.pow(rating2, 2)
            sumOfProducts += rating1 * rating2
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