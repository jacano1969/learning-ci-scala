package learningci.chapter02

case class Person(val name: String) {
}

object Person {
  val lisa = Person("Lisa Rose")
  val gene = Person("Gene Seymour")
  val michael = Person("Michael Phillips")
  val claudia = Person("Claudia Puig")
  val mick = Person("Mick LaSalle")
  val jack = Person("Jack Matthews")
  val toby = Person("Toby")
}

case class Movie(val title: String) {
}

object Movie {
  val ladyInTheWater = Movie("Lady in the Water")
  val snakesOnAPlane = Movie("Snakes on a Plane")
  val justMyLuck = Movie("Just My Luck")
  val supermanReturns = Movie("Superman Returns")
  val youMeAndDupree = Movie("You, Me and Dupree")
  val theNightListener = Movie("The Night Listener")
}

case class Critic(val person: Person, val movie: Movie, val rating: Double) {
}

object Critics {

  import Person._
  import Movie._

  val all: List[Critic] = List(

    Critic(lisa, ladyInTheWater, 2.5D)
    , Critic(lisa, snakesOnAPlane, 3.5D)
    , Critic(lisa, justMyLuck, 3.0D)
    , Critic(lisa, supermanReturns, 3.5D)
    , Critic(lisa, youMeAndDupree, 2.5D)
    , Critic(lisa, theNightListener, 3.0D)

    , Critic(gene, ladyInTheWater, 3.0D)
    , Critic(gene, snakesOnAPlane, 3.5D)
    , Critic(gene, justMyLuck, 1.5D)
    , Critic(gene, supermanReturns, 5.0D)
    , Critic(gene, youMeAndDupree, 3.5D)
    , Critic(gene, theNightListener, 3.0D)

    , Critic(michael, ladyInTheWater, 2.5D)
    , Critic(michael, snakesOnAPlane, 3.0D)
    , Critic(michael, supermanReturns, 3.5D)
    , Critic(michael, theNightListener, 3.5D)

    , Critic(claudia, snakesOnAPlane, 3.5D)
    , Critic(claudia, justMyLuck, 3.0D)
    , Critic(claudia, supermanReturns, 4.0D)
    , Critic(claudia, youMeAndDupree, 2.5D)
    , Critic(claudia, theNightListener, 4.5D)

    , Critic(mick, ladyInTheWater, 3.0D)
    , Critic(mick, snakesOnAPlane, 4.0D)
    , Critic(mick, justMyLuck, 2.0D)
    , Critic(mick, supermanReturns, 3.0D)
    , Critic(mick, youMeAndDupree, 2.0D)
    , Critic(mick, theNightListener, 3.0D)

    , Critic(jack, ladyInTheWater, 3.0D)
    , Critic(jack, snakesOnAPlane, 4.0D)
    , Critic(jack, supermanReturns, 5.0D)
    , Critic(jack, youMeAndDupree, 3.5D)
    , Critic(jack, theNightListener, 3.0D)

    , Critic(toby, snakesOnAPlane, 4.5D)
    , Critic(toby, supermanReturns, 4.0D)
    , Critic(toby, youMeAndDupree, 1.0D)

  )

}