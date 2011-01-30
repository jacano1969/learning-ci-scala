package learningci.chapter02

import collection.mutable.HashSet

class EuclideanDistanceRecommender extends Recommender {

  override def getSimilarity(critics: Map[String, Map[String, Double]], p1: Person, p2: Person) = {
    var sumOfValues: Double = 0.0D
    val critics1 = critics.get(p1.name).get
    val critics2 = critics.get(p2.name).get
    val bothContains = new HashSet[String]
    critics1.foreach({
      case (title, rating) => {
        if (critics2.get(title) != None) {
          bothContains.add(title)
        }
      }
    })
    if (bothContains.size > 0) {
      bothContains.foreach({
        title => {
          sumOfValues += math.pow(critics1.get(title).get - critics2.get(title).get, 2)
        }
      })
    }
    1 / (1 + sumOfValues)
  }

}