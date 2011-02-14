package sandbox.visibility.impl

import sandbox.visibility._

private[visibility] case class CSV(override val string: String) extends EncodedString {

  override val separator = Separator.COMMA

}
