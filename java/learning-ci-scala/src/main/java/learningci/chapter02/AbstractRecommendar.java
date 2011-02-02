package learningci.chapter02;

import learningci.chapter02.data.*;
import java.util.*;

public abstract class AbstractRecommendar implements Recommender {

    protected Map<Person, Map<Movie, Double>> data;

    public void loadData(Map<Person, Map<Movie, Double>> data) {
        this.data = data;
    }

    public List<Tuple<Person, Double>> getSimilarPersons(Person self, int maxCount) {
        List<Tuple<Person, Double>> dest = new ArrayList<Tuple<Person, Double>>();
        Set<Person> persons = data.keySet();
        for (Person person : persons) {
            if (self.equals(person)) {
                continue;
            }
            Double simularity = getSimilarity(self, person);
            Tuple<Person, Double> result = new Tuple<Person, Double>(person, simularity);
            dest.add(result);
        }
        Collections.sort(dest, new Comparator<Tuple<Person, Double>>() {
            public int compare(Tuple<Person, Double> o1, Tuple<Person, Double> o2) {
                return o1.right > o2.right ? 0 : 1;
            }
        });
        return new ArrayList<Tuple<Person, Double>>(dest.subList(0, maxCount));
    }

    public List<Tuple<Movie, Double>> getRecommendations(Person self) {

        List<Tuple<Movie, Double>> dest = new ArrayList<Tuple<Movie, Double>>();
        Map<Movie, Double> weighteddata = new HashMap<Movie, Double>();
        Map<Movie, Double> sumsOfSimilarity = new HashMap<Movie, Double>();

        Map<Movie, Double> dataBySelf = data.get(self);
        Set<Person> persons = data.keySet();
        for (Person person : persons) {
            if (self.equals(person)) {
                continue;
            }
            Double simularity = getSimilarity(self, person);
            if (simularity <= 0) {
                continue;
            }
            Map<Movie, Double> dataByOther = data.get(person);
            for (Movie movie : dataByOther.keySet()) {
                if (dataBySelf.get(movie) == null && dataByOther.get(movie) != null) {
                    if (weighteddata.get(movie) == null) {
                        weighteddata.put(movie, 0.0D);
                    }
                    weighteddata.put(movie,
                            weighteddata.get(movie) + dataByOther.get(movie) * simularity);
                    if (sumsOfSimilarity.get(movie) == null) {
                        sumsOfSimilarity.put(movie, 0.0D);
                    }
                    sumsOfSimilarity.put(movie, sumsOfSimilarity.get(movie) + simularity);
                }
            }
        }
        for (Movie movie : weighteddata.keySet()) {
            Tuple<Movie, Double> result = new Tuple(movie, weighteddata.get(movie) / sumsOfSimilarity.get(movie));
            dest.add(result);
        }
        Collections.sort(dest, new Comparator<Tuple<Movie, Double>>() {
            public int compare(Tuple<Movie, Double> o1, Tuple<Movie, Double> o2) {
                return o1.right > o2.right ? 0 : 1;
            }
        });
        return dest;
    }

}
