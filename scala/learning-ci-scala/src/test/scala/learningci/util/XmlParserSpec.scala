package learningci.util

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import xml._

class XmlParserSpec extends FlatSpec with ShouldMatchers {

  "XmlParser#parseFile" should "be available" in {
    val elem: Elem = new XmlParser().parseFile("src/test/resources/learningci/chapter06/python_search.xml")
    val title: NodeSeq = elem \ "channel" \ "title"
    title.text should equal("Google Blog Search: python")
  }

}