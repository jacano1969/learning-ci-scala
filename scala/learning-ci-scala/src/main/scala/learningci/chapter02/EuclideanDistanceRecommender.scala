package learningci.chapter02

class EuclideanDistanceRecommender extends Recommender {

  override def getSimilarity(critics: List[Critic],
                             p1: Person,
                             p2: Person): Double = {

    val criticsByPerson1 = critics filter {
      case Critic(person, title, rating) => person == p1
    }
    val criticsByPerson2 = critics filter {
      case Critic(person, title, rating) => person == p2
    }

    val sumOfValues = criticsByPerson2 map {
      case Critic(person2, title2, rating2) => {
        val bothContains = criticsByPerson1 filter {
          case Critic(person1, title1, rating1) => title1 == title2
        }
        bothContains.size match {
          case 1 => math.pow(bothContains.head.rating - rating2, 2)
          case _ => 0.0D
        }
      }
    }
    1 / (1 + sumOfValues.sum)

  }

}