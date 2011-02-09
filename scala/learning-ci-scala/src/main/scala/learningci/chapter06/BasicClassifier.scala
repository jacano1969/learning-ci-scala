package learningci.chapter06

import learningci.chapter06.datastore._

class BasicClassifier(override val datastore: Datastore) extends AbstractClassifier(datastore) {

  def this() = {
    this (new InMemoryDatastore)
  }

}