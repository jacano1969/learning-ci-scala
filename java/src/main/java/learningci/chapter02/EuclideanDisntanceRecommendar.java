package learningci.chapter02;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EuclideanDisntanceRecommendar extends AbstractRecommendar implements Recommender {

    public Double getSimilarity(Person person1, Person person2) {
        Map<String, Double> critics1 = critics.get(person1.name);
        Map<String, Double> critics2 = critics.get(person2.name);
        Set<String> bothContains = new TreeSet<String>();
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
        Integer bothContainsLen = bothContains.size();
        if (bothContainsLen == 0) {
            return 0.0D;
        }
        Double sumOfValues = 0.0D;
        for (String title : bothContains) {
            sumOfValues += Math.pow(critics1.get(title) - critics2.get(title), 2);
        }
        return 1 / (1 + sumOfValues);
    }

}
