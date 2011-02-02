package learningci.chapter02.data;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Map<Person, Map<Movie, Double>> values = new HashMap<Person, Map<Movie, Double>>();

    public static final Person LisaRose = new Person("Lisa Rose");
    public static final Person GeneSeymour = new Person("Gene Seymour");
    public static final Person MichaelPhillips = new Person("Michael Phillips");
    public static final Person ClaudiaPuig = new Person("Claudia Puig");
    public static final Person MickLaSalle = new Person("Mick LaSalle");
    public static final Person JackMatthews = new Person("Jack Matthews");
    public static final Person Toby = new Person("Toby");

    public static final Movie LadyInTheWater = new Movie("Lady in the Water");
    public static final Movie SnakesOnAPlane = new Movie("Snakes on a Plane");
    public static final Movie JustMyLuck = new Movie("Just My Luck");
    public static final Movie SupermanReturns = new Movie("Superman Returns");
    public static final Movie YouMeAndDupree = new Movie("You, Me and Dupree");
    public static final Movie TheNightListener = new Movie("The Night Listener");

    static {

        Map<Movie, Double> lisa = new HashMap<Movie, Double>();
        lisa.put(LadyInTheWater, 2.5D);
        lisa.put(SnakesOnAPlane, 3.5D);
        lisa.put(JustMyLuck, 3.0D);
        lisa.put(SupermanReturns, 3.5D);
        lisa.put(YouMeAndDupree, 2.5D);
        lisa.put(TheNightListener, 3.0D);
        values.put(LisaRose, lisa);

        Map<Movie, Double> gene = new HashMap<Movie, Double>();
        gene.put(LadyInTheWater, 3.0D);
        gene.put(SnakesOnAPlane, 3.5D);
        gene.put(JustMyLuck, 1.5D);
        gene.put(SupermanReturns, 5.0D);
        gene.put(YouMeAndDupree, 3.5D);
        gene.put(TheNightListener, 3.0D);
        values.put(GeneSeymour, gene);

        Map<Movie, Double> michael = new HashMap<Movie, Double>();
        michael.put(LadyInTheWater, 2.5D);
        michael.put(SnakesOnAPlane, 3.0D);
        michael.put(JustMyLuck, null);
        michael.put(SupermanReturns, 3.5D);
        michael.put(YouMeAndDupree, null);
        michael.put(TheNightListener, 3.5D);
        values.put(MichaelPhillips, michael);

        Map<Movie, Double> claudia = new HashMap<Movie, Double>();
        claudia.put(LadyInTheWater, null);
        claudia.put(SnakesOnAPlane, 3.5D);
        claudia.put(JustMyLuck, 3.0D);
        claudia.put(SupermanReturns, 4.0D);
        claudia.put(YouMeAndDupree, 2.5D);
        claudia.put(TheNightListener, 4.5D);
        values.put(ClaudiaPuig, claudia);

        Map<Movie, Double> mick = new HashMap<Movie, Double>();
        mick.put(LadyInTheWater, 3.0D);
        mick.put(SnakesOnAPlane, 4.0D);
        mick.put(JustMyLuck, 2.0D);
        mick.put(SupermanReturns, 3.0D);
        mick.put(YouMeAndDupree, 2.0D);
        mick.put(TheNightListener, 3.0D);
        values.put(MickLaSalle, mick);

        Map<Movie, Double> jack = new HashMap<Movie, Double>();
        jack.put(LadyInTheWater, 3.0D);
        jack.put(SnakesOnAPlane, 4.0D);
        jack.put(JustMyLuck, null);
        jack.put(SupermanReturns, 5.0D);
        jack.put(YouMeAndDupree, 3.5D);
        jack.put(TheNightListener, 3.0D);
        values.put(JackMatthews, jack);

        Map<Movie, Double> toby = new HashMap<Movie, Double>();
        toby.put(LadyInTheWater, null);
        toby.put(SnakesOnAPlane, 4.5D);
        toby.put(JustMyLuck, null);
        toby.put(SupermanReturns, 4.0D);
        toby.put(YouMeAndDupree, 1.0D);
        toby.put(TheNightListener, null);
        values.put(Toby, toby);

    }

    public static Map<Person, Map<Movie, Double>> getAll() {
        return values;
    }

}
