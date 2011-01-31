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
      case Critic(person, title, rating) => person
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
                         self: Person): List[Tuple2[String, Double]] = {

    val persons = critics map {
      case Critic(person, title, rating) => person
    } distinct

    val similarPersons: List[Tuple2[Person, Double]] = persons filter {
      case person => person != self
    } map {
      case person => {
        (person, getSimilarity(critics, self, person))
      }
    }

    val criticsBySelf = critics filter {
      case Critic(person, title, rating) => person == self
    }

    val weightedCritics = new HashMap[String, Double]()
    val sumsOfSimilarity = new HashMap[String, Double]()

    critics foreach {
      case Critic(person, title, rating) => {
        criticsBySelf filter {
          case Critic(p, t, r) => t == title
        } size match {
          case 0 => {
            val similar = similarPersons filter {
              case (p, similarity) => p.name == person.name
            }
            val similarity = similar.head._2
            weightedCritics.update(title,
              weightedCritics.getOrElse(title, 0.0D) + rating * similarity)
            sumsOfSimilarity.update(title,
              sumsOfSimilarity.getOrElse(title, 0.0D) + similarity)
          }
          case _ =>
        }
      }
    }

    var buffer = new ListBuffer[Tuple2[String, Double]]
    weightedCritics map {
      case (title, weightedValue) => {
        sumsOfSimilarity.get(title) match {
          case Some(denominator) => {
            buffer.append((title, weightedValue / denominator))
          }
          case None => {
            (title, 0.0D)
          }
        }
      }
    }
    buffer.toList.sortWith(_._2 > _._2)

  }

}