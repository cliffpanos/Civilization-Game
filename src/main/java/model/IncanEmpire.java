package model;

/**
 * Represents the Incan Empire civilization.
 *
 * @version 2.0
 * @author Cliff Panos
 */
public class IncanEmpire extends Civilization {

    private Hills hills = new Hills();

    /**
     * Public constructor.
     */
    public IncanEmpire() {
        super("Incan Empire");
    }

    @Override
    public String explore() {
        int resources = hills.mineCoal();
        produceResources(resources);
        return "You go mining and get " + resources + " resources!";
    }

    /**
     * @return the Hills for this Civilization.
     */
    public Hills getHills() {
        return hills;
    }


    //THE BELOW UNIT AND LANDMARK ARE DIFFERENT FROM OTHER civilizations

    @Override
    public MeleeUnit getMeleeUnit() {
        return new RuminawiUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new MachuPicchu(this);
    }

}
