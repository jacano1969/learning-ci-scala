#!/usr/bin/env ruby

module Chapter02

  module NameHolder
    def initialize(name)
      @name = name
    end 
    attr_reader :name 
    def to_s() @name end
  end

  class Person 
    include NameHolder

    LisaRose        = Person.new('Lisa Rose')
    GeneSeymour     = Person.new('Gene Seymour')
    MichaelPhillips = Person.new('Michael Phillips')
    ClaudiaPuig     = Person.new('Claudia Puig')
    MickLaSalle     = Person.new('Mick LaSalle')
    JackMatthews    = Person.new('Jack Matthews')
    Toby            = Person.new('Toby')
  end

  class Movie 
    include NameHolder

    LadyInTheWater   = Movie.new('Lady in the Water')
    SnakesOnAPlane   = Movie.new('Snakes on a Plane')
    JustMyLuck       = Movie.new('Just My Luck')
    SupermanReturns  = Movie.new('Superman Returns')
    YouMeAndDupree   = Movie.new('You, Me and Dupree')
    TheNightListener = Movie.new('The Night Listener')
  end

  class Critic
    def initialize(person,movie,rating)
      @person = person
      @movie = movie
      @rating = rating
    end
    attr_reader :person, :movie, :rating
    def to_s() 
      "(" + @person.to_s + "," + @movie.to_s + "," + @rating.to_s + ")"
    end
  end

  module Critics
    All = Array.new([
      Critic.new(Person::LisaRose, Movie::LadyInTheWater,   2.5),
      Critic.new(Person::LisaRose, Movie::SnakesOnAPlane,   3.5),
      Critic.new(Person::LisaRose, Movie::JustMyLuck,       3.0),
      Critic.new(Person::LisaRose, Movie::SupermanReturns,  3.5),
      Critic.new(Person::LisaRose, Movie::YouMeAndDupree,   2.5),
      Critic.new(Person::LisaRose, Movie::TheNightListener, 3.0),
      Critic.new(Person::GeneSeymour, Movie::LadyInTheWater,   3.0),
      Critic.new(Person::GeneSeymour, Movie::SnakesOnAPlane,   3.5),
      Critic.new(Person::GeneSeymour, Movie::JustMyLuck,       1.5),
      Critic.new(Person::GeneSeymour, Movie::SupermanReturns,  5.0),
      Critic.new(Person::GeneSeymour, Movie::YouMeAndDupree,   3.5),
      Critic.new(Person::GeneSeymour, Movie::TheNightListener, 3.0),
      Critic.new(Person::MichaelPhillips, Movie::LadyInTheWater,   2.5),
      Critic.new(Person::MichaelPhillips, Movie::SnakesOnAPlane,   3.0),
      Critic.new(Person::MichaelPhillips, Movie::SupermanReturns,  3.5),
      Critic.new(Person::MichaelPhillips, Movie::TheNightListener, 3.5),
      Critic.new(Person::ClaudiaPuig, Movie::SnakesOnAPlane,   3.5),
      Critic.new(Person::ClaudiaPuig, Movie::JustMyLuck,       3.0),
      Critic.new(Person::ClaudiaPuig, Movie::SupermanReturns,  4.0),
      Critic.new(Person::ClaudiaPuig, Movie::YouMeAndDupree,   2.5),
      Critic.new(Person::ClaudiaPuig, Movie::TheNightListener, 4.5),
      Critic.new(Person::MickLaSalle, Movie::LadyInTheWater,   3.0),
      Critic.new(Person::MickLaSalle, Movie::SnakesOnAPlane,   4.0),
      Critic.new(Person::MickLaSalle, Movie::JustMyLuck,       2.0),
      Critic.new(Person::MickLaSalle, Movie::SupermanReturns,  3.0),
      Critic.new(Person::MickLaSalle, Movie::YouMeAndDupree,   2.0),
      Critic.new(Person::MickLaSalle, Movie::TheNightListener, 3.0),
      Critic.new(Person::JackMatthews, Movie::LadyInTheWater,   3.0),
      Critic.new(Person::JackMatthews, Movie::SnakesOnAPlane,   4.0),
      Critic.new(Person::JackMatthews, Movie::SupermanReturns,  5.0),
      Critic.new(Person::JackMatthews, Movie::YouMeAndDupree,   3.5),
      Critic.new(Person::JackMatthews, Movie::TheNightListener, 3.0),
      Critic.new(Person::Toby, Movie::SnakesOnAPlane,   4.5),
      Critic.new(Person::Toby, Movie::SupermanReturns,  4.0),
      Critic.new(Person::Toby, Movie::YouMeAndDupree,   1.0),
    ])
  end

end

