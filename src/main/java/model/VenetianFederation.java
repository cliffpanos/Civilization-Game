package model;

/**
 * Represents the Venetian Federation Civiization.
 *
 * @version 2.0
 * @author Cliff Panos
 */
public class VenetianFederation extends Civilization {
    private Hills hills = new Hills();

    /**
     * Public constructor.
     */
    public VenetianFederation() {
        super("Venetian Federation");
    }

    @Override
    public String explore() {
        int food = hills.hunt().getHealth();
        hills.replenishGame();
        makeFood(food);
        return "You go hunting and get " + food + " food!";
    }

    /**
     * @return the Hills for this Civilization.
     */
    public Hills getHills() {
        return hills;
    }


    //THE BELOW UNIT AND LANDMARK ARE DIFFERENT FROM OTHER civilizations

    @Override
    public SiegeUnit getSiegeUnit() {
        return new MediterraneanUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Basilica(this);
    }
}
