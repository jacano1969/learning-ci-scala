package learningci.chapter02

object SampleData {

  val lisa = Person("Lisa Rose")
  val gene = Person("Gene Seymour")
  val michael = Person("Michael Phillips")
  val claudia = Person("Claudia Puig")
  val mick = Person("Mick LaSalle")
  val jack = Person("Jack Matthews")
  val toby = Person("Toby")

  val LadyInTheWater = "Lady in the Water"
  val SnakesOnAPlane = "Snakes on a Plane"
  val JustMyLuck = "Just My Luck"
  val SupermanReturns = "Superman Returns"
  val YouMeAndDupree = "You, Me and Dupree"
  val TheNightListener = "The Night Listener"

  val critics: List[Critic] = List(

    Critic(lisa, LadyInTheWater, 2.5D)
    , Critic(lisa, SnakesOnAPlane, 3.5D)
    , Critic(lisa, JustMyLuck, 3.0D)
    , Critic(lisa, SupermanReturns, 3.5D)
    , Critic(lisa, YouMeAndDupree, 2.5D)
    , Critic(lisa, TheNightListener, 3.0D)

    , Critic(gene, LadyInTheWater, 3.0D)
    , Critic(gene, SnakesOnAPlane, 3.5D)
    , Critic(gene, JustMyLuck, 1.5D)
    , Critic(gene, SupermanReturns, 5.0D)
    , Critic(gene, YouMeAndDupree, 3.5D)
    , Critic(gene, TheNightListener, 3.0D)

    , Critic(michael, LadyInTheWater, 2.5D)
    , Critic(michael, SnakesOnAPlane, 3.0D)
    , Critic(michael, SupermanReturns, 3.5D)
    , Critic(michael, TheNightListener, 3.5D)

    , Critic(claudia, SnakesOnAPlane, 3.5D)
    , Critic(claudia, JustMyLuck, 3.0D)
    , Critic(claudia, SupermanReturns, 4.0D)
    , Critic(claudia, YouMeAndDupree, 2.5D)
    , Critic(claudia, TheNightListener, 4.5D)

    , Critic(mick, LadyInTheWater, 3.0D)
    , Critic(mick, SnakesOnAPlane, 4.0D)
    , Critic(mick, JustMyLuck, 2.0D)
    , Critic(mick, SupermanReturns, 3.0D)
    , Critic(mick, YouMeAndDupree, 2.0D)
    , Critic(mick, TheNightListener, 3.0D)

    , Critic(jack, LadyInTheWater, 3.0D)
    , Critic(jack, SnakesOnAPlane, 4.0D)
    , Critic(jack, SupermanReturns, 5.0D)
    , Critic(jack, YouMeAndDupree, 3.5D)
    , Critic(jack, TheNightListener, 3.0D)

    , Critic(toby, SnakesOnAPlane, 4.5D)
    , Critic(toby, SupermanReturns, 4.0D)
    , Critic(toby, YouMeAndDupree, 1.0D)

  )

}