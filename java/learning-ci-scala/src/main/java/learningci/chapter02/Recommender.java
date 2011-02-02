package learningci.chapter02;

import java.util.List;
import java.util.Map;
import learningci.chapter02.data.*;
import learningci.chapter02.data.*;
import learningci.chapter02.data.*;

public interface Recommender {

    void loadData(Map<Person, Map<Movie, Double>> data);

    Double getSimilarity(Person person1, Person person2);

    List<Tuple<Person, Double>> getSimilarPersons(Person self, int maxCount);

    List<Tuple<Movie, Double>> getRecommendations(Person self);

}
