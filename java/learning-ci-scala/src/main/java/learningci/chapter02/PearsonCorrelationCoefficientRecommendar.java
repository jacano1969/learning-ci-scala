package learningci.chapter02;

import learningci.chapter02.data.Movie;
import learningci.chapter02.data.Person;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PearsonCorrelationCoefficientRecommendar extends AbstractRecommendar implements Recommender {

    public Double getSimilarity(Person person1, Person person2) {
        Map<Movie, Double> critics1 = data.get(person1);
        Map<Movie, Double> critics2 = data.get(person2);
        Set<Movie> bothContains = new HashSet<Movie>();
        for (Movie movie1 : critics1.keySet()) {
            for (Movie movie2 : critics2.keySet()) {
                if (movie1.equals(movie2)) {
                    if (critics1.get(movie1) != null && critics2.get(movie2) != null) {
                        bothContains.add(movie1);
                    }
                    break;
                }
            }
        }
        Double sumOfCriticsByPerson1 = 0.0D;
        Double sumOfCriticsByPerson2 = 0.0D;
        for (Movie movie : bothContains) {
            sumOfCriticsByPerson1 += critics1.get(movie);
            sumOfCriticsByPerson2 += critics2.get(movie);
        }
        Double sumOfPowedCriticsByPerson1 = 0.0D;
        Double sumOfPowedCriticsByPerson2 = 0.0D;
        for (Movie movie : bothContains) {
            sumOfPowedCriticsByPerson1 += Math.pow(critics1.get(movie), 2);
            sumOfPowedCriticsByPerson2 += Math.pow(critics2.get(movie), 2);
        }
        Double sumOfProducts = 0.0D;
        for (Movie movie : bothContains) {
            sumOfProducts += critics1.get(movie) * critics2.get(movie);
        }
        Integer bothContainsLen = bothContains.size();
        Double numerator = sumOfProducts - (sumOfCriticsByPerson1 * sumOfCriticsByPerson2 / bothContainsLen);
        Double denominator = Math.sqrt(
                (sumOfPowedCriticsByPerson1 - Math.pow(sumOfCriticsByPerson1, 2))
                        * (sumOfPowedCriticsByPerson2 - Math.pow(sumOfCriticsByPerson2, 2)
                ) / bothContainsLen);
        if (denominator == 0.0D) {
            return 0.0D;
        }
        return numerator / denominator;
    }

}
