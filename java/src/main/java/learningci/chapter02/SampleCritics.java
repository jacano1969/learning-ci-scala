package learningci.chapter02;

import java.util.HashMap;
import java.util.Map;

public class SampleCritics {

    public static Map<String,Map<String,Double>> critics = new HashMap<String, Map<String,Double>>();

    public static final String LisaRose = "Lisa Rose";
    public static final String GeneSeymour = "Gene Seymour";
    public static final String MichaelPhillips = "Michael Phillips";
    public static final String ClaudiaPuig = "Claudia Puig";
    public static final String MickLaSalle = "Mick LaSalle";
    public static final String JackMatthews = "Jack Matthews";
    public static final String Toby = "Toby";

    public static final String LadyInTheWater = "Lady in the Water";
    public static final String SnakesOnAPlane = "Snakes on a Plane";
    public static final String JustMyLuck = "Just My Luck";
    public static final String SupermanReturns = "Superman Returns";
    public static final String YouMeAndDupree = "You, Me and Dupree";
    public static final String TheNightListener = "The Night Listener";

    static {

        Map<String,Double> lisa = new HashMap<String,Double>();
        lisa.put(LadyInTheWater,2.5D);
        lisa.put(SnakesOnAPlane, 3.5D);
        lisa.put(JustMyLuck,3.0D);
        lisa.put(SupermanReturns, 3.5D);
        lisa.put(YouMeAndDupree, 2.5D);
        lisa.put(TheNightListener, 3.0D);
        critics.put(LisaRose,lisa);

        Map<String,Double> gene = new HashMap<String,Double>();
        gene.put(LadyInTheWater,3.0D);
        gene.put(SnakesOnAPlane, 3.5D);
        gene.put(JustMyLuck,1.5D);
        gene.put(SupermanReturns, 5.0D);
        gene.put(YouMeAndDupree, 3.5D);
        gene.put(TheNightListener, 3.0D);
        critics.put(GeneSeymour,gene);

        Map<String,Double> michael = new HashMap<String,Double>();
        michael.put(LadyInTheWater,2.5D);
        michael.put(SnakesOnAPlane, 3.0D);
        michael.put(JustMyLuck,null);
        michael.put(SupermanReturns, 3.5D);
        michael.put(YouMeAndDupree, null);
        michael.put(TheNightListener, 3.5D);
        critics.put(MichaelPhillips,michael);

        Map<String,Double> claudia = new HashMap<String,Double>();
        claudia.put(LadyInTheWater,null);
        claudia.put(SnakesOnAPlane, 3.5D);
        claudia.put(JustMyLuck,3.0D);
        claudia.put(SupermanReturns, 4.0D);
        claudia.put(YouMeAndDupree, 2.5D);
        claudia.put(TheNightListener, 4.5D);
        critics.put(ClaudiaPuig,claudia);

        Map<String,Double> mick = new HashMap<String,Double>();
        mick.put(LadyInTheWater,3.0D);
        mick.put(SnakesOnAPlane, 4.0D);
        mick.put(JustMyLuck,2.0D);
        mick.put(SupermanReturns, 3.0D);
        mick.put(YouMeAndDupree, 2.0D);
        mick.put(TheNightListener, 3.0D);
        critics.put(MickLaSalle,mick);

        Map<String,Double> jack = new HashMap<String,Double>();
        jack.put(LadyInTheWater,3.0D);
        jack.put(SnakesOnAPlane, 4.0D);
        jack.put(JustMyLuck,null);
        jack.put(SupermanReturns, 5.0D);
        jack.put(YouMeAndDupree, 3.5D);
        jack.put(TheNightListener, 3.0D);
        critics.put(JackMatthews,jack);

        Map<String,Double> toby = new HashMap<String,Double>();
        toby.put(LadyInTheWater,null);
        toby.put(SnakesOnAPlane, 4.5D);
        toby.put(JustMyLuck,null);
        toby.put(SupermanReturns, 4.0D);
        toby.put(YouMeAndDupree, 1.0D);
        toby.put(TheNightListener, null);
        critics.put(Toby,toby);

    }

    public static Map<String,Map<String,Double>> get() {
            return critics;
    }

}
