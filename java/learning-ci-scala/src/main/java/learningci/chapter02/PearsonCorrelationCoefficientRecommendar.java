package learningci.chapter02;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PearsonCorrelationCoefficientRecommendar extends AbstractRecommendar implements Recommender {

    public Double getSimilarity(Person person1, Person person2) {
        Map<String, Double> critics1 = critics.get(person1.name);
        Map<String, Double> critics2 = critics.get(person2.name);
        Set<String> bothContains = new HashSet<String>();
        for (String title1 : critics1.keySet()) {
            for (String title2 : critics2.keySet()) {
                if (title1.equals(title2)) {
                    if (critics1.get(title1) != null && critics2.get(title2) != null) {
                        bothContains.add(title1);
                    }
                    break;
                }
            }
        }
        Double sumOfCriticsByPerson1 = 0.0D;
        Double sumOfCriticsByPerson2 = 0.0D;
        for (String key : bothContains) {
            sumOfCriticsByPerson1 += critics1.get(key);
            sumOfCriticsByPerson2 += critics2.get(key);
        }
        Double sumOfPowedCriticsByPerson1 = 0.0D;
        Double sumOfPowedCriticsByPerson2 = 0.0D;
        for (String key : bothContains) {
            sumOfPowedCriticsByPerson1 += Math.pow(critics1.get(key), 2);
            sumOfPowedCriticsByPerson2 += Math.pow(critics2.get(key), 2);
        }
        Double sumOfProducts = 0.0D;
        for (String key : bothContains) {
            sumOfProducts += critics1.get(key) * critics2.get(key);
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
