package sandbox.actor

import actors.Actor
import actors.Actor._
import java.util.Date

object FirstActorSample extends Application {
  val robert = new Redford
  robert.start()
  println("Before act?")

  Thread.sleep(1000L)
  System.exit(0)
}

class Redford extends Actor {
  def act() {
    Thread.sleep(500L)
    println("A lot of what acting is, is paying attention.")
  }
}

object ActorFactorySample extends Application {
  // Actor.actor
  val paulNewman = actor {
    Thread.sleep(500L)
    println("To be an actor, you have to be a child.")
  }
  println("Before act?")

  Thread.sleep(2000L)
  System.exit(0)

}

object PattternMatchActorSample extends Application {
  // Actor.actor, Actor.loop, Actor.receive
  val fussyActor = actor {
    loop {
      receive {
        case str: String => println("I got a String : " + str)
        case int: Int => println("I got an Int : " + int)
        case unknown => println("I have no idea what I just got. : " + unknown)
      }
    }
  }
  fussyActor.!("Hi, all")
  fussyActor ! 23
  fussyActor ! 3.33

  Thread.sleep(2000L)
  System.exit(0)

}

object MailBoxSample extends Application {
  val mailBox = actor {
    loop {
      react {
        case param: String
          if param.split("by")(0) == "how many? "
            && !param.split("by")(1).isEmpty => {
          // Actor.mailboxSize
          println(param.split("by")(1) + " has got " + mailboxSize.toString + " messages remained in the mail box")

        }
      }
    }
  }
  mailBox ! "how many? by Kaz"
  Thread.sleep(100L)
  mailBox ! "unknown request"
  mailBox ! "unknown request"
  mailBox ! "unknown request"
  Thread.sleep(100L)
  mailBox ! "how many? by Tom"
  Thread.sleep(100L)
  mailBox ! "how many? by Brian"
  mailBox ! "unknown request"
  Thread.sleep(100L)
  mailBox ! "how many? by Kent"

  Thread.sleep(1000L)
  System.exit(0)

}
