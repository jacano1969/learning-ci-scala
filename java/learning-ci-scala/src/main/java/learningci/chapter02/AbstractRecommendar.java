package learningci.chapter02;

import java.util.*;

public abstract class AbstractRecommendar implements Recommender {

    protected Map<String, Map<String, Double>> critics;

    public void loadData(Map<String, Map<String, Double>> critics) {
        this.critics = critics;
    }

    public List<Tuple<Person, Double>> getSimilarPersons(Person self, int maxCount) {
        List<Tuple<Person, Double>> dest = new ArrayList<Tuple<Person, Double>>();
        Set<String> personNames = critics.keySet();
        for (String personName : personNames) {
            if (self.name.equals(personName)) {
                continue;
            }
            Double simularity = getSimilarity(self, new Person(personName));
            Tuple<Person, Double> result = new Tuple<Person, Double>(new Person(personName), simularity);
            dest.add(result);
        }
        Collections.sort(dest, new Comparator<Tuple<Person, Double>>() {
            public int compare(Tuple<Person, Double> o1, Tuple<Person, Double> o2) {
                return o1.right > o2.right ? 0 : 1;
            }
        });
        return new ArrayList<Tuple<Person, Double>>(dest.subList(0, maxCount));
    }

    public List<Tuple<String, Double>> getRecommendations(Person self) {

        List<Tuple<String, Double>> dest = new ArrayList<Tuple<String, Double>>();
        Map<String, Double> weightedCritics = new HashMap<String, Double>();
        Map<String, Double> sumsOfSimilarity = new HashMap<String, Double>();

        Map<String, Double> criticsBySelf = critics.get(self.name);
        Set<String> personNames = critics.keySet();
        for (String personName : personNames) {
            if (self.name.equals(personName)) {
                continue;
            }
            Double simularity = getSimilarity(self, new Person(personName));
            if (simularity <= 0) {
                continue;
            }
            Map<String, Double> criticsByOther = critics.get(personName);
            for (String title : criticsByOther.keySet()) {
                if (criticsBySelf.get(title) == null && criticsByOther.get(title) != null) {
                    if (weightedCritics.get(title) == null) {
                        weightedCritics.put(title, 0.0D);
                    }
                    weightedCritics.put(title,
                            weightedCritics.get(title) + criticsByOther.get(title) * simularity);
                    if (sumsOfSimilarity.get(title) == null) {
                        sumsOfSimilarity.put(title, 0.0D);
                    }
                    sumsOfSimilarity.put(title, sumsOfSimilarity.get(title) + simularity);
                }
            }
        }
        for (String title : weightedCritics.keySet()) {
            Tuple<String, Double> result = new Tuple(title, weightedCritics.get(title) / sumsOfSimilarity.get(title));
            dest.add(result);
        }
        Collections.sort(dest, new Comparator<Tuple<String, Double>>() {
            public int compare(Tuple<String, Double> o1, Tuple<String, Double> o2) {
                return o1.right > o2.right ? 0 : 1;
            }
        });
        return dest;
    }

}
