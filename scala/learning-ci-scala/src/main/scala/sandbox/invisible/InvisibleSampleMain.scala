package sandbox.invisible

object InvisibleSampleMain extends Application {

  // compile error
  // error: class CSV cannot be accessed in package sandbox.visibility.impl
  // val invisible = new CSV("hoge")

}