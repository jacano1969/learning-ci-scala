package learningci.chapter02;

import learningci.chapter02.data.Movie;
import learningci.chapter02.data.Person;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PearsonCorrelationCoefficientRecommendar extends AbstractRecommendar implements Recommender {

    public Double getSimilarity(Person person1, Person person2) {
        Map<Movie, Double> criticsByPerson1 = data.get(person1);
        Map<Movie, Double> criticsByPerson2 = data.get(person2);
        Set<Movie> bothContains = new HashSet<Movie>();
        for (Movie movie1 : criticsByPerson1.keySet()) {
            for (Movie movie2 : criticsByPerson2.keySet()) {
                if (movie1.equals(movie2)) {
                    if (criticsByPerson1.get(movie1) != null && criticsByPerson2.get(movie2) != null) {
                        bothContains.add(movie1);
                    }
                    break;
                }
            }
        }
        Double sumOfRatingsByPerson1 = 0.0D;
        Double sumOfRatingsByPerson2 = 0.0D;
        for (Movie movie : bothContains) {
            sumOfRatingsByPerson1 += criticsByPerson1.get(movie);
            sumOfRatingsByPerson2 += criticsByPerson2.get(movie);
        }
        Double sumOfRatingSquaresByPerson1 = 0.0D;
        Double sumOfRatingSquaresByPerson2 = 0.0D;
        for (Movie movie : bothContains) {
            sumOfRatingSquaresByPerson1 += Math.pow(criticsByPerson1.get(movie), 2);
            sumOfRatingSquaresByPerson2 += Math.pow(criticsByPerson2.get(movie), 2);
        }
        Double sumOfProducts = 0.0D;
        for (Movie movie : bothContains) {
            sumOfProducts += criticsByPerson1.get(movie) * criticsByPerson2.get(movie);
        }
        Integer bothContainsCount = bothContains.size();
        Double numerator = sumOfProducts - (sumOfRatingsByPerson1 * sumOfRatingsByPerson2 / bothContainsCount);
        Double denominator = Math.sqrt(
                (sumOfRatingSquaresByPerson1 - Math.pow(sumOfRatingsByPerson1, 2))
                        * (sumOfRatingSquaresByPerson2 - Math.pow(sumOfRatingsByPerson2, 2)
                ) / bothContainsCount);
        return (denominator == 0.0D) ? 0.0D : numerator / denominator;
    }

}
