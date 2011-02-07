package learningci.util

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SqliteDatabaseSpec extends FlatSpec with ShouldMatchers {

  "SqliteDatabase" should "be available" in {
    val db = new SqliteDatabase("test.db")
  }

  "SqliteDatabase#executeQuery" should "be available" in {
    val db = new SqliteDatabase("./test.db")
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values ('foo','foovalue');")
    val resultSet = db.execteQuery("select * from sample;")
    while (resultSet.next()) {
      print(resultSet.getString("key") + "," + resultSet.getString("value"))
      resultSet.getString("key") should equal("foo")
      resultSet.getString("value") should equal("foovalue")
    }
    db.close
  }

  "SqliteDatabase#executeQuery after commit" should "be available" in {
    val db = new SqliteDatabase("./test.db")
    db.begin
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values ('foo','foovalue');")
    db.commit
    val resultSet = db.execteQuery("select * from sample;")
    while (resultSet.next()) {
      print(resultSet.getString("key") + "," + resultSet.getString("value"))
      resultSet.getString("key") should equal("foo")
      resultSet.getString("value") should equal("foovalue")
    }
    db.close
  }

  "SqliteDatabase#executeUpdate" should "be available" in {
    val db = new SqliteDatabase("test.db")
    db.begin
    db.executeUpdate("drop table if exists sample;")
    db.executeUpdate("create table if not exists sample(key,value);")
    db.executeUpdate("insert into sample values ('foo','foovalue');")
    db.executeUpdate("drop table if exists sample;")
    db.commit
    db.close
  }

}