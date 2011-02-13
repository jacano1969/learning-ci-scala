package learningci.chapter06

import learningci.chapter06.datastore._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import learningci.util.{XmlParser, SqliteDatabase}

class SqliteNaiveBayesClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.5.1 : get basic probability " should "return 0.0625" in {
    DatabaseTool.initialize()
    val classifier = new NaiveBayesClassifier(new SqliteDatastore)
    classifier.getBasicProbabilityForDocument(Document("Nobody owns the water"), JudgeTag.Good) should equal(0.0625D)
  }

  "Chapter 6.5.2 : After training all, get tag probability " should "return 0.15624, 0.05" in {
    DatabaseTool.initialize()
    val classifier = new NaiveBayesClassifier(new SqliteDatastore)
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getTagProbabilityForDocument(Document("quick rabbit"), JudgeTag.Good) should equal(0.15624999999999997D)
    classifier.getTagProbabilityForDocument(Document("quick rabbit"), JudgeTag.Bad) should equal(0.05D)
  }

  "Chapter 6.5.3 : After training all, get classified tag " should "return expected values" in {
    DatabaseTool.initialize()
    val classifier = new NaiveBayesClassifier(new SqliteDatastore)
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick rabbit")) should equal(JudgeTag.Good)
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Bad)

    classifier.setThreshold(JudgeTag.Bad, 3.0D)
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Unknown)

    for (i <- 1 to 10) {
      Documents.all foreach {
        case (document, tag) => classifier.train(document, tag)
      }
    }
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Bad)
  }

  "Chapter 6.7 : get classified tag" should "return bad" in {
    DatabaseTool.initialize()
    val classifier = new NaiveBayesClassifier(new SqliteDatastore)
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick money")) should equal(JudgeTag.Bad)
  }

  "Chapter 6.8 : Reading rss feed" should "be available" in {
    val elem = new XmlParser().parseFile("src/test/resources/learningci/chapter06/python_search.xml")
    val title = elem \ "channel" \ "title"
    title.text should equal("Google Blog Search: python")
    val items = elem \\ "item"

    DatabaseTool.initialize()
    val classifier = new NaiveBayesClassifier(new SqliteDatastore)
    items foreach {
      item => {
        println("train target : " + item.text)
        //classifier.train(Document(item.text), PythonTag.Programming)
      }
    }
  }

  "Chapter 6.8 : \"Google Blog Search: python\"" should "be available" in {
    DatabaseTool.db_=(new SqliteDatabase("src/test/resources/learningci/chapter06/python_feed.db.sample"))
    //DatabaseTool.print_tagCount
    //DatabaseTool.print_tagCountForWords
    val datastore = new SqliteDatastore(DatabaseTool.db)
    val classifier = new NaiveBayesClassifier(datastore)
    classifier.getClassifiedTag(Document("Python is a programming language that lets you work more quickly and integrate your systems more effectively. You can learn to use Python and see almost immediate gains in productivity and lower maintenance costs.")) should equal(PythonTag.Programming)
    classifier.getClassifiedTag(Document("The irreverent Monty Python comedy troupe present a series of skits which are often surreal, baudy, uncompromising, tasteless, but inevitably hilarious.")) should equal(PythonTag.Monty)
    classifier.getClassifiedTag(Document("As long as you accept this characteristic about ball pythons, and you're okay with it, then I would still recommend them as a pet snake.")) should equal(PythonTag.Snake)
  }

}