package learningci.chapter02;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EuclideanDistanceRecommendarTest {

    @Test
    public void type() throws Exception {
        assertNotNull(EuclideanDistanceRecommendar.class);
    }

    @Test
    public void instantiation() throws Exception {
        Recommender target = new EuclideanDistanceRecommendar();
        assertNotNull(target);
    }

    @Test
    public void getSimilarity_A$Person$Person() throws Exception {
        Recommender recommender = new EuclideanDistanceRecommendar();
        recommender.loadData(SampleCritics.get());
        List<Tuple<Person, Double>> persons = recommender.getSimilarPersons(new Person(SampleCritics.Toby), 3);
        for (Tuple<Person, Double> tuple : persons) {
            System.out.println("Euclidean distance : " + tuple.left.name + " -> " + tuple.right);
        }
        assertEquals(SampleCritics.MickLaSalle, persons.get(0).left.name);
        assertEquals(SampleCritics.MichaelPhillips, persons.get(1).left.name);
        assertEquals(SampleCritics.ClaudiaPuig, persons.get(2).left.name);
        assertEquals(new Double("0.3076923076923077"), persons.get(0).right);
        assertEquals(new Double("0.2857142857142857"), persons.get(1).right);
        assertEquals(new Double("0.23529411764705882"), persons.get(2).right);
    }

    @Test
    public void getRecommendations_A$Person() throws Exception {
        Recommender recommender = new EuclideanDistanceRecommendar();
        recommender.loadData(SampleCritics.get());
        List<Tuple<String, Double>> resultList = recommender.getRecommendations(new Person(SampleCritics.Toby));
        for (Tuple<String, Double> tuple : resultList) {
            System.out.println("Euclidean distance : " + tuple.left + " -> " + tuple.right);
        }
        assertEquals(SampleCritics.TheNightListener, resultList.get(0).left);
        assertEquals(SampleCritics.LadyInTheWater, resultList.get(1).left);
        assertEquals(SampleCritics.JustMyLuck, resultList.get(2).left);
        assertEquals(new Double("3.3883502969520216"), resultList.get(0).right);
        assertEquals(new Double("2.7561242939959363"), resultList.get(1).right);
        assertEquals(new Double("2.461988486074374"), resultList.get(2).right);
    }

}
