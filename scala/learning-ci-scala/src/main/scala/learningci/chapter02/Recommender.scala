package learningci.chapter02

import collection.mutable.{ListBuffer, HashMap}

trait Recommender {

  def getSimilarity(critics: List[Critic],
                    p1: Person,
                    p2: Person): Double

  def getSimilarPersons(critics: List[Critic],
                        self: Person,
                        maxCount: Int): List[Tuple2[Person, Double]] = {
    val persons = critics map {
      case Critic(person, movie, rating) => person
    } distinct

    persons filter {
      person => person != self
    } map {
      case person => {
        (person, getSimilarity(critics, self, person))
      }
    } sortWith (_._2 > _._2) slice (0, maxCount)
  }

  def getRecommendations(critics: List[Critic],
                         self: Person): List[Tuple2[Movie, Double]] = {
    val persons = critics map {
      case Critic(person, movie, rating) => person
    } distinct

    val similarPersons: List[Tuple2[Person, Double]] = persons filter {
      case person => person != self
    } map {
      case person => {
        (person, getSimilarity(critics, self, person))
      }
    }
    val criticsBySelf = critics filter {
      case Critic(person, movie, rating) => person == self
    }
    val weightedCritics = new HashMap[Movie, Double]()
    val sumsOfSimilarity = new HashMap[Movie, Double]()
    critics foreach {
      case Critic(person, movie, rating) => {
        criticsBySelf filter {
          case Critic(p, m, r) => m == movie
        } size match {
          case 0 => {
            val similar = similarPersons filter {
              case (p, similarity) => p.name == person.name
            }
            val similarity = similar.head._2
            weightedCritics.update(movie,
              weightedCritics.getOrElse(movie, 0.0D) + rating * similarity)
            sumsOfSimilarity.update(movie,
              sumsOfSimilarity.getOrElse(movie, 0.0D) + similarity)
          }
          case _ =>
        }
      }
    }
    var buffer = new ListBuffer[Tuple2[Movie, Double]]
    weightedCritics map {
      case (movie, weightedValue) => {
        sumsOfSimilarity.get(movie) match {
          case Some(denominator) => {
            buffer.append((movie, weightedValue / denominator))
          }
          case None => {
            (movie, 0.0D)
          }
        }
      }
    }
    buffer.toList.sortWith(_._2 > _._2)
  }

}