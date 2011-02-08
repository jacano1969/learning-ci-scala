package learningci.util

import java.sql._
import collection.mutable.{ListBuffer, HashMap}

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
      case e => {
        e.printStackTrace
        rollback
      }
    }
  }

  def close(): Unit = {
    try {
      conn.close
    } catch {
      case e => e.printStackTrace
    }
  }

  private def bindParams(statement: PreparedStatement, params: List[Any]): Unit = {
    var i = 1
    params foreach {
      param => {
        statement.setObject(i, param)
        i += 1
      }
    }
  }

  def executeQuery(query: String, params: List[Any] = List()): List[Map[String, Any]] = {
    var buffer = new ListBuffer[Map[String, Any]]
    try {
      val statement = conn.prepareStatement(query)
      bindParams(statement, params)
      val resultSet = statement.executeQuery()
      resultSet match {
        case rs if resultSet != null => {
          while (rs.next) {
            val each = new HashMap[String, Object]
            for (i <- 1 to rs.getMetaData.getColumnCount) {
              val name = rs.getMetaData.getColumnName(i);
              each.update(name, rs.getObject(name))
            }
            buffer.append(each.toMap)
          }
        }
        case _ =>
      }
    } catch {
      case e => {
        e.printStackTrace
        rollback
      }
    }
    buffer.toList
  }

  def executeUpdate(query: String, params: List[Any] = List()): Int = {
    var dest: Int = 0
    try {
      val statement = conn.prepareStatement(query)
      var i = 1
      params foreach {
        param => {
          statement.setObject(i, param)
          i += 1
        }
      }
      dest = statement.executeUpdate()
    } catch {
      case e => {
        e.printStackTrace
        rollback
      }
    }
    dest
  }

}