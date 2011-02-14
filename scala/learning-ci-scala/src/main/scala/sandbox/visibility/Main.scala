package sandbox.visibility

import impl._

object Main extends Application {

  def p(s: EncodedString) = {
    println("EncodedString: " + s)
    s.toTokens foreach (x => println("token: " + x))
  }

  val csv = EncodedString("Scala,is,great!", Separator.COMMA)
  val tsv = EncodedString("Scala\tis\tgreat!", Separator.TAB)

  p(csv)
  p(tsv)

  println("\nExtraction:")

  List(csv, "ProgrammingScala", tsv, 3.14159) foreach {
    case EncodedString(str, del) => {
      println("EncodedString: \"" + str + "\", delimiter: \"" + del + "\"")
    }
    case s: String => println(s)
    case x => println("Unknown value:" + x)
  }

}