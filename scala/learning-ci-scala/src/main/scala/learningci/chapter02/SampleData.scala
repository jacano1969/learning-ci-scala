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

  val movie_LadyInTheWater = Movie(LadyInTheWater)
  val movie_SnakesOnAPlane = Movie(SnakesOnAPlane)
  val movie_JustMyLuck = Movie(JustMyLuck)
  val movie_SupermanReturns = Movie(SupermanReturns)
  val movie_YouMeAndDupree = Movie(YouMeAndDupree)
  val movie_TheNightListener = Movie(TheNightListener)

  val critics: List[Critic] = List(

    Critic(lisa, movie_LadyInTheWater, 2.5D)
    , Critic(lisa, movie_SnakesOnAPlane, 3.5D)
    , Critic(lisa, movie_JustMyLuck, 3.0D)
    , Critic(lisa, movie_SupermanReturns, 3.5D)
    , Critic(lisa, movie_YouMeAndDupree, 2.5D)
    , Critic(lisa, movie_TheNightListener, 3.0D)

    , Critic(gene, movie_LadyInTheWater, 3.0D)
    , Critic(gene, movie_SnakesOnAPlane, 3.5D)
    , Critic(gene, movie_JustMyLuck, 1.5D)
    , Critic(gene, movie_SupermanReturns, 5.0D)
    , Critic(gene, movie_YouMeAndDupree, 3.5D)
    , Critic(gene, movie_TheNightListener, 3.0D)

    , Critic(michael, movie_LadyInTheWater, 2.5D)
    , Critic(michael, movie_SnakesOnAPlane, 3.0D)
    , Critic(michael, movie_SupermanReturns, 3.5D)
    , Critic(michael, movie_TheNightListener, 3.5D)

    , Critic(claudia, movie_SnakesOnAPlane, 3.5D)
    , Critic(claudia, movie_JustMyLuck, 3.0D)
    , Critic(claudia, movie_SupermanReturns, 4.0D)
    , Critic(claudia, movie_YouMeAndDupree, 2.5D)
    , Critic(claudia, movie_TheNightListener, 4.5D)

    , Critic(mick, movie_LadyInTheWater, 3.0D)
    , Critic(mick, movie_SnakesOnAPlane, 4.0D)
    , Critic(mick, movie_JustMyLuck, 2.0D)
    , Critic(mick, movie_SupermanReturns, 3.0D)
    , Critic(mick, movie_YouMeAndDupree, 2.0D)
    , Critic(mick, movie_TheNightListener, 3.0D)

    , Critic(jack, movie_LadyInTheWater, 3.0D)
    , Critic(jack, movie_SnakesOnAPlane, 4.0D)
    , Critic(jack, movie_SupermanReturns, 5.0D)
    , Critic(jack, movie_YouMeAndDupree, 3.5D)
    , Critic(jack, movie_TheNightListener, 3.0D)

    , Critic(toby, movie_SnakesOnAPlane, 4.5D)
    , Critic(toby, movie_SupermanReturns, 4.0D)
    , Critic(toby, movie_YouMeAndDupree, 1.0D)

  )

}