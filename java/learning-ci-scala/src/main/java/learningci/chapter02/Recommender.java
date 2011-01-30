package learningci.chapter02;

import java.util.List;
import java.util.Map;

public interface Recommender {

    void loadData(Map<String, Map<String, Double>> critics);

    Double getSimilarity(Person person1, Person person2);

    List<Tuple<Person, Double>> getSimilarPersons(Person self, int maxCount);

    List<Tuple<String, Double>> getRecommendations(Person self);

}
