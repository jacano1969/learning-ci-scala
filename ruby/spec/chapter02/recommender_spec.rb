#!/usr/bin/ruby

require File.dirname(__FILE__) + '/../../lib/chapter02/recommender.rb'
require 'rspec'

describe Chapter02::EuclideanDistanceRecommender, "EuclideanDistanceRecommender" do

  before do
    @recommender = Chapter02::EuclideanDistanceRecommender.new
    @all_critics = Chapter02::Critics::All
    @toby = Chapter02::Person::Toby
  end

  it "should not be nil" do
    @recommender.should_not nil
  end

  it "#get_similar_persons(critics,person,max_count) should return expected value" do
    recommended_persons = @recommender.get_similar_persons(@all_critics, @toby, 3)
    recommended_persons[0].person.should equal Chapter02::Person::MickLaSalle
    recommended_persons[1].person.should equal Chapter02::Person::MichaelPhillips
    recommended_persons[2].person.should equal Chapter02::Person::ClaudiaPuig
    recommended_persons[0].similarity.should be_within(0.3076923076923077).of(0.3076923076923078)
    recommended_persons[1].similarity.should be_within(0.2857142857142857).of(0.2857142857142858)
    recommended_persons[2].similarity.should be_within(0.2352941176470588).of(0.2352941176470589)
  end

  it "#get_recommendations(critics,person) should return expected value" do
    recommendations = @recommender.get_recommendations(@all_critics, @toby)
    recommendations[0].movie.should equal Chapter02::Movie::TheNightListener
    recommendations[1].movie.should equal Chapter02::Movie::LadyInTheWater
    recommendations[2].movie.should equal Chapter02::Movie::JustMyLuck
    recommendations[0].similarity.should be_within(3.3883502969520225).of(3.3883502969520226)
    recommendations[1].similarity.should be_within(2.7561242939959363).of(2.7561242939959364)
    recommendations[2].similarity.should be_within(2.461988486074374).of(2.461988486074375)
  end

  after do
    @recommender = nil
  end

end

describe Chapter02::PearsonCorrelationCoefficientRecommender, "PearsonCorrelationCoefficientRecommender" do

  before do
    @recommender = Chapter02::PearsonCorrelationCoefficientRecommender.new
    @all_critics = Chapter02::Critics::All
    @toby = Chapter02::Person::Toby
  end

  it "should not be nil" do
    @recommender.should_not nil
  end

  it "#get_similar_persons(critics,person,max_count) should return expected value" do
    recommended_persons = @recommender.get_similar_persons(@all_critics, @toby, 3)
    recommended_persons[0].person.should equal Chapter02::Person::MickLaSalle
    recommended_persons[1].person.should equal Chapter02::Person::ClaudiaPuig
    recommended_persons[2].person.should equal Chapter02::Person::LisaRose
    recommended_persons[0].similarity.should be_within(0.1154752909313282).of(0.1154752909313283)
    recommended_persons[1].similarity.should be_within(0.07594208041337897).of(0.07594208041337898)
    recommended_persons[2].similarity.should be_within(0.06682766880808848).of(0.06682766880808849)
  end

  it "#get_recommendations(critics,person) should return expected value" do
    recommendations = @recommender.get_recommendations(@all_critics, @toby)
    recommendations[0].movie.should equal Chapter02::Movie::TheNightListener
    recommendations[1].movie.should equal Chapter02::Movie::LadyInTheWater
    recommendations[2].movie.should equal Chapter02::Movie::JustMyLuck
    recommendations[0].similarity.should be_within(3.338175971394646).of(3.338175971394647)
    recommendations[1].similarity.should be_within(2.8798748976946).of(2.8798748976947)
    recommendations[2].similarity.should be_within(2.4413324446514006).of(2.4413324446514007)
  end

  after do
    @recommender = nil
  end

end

