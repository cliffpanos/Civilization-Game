package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a settlement that produces philosophy and gold.
 *
 * @version 2.0
 * @author Angie Palm, Jim Harris
 */
public class Settlement extends Building {
    private String name;
    private int numBuildings;

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     * @param name the name of the Settlement.
     */
    public Settlement(Civilization owner, String name) {
        super(200, owner, 10, 0, 0, 0, 0, 10);
        this.name = name;
        numBuildings = 0;
        owner.getSettlements().add(this);
    }

    @Override
    public void invest() {
        setPhilosophyGeneration(getPhilosophyGeneration() + 5);
        numBuildings++;
        setGoldGeneration(getGoldGeneration() + 5);
    }

    @Override
    public char symbol() {
        return '$';
    }

    public String getName() {
        return name;
    }

    public int getNumBuildings() {
        return numBuildings;
    }

    @Override
    public String toString() {
        return name + " Settlement. " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/settlement_icon.PNG");
    }
}
