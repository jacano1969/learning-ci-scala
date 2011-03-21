#!/usr/bin/ruby

require File.dirname(__FILE__) + '/critics.rb'
require 'ostruct'

module Chapter02

  class RecommendedMovie
    def initialize(movie,similarity)
      @movie = movie
      @similarity = similarity
    end
    attr_reader :movie, :similarity
    def to_s() 
      "(" + @movie.to_s + "," + @similarity.to_s + ")"
    end
  end

  class RecommendedPerson
    def initialize(person, similarity)
      @person = person
      @similarity = similarity
    end
    attr_reader :person, :similarity
    def to_s() 
      "(" + @person.to_s + "," + @similarity.to_s + ")"
    end
  end

  module Recommender

    def get_similarity(critics, person1, person2) 
      raise NotImplementedError, "Recommender is not concrete class."
    end

    def get_similar_persons(critics, person_self, max_count) 
      persons_but_self = critics.select { |c| c.person != person_self }
                                .map { |c| c.person }.uniq
      persons_but_self.map { |person|
        RecommendedPerson.new(
          person, 
          get_similarity(critics,person_self,person)
        )
      }.sort { |r1,r2| 
        r1.similarity > r2.similarity ? -1 : 1
      }.slice(0,max_count)
    end

    def get_recommendations(critics, person_self)
      persons_but_self = critics.select { |c| c.person != person_self }
                                .map { |c| c.person }.uniq
      persons_with_similarity = persons_but_self.map { |person| 
        OpenStruct.new({
          :person => person,
          :similarity => get_similarity(critics, person_self, person)
        })
      }
      critics_by_self = critics.select { |c| c.person == person_self }
      weighted_critics = Hash.new
      weighted_critics.default = 0.0
      sums_of_similarity = Hash.new
      sums_of_similarity.default = 0.0
      critics.each { |c|
        is_rated_by_self = critics_by_self.select { |c_by_self| 
          c_by_self.movie == c.movie 
        }.size == 0
        if is_rated_by_self then
          person_with_similarity = persons_with_similarity.select { |p_and_s|
            p_and_s.person == c.person
          }
          similarity = person_with_similarity[0].similarity
          weighted_critics.store(c.movie, weighted_critics[c.movie] + c.rating * similarity)
          sums_of_similarity.store(c.movie, sums_of_similarity[c.movie] + similarity)
        end
      }
      recommendations = Array.new
      weighted_critics.each { |movie,value|
        denominator = sums_of_similarity[movie]
        if denominator > 0 then 
          recommendations << RecommendedMovie.new(movie, value/denominator)
        end
      }
      recommendations.sort { |r1,r2| r1.similarity > r2.similarity ? -1 : 1 }
    end

  end

  class EuclideanDistanceRecommender 
    include Recommender

    def get_similarity(critics, person1, person2)
      critics_by_p1 = critics.select { |c| c.person == person1 }
      critics_by_p2 = critics.select { |c| c.person == person2 }
      sum_of_values = 0.0
      critics_by_p2.map { |c2| 
        both_contains = critics_by_p1.select { |c1| c1.movie == c2.movie }
        if both_contains.size == 1 then
          (both_contains[0].rating - c2.rating) ** 2
        else
          0.0
        end
      }.each { |value| 
        sum_of_values += value
      }
      1 / (1 + sum_of_values)
    end

  end

  class PearsonCorrelationCoefficientRecommender
    include Recommender

    def get_similarity(critics, person1, person2)
      both_contained_count = 0
      sum_of_ratings_by_p1 = 0.0
      sum_of_ratings_by_p2 = 0.0
      sum_of_rating_squares_by_p1 = 0.0
      sum_of_rating_squares_by_p2 = 0.0
      sum_of_products = 0.0
      critics_by_p1 = critics.select { |c| c.person == person1 }
      critics_by_p2 = critics.select { |c| c.person == person2 }
      critics_by_p2.each { |c2|
        critics_by_p1.each { |c1|
          if c1.movie == c2.movie then
            both_contained_count += 1
            sum_of_ratings_by_p1 += c1.rating
            sum_of_ratings_by_p2 += c2.rating
            sum_of_rating_squares_by_p1 += c1.rating ** 2
            sum_of_rating_squares_by_p2 += c2.rating ** 2
            sum_of_products += c1.rating * c2.rating
          end
        }
      }
      numerator = sum_of_products - 
        (sum_of_ratings_by_p1 * sum_of_ratings_by_p2 / both_contained_count)
      denominator = Math::sqrt(
        (sum_of_rating_squares_by_p1 - sum_of_ratings_by_p1 ** 2) \
        * (sum_of_rating_squares_by_p2 - sum_of_ratings_by_p2 ** 2) \
        / both_contained_count
      )
      if denominator != 0.0 then
        numerator / denominator
      else 0.0
      end
    end

  end

end

