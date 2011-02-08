package learningci.util

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SqliteDatabaseSpec extends FlatSpec with ShouldMatchers {

  "SqliteDatabase" should "be available" in {
    val db = new SqliteDatabase("./test.db")
  }

  "SqliteDatabase#executeQuery" should "be available" in {
    val db = new SqliteDatabase("./test.db")
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values (?,?);", List("foo", "foovalue"))
    val result = db.executeQuery("select * from sample;")
    result foreach {
      map => {
        map foreach {
          case (key, value) => {
            println(key + "," + value)
            key match {
              case "key" => value should equal("foo")
              case "value" => value should equal("foovalue")
              case _ => fail("unknown")
            }
          }
        }
      }
    }
    db.close
  }

  "SqliteDatabase#executeQuery after commit" should "be available" in {
    val db = new SqliteDatabase("./test.db")
    db.begin
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values (?,?);", List("foo", "foovalue"))
    db.commit
    val result = db.executeQuery("select * from sample;")
    result foreach {
      map => {
        map foreach {
          case (key, value) => {
            println(key + "," + value)
            key match {
              case "key" => value should equal("foo")
              case "value" => value should equal("foovalue")
              case _ => fail("unknown")
            }
          }
        }
      }
    }
    db.close
  }

  "SqliteDatabase#executeUpdate" should "be available" in {
    val db = new SqliteDatabase("./test.db")
    db.begin
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values ('foo','foovalue');")
    db.executeUpdate("drop table if exists sample;")
    db.commit
    db.close
  }

}