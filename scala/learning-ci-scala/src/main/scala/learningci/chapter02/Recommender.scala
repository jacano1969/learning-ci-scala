package learningci.chapter02

import collection.mutable.ListBuffer
import collection.mutable.HashMap

trait Recommender {

  def getSimilarity(critics: Map[String, Map[String, Double]], p1: Person, p2: Person): Double

  def getSimilarPersons(critics: Map[String, Map[String, Double]],
                        self: Person,
                        maxCount: Int): List[Tuple2[Person, Double]] = {
    val dest = new ListBuffer[Tuple2[Person, Double]]()
    critics.foreach({
      case (name, criticsByPerson) => {
        if (name != self.name) {
          val similarity = getSimilarity(critics, self, new Person(name))
          dest.append((new Person(name), similarity))
        }
      }
    })
    dest.toList.sortWith(_._2 > _._2).slice(0, maxCount)
  }

  def getRecommendations(critics: Map[String, Map[String, Double]],
                         self: Person): List[Tuple2[String, Double]] = {
    val weightedCritics = new HashMap[String, Double]()
    val sumsOfSimilarity = new HashMap[String, Double]()
    val criticsBySelf = critics.get(self.name).get
    critics.foreach({
      case (name, criticsByOther) => {
        if (name != self.name) {
          val similarity = getSimilarity(critics, self, new Person(name))
          if (similarity > 0) {
            criticsByOther.foreach({
              case (title, rating) => {
                if (criticsBySelf.get(title) == None && criticsByOther.get(title) != None) {
                  if (!weightedCritics.get(title).isDefined) {
                    weightedCritics.put(title, 0.0D)
                  }
                  weightedCritics.put(title,
                    weightedCritics.get(title).get + criticsByOther.get(title).get * similarity)
                  if (!sumsOfSimilarity.get(title).isDefined) {
                    sumsOfSimilarity.put(title, 0.0D)
                  }
                  sumsOfSimilarity.put(title, sumsOfSimilarity.get(title).get + similarity)
                }
              }
            })
          }
        }
      }
    })
    val dest = new ListBuffer[Tuple2[String, Double]]()
    weightedCritics.foreach({
      case (title, value) => {
        val element = (title, weightedCritics.get(title).get / sumsOfSimilarity.get(title).get)
        dest.append(element)
      }
    })
    dest.toList.sortWith(_._2 > _._2)
  }

}