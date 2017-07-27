package model;

/**
 * Represents the Greece Civilization.
 * The Author of this code is one-quarter Greek. How about that.
 *
 * @version 2.0
 * @author Cliff Panos
 */
public class Greece extends Civilization {

    private Desert desert = new Desert();

    /**
     * Public constructor.
     */
    public Greece() {
        super("Ancient Greece");
    }

    @Override
    public String explore() {
        int gold = desert.findTreasure();
        getTreasury().earn(gold);
        return "You explore the desert and find " + gold + " gold!";
    }

    /**
     * @return this civilization's Desert.
     */
    public Desert getDesert() {
        return desert;
    }


    //THE BELOW UNIT AND LANDMARK ARE DIFFERENT FROM OTHER civilizations

    @Override
    public RangedUnit getRangedUnit() {
        return new HoplitesUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Parthenon(this);
    }
}
