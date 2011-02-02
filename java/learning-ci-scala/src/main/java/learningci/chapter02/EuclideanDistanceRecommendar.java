package learningci.chapter02;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import learningci.chapter02.data.*;

public class EuclideanDistanceRecommendar extends AbstractRecommendar implements Recommender {

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
        Integer bothContainsLen = bothContains.size();
        if (bothContainsLen == 0) {
            return 0.0D;
        }
        Double sumOfValues = 0.0D;
        for (Movie movie : bothContains) {
            sumOfValues += Math.pow(critics1.get(movie) - critics2.get(movie), 2);
        }
        return 1 / (1 + sumOfValues);
    }

}
