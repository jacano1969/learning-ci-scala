package sandbox.visibility.impl

import sandbox.visibility._

private[visibility] case class TSV(override val string: String) extends EncodedString {

  override val separator = Separator.TAB

}
