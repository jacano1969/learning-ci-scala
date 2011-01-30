package learningci.chapter02;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PearsonCorrelationCoefficientRecommendarTest {

    @Test
    public void type() throws Exception {
        assertNotNull(PearsonCorrelationCoefficientRecommendar.class);
    }

    @Test
    public void instantiation() throws Exception {
        Recommender target = new PearsonCorrelationCoefficientRecommendar();
        assertNotNull(target);
    }

    @Test
    public void getSimilarity_A$Person$Person() throws Exception {
        Recommender recommender = new PearsonCorrelationCoefficientRecommendar();
        recommender.loadData(SampleCritics.get());
        List<Tuple<Person, Double>> persons = recommender.getSimilarPersons(new Person(SampleCritics.Toby), 3);
        for (Tuple<Person, Double> tuple : persons) {
            System.out.println("Pearson correlation coefficient : " + tuple.left.name + " -> " + tuple.right);
        }
        assertEquals(SampleCritics.MickLaSalle, persons.get(0).left.name);
        assertEquals(SampleCritics.ClaudiaPuig, persons.get(1).left.name);
        assertEquals(SampleCritics.LisaRose, persons.get(2).left.name);
        assertEquals(new Double("0.1154752909313282"), persons.get(0).right);
        assertEquals(new Double("0.07594208041337897"), persons.get(1).right);
        assertEquals(new Double("0.06682766880808848"), persons.get(2).right);
    }

    @Test
    public void getRecommendations_A$Person() throws Exception {
        Recommender recommender = new PearsonCorrelationCoefficientRecommendar();
        recommender.loadData(SampleCritics.get());
        List<Tuple<String, Double>> resultList = recommender.getRecommendations(new Person(SampleCritics.Toby));
        for (Tuple<String, Double> tuple : resultList) {
            System.out.println("Pearson correlation coefficient : " + tuple.left + " -> " + tuple.right);
        }
        assertEquals(SampleCritics.TheNightListener, resultList.get(0).left);
        assertEquals(SampleCritics.LadyInTheWater, resultList.get(1).left);
        assertEquals(SampleCritics.JustMyLuck, resultList.get(2).left);
        assertEquals(new Double("3.341293144620487"), resultList.get(0).right);
        assertEquals(new Double("2.870402144824802"), resultList.get(1).right);
        assertEquals(new Double("2.4413324446514006"), resultList.get(2).right);
    }

}
