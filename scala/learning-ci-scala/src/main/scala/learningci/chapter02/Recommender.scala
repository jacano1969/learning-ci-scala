package learningci.chapter02

import collection.mutable.{ListBuffer, HashMap}

trait Recommender {

  def getSimilarity(critics: List[Critic],
                    p1: Person,
                    p2: Person): Double

  def getSimilarPersons(critics: List[Critic],
                        self: Person,
                        maxCount: Int): List[Tuple2[Person, Double]] = {
    val personsButSelf =
      critics map {
        case Critic(person, _, _) => person
      } filter (_ != self) distinct

    personsButSelf map {
      case person => (person, getSimilarity(critics, self, person))
    } sortWith (_._2 > _._2) slice (0, maxCount)
  }

  def getRecommendations(critics: List[Critic],
                         self: Person): List[Tuple2[Movie, Double]] = {
    val personsButSelf =
      critics map {
        case Critic(person, _, _) => person
      } filter (_ != self) distinct

    val personsAndSimilarities: List[Tuple2[Person, Double]] =
      personsButSelf map {
        case person => (person, getSimilarity(critics, self, person))
      }

    val criticsBySelf =
      critics filter {
        case Critic(person, _, _) => person == self
      }

    val weightedCritics = new HashMap[Movie, Double]()
    val sumsOfSimilarity = new HashMap[Movie, Double]()
    critics foreach {
      case Critic(person, movie, rating) => {
        criticsBySelf filter {
          case Critic(_, movieBySelf, _) => movieBySelf == movie
        } size match {
          case 0 => {
            val personAndSimilarity = personsAndSimilarities filter {
              case (personThatHasSimilarity, _) => personThatHasSimilarity == person
            } head
            val similarity = personAndSimilarity._2
            weightedCritics.update(movie, weightedCritics.getOrElse(movie, 0.0D) + rating * similarity)
            sumsOfSimilarity.update(movie, sumsOfSimilarity.getOrElse(movie, 0.0D) + similarity)
          }
          case _ =>
        }
      }
    }

    val listBuffer = new ListBuffer[Tuple2[Movie, Double]]()
    weightedCritics foreach {
      case (movie, weightedValue) => {
        sumsOfSimilarity.get(movie) match {
          case Some(similarityValueDenominator) => {
            listBuffer.append((movie, weightedValue / similarityValueDenominator))
          }
          case None =>
        }
      }
    }
    listBuffer.toList sortWith (_._2 > _._2)
  }

}