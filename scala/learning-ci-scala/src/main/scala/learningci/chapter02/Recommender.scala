package learningci.chapter02

import collection.mutable.ListBuffer
import collection.mutable.HashMap

trait Recommender {

  def getSimilarity(critics: Map[String, Map[String, Double]], p1: Person, p2: Person): Double

  def getSimilarPersons(critics: Map[String, Map[String, Double]],
                        self: Person,
                        maxCount: Int): List[Tuple2[Person, Double]] = {
    val buffer = new ListBuffer[Tuple2[Person, Double]]()
    critics foreach {
      case (name, criticsByPerson) => {
        if (name != self.name) {
          buffer.append((new Person(name), getSimilarity(critics, self, new Person(name))))
        }
      }
    }
    buffer.toList.sortWith(_._2 > _._2).slice(0, maxCount)
  }

  def getRecommendations(critics: Map[String, Map[String, Double]],
                         self: Person): List[Tuple2[String, Double]] = {
    val weightedCritics = new HashMap[String, Double]()
    val sumsOfSimilarity = new HashMap[String, Double]()
    critics foreach {
      case (name, criticsByOther) => {
        if (name != self.name) {
          val similarity = getSimilarity(critics, self, new Person(name))
          if (similarity > 0) {
            criticsByOther foreach {
              case (title, rating) => {
                critics.get(self.name) match {
                  case Some(criticsBySelf) => {
                    criticsBySelf.get(title) match {
                      case Some(v) =>
                      case None => {
                        criticsByOther.get(title) match {
                          case Some(valueByOther) => {
                            weightedCritics.update(title, weightedCritics.getOrElse(title, 0.0D) + valueByOther * similarity)
                            sumsOfSimilarity.update(title, sumsOfSimilarity.getOrElse(title, 0.0D) + similarity)
                          }
                          case None =>
                        }
                      }
                    }
                  }
                  case None =>
                }
              }
            }
          }
        }
      }
    }
    val buffer = new ListBuffer[Tuple2[String, Double]]()
    weightedCritics foreach {
      case (title, weighted) => {
        sumsOfSimilarity.get(title) match {
          case Some(sum) => {
            val element = (title, weighted / sum)
            buffer.append(element)
          }
          case None =>
        }
      }
    }
    buffer.toList.sortWith(_._2 > _._2)
  }

}