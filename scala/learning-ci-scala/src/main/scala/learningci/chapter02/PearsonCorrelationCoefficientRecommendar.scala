package learningci.chapter02

class PearsonCorrelationCoefficientRecommendar extends Recommender {

  override def getSimilarity(critics: List[Critic],
                             p1: Person,
                             p2: Person): Double = {

    var bothContained: Int = 0
    var sumOfCriticsByP1: Double = 0.0D
    var sumOfCriticsByP2: Double = 0.0D
    var sumOfPowedCriticsByP1: Double = 0.0D
    var sumOfPowedCriticsByP2: Double = 0.0D
    var sumOfProducts: Double = 0.0D

    val criticsByPerson1 = critics filter {
      case Critic(person, title, rating) => person == p1
    }
    val criticsByPerson2 = critics filter {
      case Critic(person, title, rating) => person == p2
    }

    criticsByPerson2 foreach {
      case Critic(person2, title2, rating2) => {
        val bothContains = criticsByPerson1 foreach {
          case Critic(person1, title1, rating1) if title1 == title2 => {
            bothContained += 1
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

    val numerator: Double = sumOfProducts - (sumOfCriticsByP1 * sumOfCriticsByP2 / bothContained)
    val denominator: Double = math.sqrt(
      (sumOfPowedCriticsByP1 - math.pow(sumOfCriticsByP1, 2))
        * (sumOfPowedCriticsByP2 - math.pow(sumOfCriticsByP2, 2))
        / bothContained
    )
    denominator match {
      case 0.0D => 0.0D
      case _ => numerator / denominator
    }

  }

}