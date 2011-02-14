/**
 * Programmming Scala 13.6.1 sample code
 * http://oreilly.com/catalog/9780596155964
 */
package sandbox.visibility

import impl._

trait EncodedString {
  protected[visibility] val string: String
  val separator: Separator.Delimiter

  override def toString = string

  def toTokens: List[String] = string.split(separator.toString).toList
}

object Separator extends Enumeration {
  type Delimiter = Value
  val COMMA = Value(",")
  val TAB = Value("\t")
}

object EncodedString extends Enumeration {

  def apply(s: String, sep: Separator.Delimiter) = sep match {
    case Separator.COMMA => CSV(s)
    case Separator.TAB => TSV(s)
  }

  def unapply(es: EncodedString) = Some(Pair(es.string, es.separator))

}