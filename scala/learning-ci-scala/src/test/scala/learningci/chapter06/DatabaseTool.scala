package learningci.chapter06

import learningci.util.SqliteDatabase

object DatabaseTool {

  var filename = "./SqliteDatastore.db"

  var _db = new SqliteDatabase(filename)

  def db() = this._db

  def db_=(newDb: SqliteDatabase) = {
    this._db = newDb
  }

  def initialize() = {
    db.executeUpdate("drop table if exists tag_count_for_words;")
    db.executeUpdate("drop table if exists tag_count;")
    db.executeUpdate("create table if not exists tag_count_for_words (word,tag,count);")
    db.executeUpdate("create table if not exists tag_count (tag,count);")
  }

  def print_tagCountForWords = {
    db.executeQuery("select * from tag_count_for_words;") foreach {
      case each => println(each.getOrElse("word", "_") + ","
        + each.getOrElse("tag", "_") + "," + each.getOrElse("count", "_"))
    }
  }

  def print_tagCount = {
    db.executeQuery("select * from tag_count;") foreach {
      case each => println(each.getOrElse("tag", "_") + "," + each.getOrElse("count", "_"))
    }
  }

}
