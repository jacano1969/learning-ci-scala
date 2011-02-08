package learningci.chapter06.datastore

import learningci.util.SqliteDatabase
import collection.mutable.ListBuffer
import learningci.chapter06._

class SqliteDatastore extends Datastore {

  protected val db = new SqliteDatabase("./SqliteDatastore.db")

  override def addToTagCountForWords(word: Word,
                                     tag: Tag): Unit = {
    val result = db.executeQuery(
      "select word,tag,count from tag_count_for_words where word = ? and tag = ?;",
      List(word.value, tag.value))
    result.size match {
      case 0 => db.executeUpdate(
        "insert into tag_count_for_words values (?,?,?);",
        List(word.value, tag.value, 1)
      )
      case 1 => db.executeUpdate(
        "update tag_count_for_words set count = ? where word = ? and tag = ?",
        List(result(0).getOrElse("count", 0).toString.toInt + 1, word.value, tag.value)
      )
      case _ => throw new IllegalStateException
    }
  }

  override def addToTagCount(tag: Tag): Unit = {
    val result = db.executeQuery(
      "select tag,count from tag_count where tag = ?;",
      List(tag.value))
    result.size match {
      case 0 => db.executeUpdate(
        "insert into tag_count values (?,?);",
        List(tag.value, 1)
      )
      case 1 => db.executeUpdate(
        "update tag_count set count = ? where tag = ?",
        List(result(0).getOrElse("count", 0).toString.toInt + 1, tag.value)
      )
      case _ => throw new IllegalStateException
    }
  }

  override def getWordCountPerTag(word: Word,
                                  tag: Tag): Double = {
    val result = db.executeQuery(
      "select word,tag,count from tag_count_for_words where word = ? and tag = ?",
      List(word.value, tag.value)
    )
    result.size match {
      case 0 => 0.0D
      case 1 => result(0).getOrElse("count", 0).toString.toDouble
      case _ => throw new IllegalStateException
    }
  }

  override def getCountPerTag(tag: Tag): Double = {
    val result = db.executeQuery(
      "select tag,count from tag_count where tag = ?",
      List(tag.value)
    )
    result.size match {
      case 0 => 0.0D
      case 1 => result(0).getOrElse("count", 0).toString.toDouble
      case _ => throw new IllegalStateException
    }
  }

  override def getSumOfTagCounts(): Double = {
    val result = db.executeQuery("select sum(count) from tag_count")
    result.size match {
      case 1 => result(0).getOrElse("sum(count)", 0).toString.toDouble
      case _ => throw new IllegalStateException
    }
  }

  override def getAllTags(): List[Tag] = {
    val buffer = new ListBuffer[Tag]
    val result = db.executeQuery("select tag from tag_count")
    result foreach {
      each => {
        each.get("tag") match {
          case Some(value) => buffer.append(Tag(value.toString))
          case _ =>
        }
      }
    }
    buffer.toList
  }

}
