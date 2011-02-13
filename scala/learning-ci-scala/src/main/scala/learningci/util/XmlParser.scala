package learningci.util

import java.io._
import xml._

class XmlParser {

  def parseFile(filepath: String): Elem = {
    XML.load(new FileInputStream(new File(filepath)))
  }

}