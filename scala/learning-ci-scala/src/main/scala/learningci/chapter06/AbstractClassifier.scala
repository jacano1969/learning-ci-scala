package learningci.chapter06

import learningci.chapter06.datastore._

abstract class AbstractClassifier extends Classifier {

  protected var datastore: Datastore = new InMemoryDatastore

  override def getDatastore(): Datastore = {
    this.datastore
  }

  override def setDatastore(datastore: Datastore): Unit = {
    this.datastore = datastore
  }

  override def getDistinctWords(document: Document): List[Word] = {
    val words = """\s+""".r.split(document.value).toList
    words filter {
      word => word.length > 2 && word.length < 20
    } map {
      word => Word(word.toLowerCase)
    }
  }

  override def getTagProbabilityForWord(word: Word, tag: Tag): Double = {
    val countPerTag = datastore.getCountPerTag(tag)
    countPerTag match {
      case 0.0D => 0.0D
      case _ => datastore.getWordCountPerTag(word, tag) / countPerTag
    }
  }

  override def getTagProbabilityForDocument(document: Document, tag: Tag): Double = {
    throw new UnsupportedOperationException
  }

  override def getClassifiedTag(document: Document, default: Tag): Tag = {
    throw new UnsupportedOperationException
  }

  override def train(document: Document,
                     tag: Tag): Unit = {
    val words = getDistinctWords(document)
    words foreach {
      word => datastore.addToTagCountForWords(word, tag)
    }
    datastore.addToTagCount(tag)
  }

  override def getWeightedWordProbability(word: Word,
                                          tag: Tag,
                                          weight: Double = 1.0D,
                                          assumedProbability: Double = 0.5D): Double = {
    val basicProbability = getTagProbabilityForWord(word, tag)
    val sumOfWordCounts =
      datastore.getAllTags map {
        eachTag => datastore.getWordCountPerTag(word, eachTag)
      } sum;
    ((weight * assumedProbability) + (sumOfWordCounts * basicProbability)
      ) / (weight + sumOfWordCounts)
  }

}
