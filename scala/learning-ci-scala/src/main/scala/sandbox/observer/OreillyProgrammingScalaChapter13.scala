package sandbox.observer

/**
 * http://programming-scala.labs.oreilly.com/ch13.html
 */
abstract class AbstractContainer {
  type S <: Subject
  type O <: Observer

  trait Subject {
    // self-type annotation
    // we can now use "self" as an alias for "this"
    self: S =>
    private var observers = List[O]()

    def addObserver(observer: O) = observers ::= observer

    //[error] /Users/seratch/IdeaProjects/learning-ci-scala/scala/learning-ci-scala/src/main/scala/sandbox/observer/AbstractContainer.scala:14: type mismatch;
    //[error]  found   : Subject.this.type (with underlying type AbstractContainer.this.Subject)
    //[error]  required: AbstractContainer.this.S
    //[error]     def notifyObservers = observers foreach (_.receiveUpdate(this))
    def notifyObservers = observers foreach (_.receiveUpdate(self))
  }

  trait Observer {
    def receiveUpdate(subject: S)
  }

}

trait Clickable {
  def click()
}

class Button(val label: String) extends Clickable {
  def click() = println("click!")
}

object Container extends AbstractContainer {
  type S = ObservableButton
  type O = ButtonObserver

  class ObservableButton(name: String) extends Button(name) with Subject {
    override def click() = {
      super.click
      notifyObservers
    }
  }

  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }

  import scala.collection.mutable.HashMap

  class ButtonClickObserver extends Container.ButtonObserver {
    val clicks = new HashMap[String, Int]

    def receiveUpdate(button: Container.ObservableButton) = {
      val count = clicks.getOrElse(button.label, 0) + 1
      clicks.update(button.label, count)
    }
  }

}

object Main extends Application {

  val button1 = new Container.ObservableButton("button1")
  val button2 = new Container.ObservableButton("button2")
  val button3 = new Container.ObservableButton("button3")

  val observer = new Container.ButtonClickObserver

  button1.addObserver(observer)
  button2.addObserver(observer)
  button3.addObserver(observer)

  clickButton(button1, 1)
  clickButton(button2, 2)
  clickButton(button3, 3)

  assert(observer.clicks.get("button1").get == 1)
  assert(observer.clicks.get("button2").get == 2)
  assert(observer.clicks.get("button3").get == 3)

  def clickButton(button: Button, nClicks: Int) = {
    for (i <- 1 to nClicks)
      button.click()
  }

}
