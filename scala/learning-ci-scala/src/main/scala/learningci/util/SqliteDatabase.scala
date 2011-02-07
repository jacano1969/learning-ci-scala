package learningci.util

import java.sql.{Array => _, _}

class SqliteDatabase(val file: String) {

  lazy val conn: Connection = {
    Class.forName("org.sqlite.JDBC")
    DriverManager.getConnection("jdbc:sqlite:%s".format(file))
  }

  def begin(): Unit = {
    conn.setAutoCommit(false)
  }

  def rollback(): Unit = {
    try {
      conn.rollback
      conn.setAutoCommit(true)
    } catch {
      case e => {
        e.printStackTrace
      }
    }
  }

  def commit(): Unit = {
    try {
      conn.commit
      conn.setAutoCommit(true)
    } catch {
      case _ => rollback
    }
  }

  def close(): Unit = {
    try {
      conn.close
    } catch {
      case e => e.printStackTrace
    }
  }

  def execteQuery(query: String): ResultSet = {
    try {
      val statement = conn.createStatement()
      statement.executeQuery(query)
    } catch {
      case _ => {
        rollback
        return null
      }
    }
  }

  def executeUpdate(query: String): Int = {
    try {
      val statement = conn.createStatement()
      statement.executeUpdate(query)
    } catch {
      case _ => {
        rollback
        return 0
      }
    }
  }

}