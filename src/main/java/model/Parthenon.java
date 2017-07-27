package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a Greek Parthenon that can improve writing and happiness.
 *
 * @version 1.0
 * @author Cliff Panos
 */
class Parthenon extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public Parthenon(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getTechnology().improveWriting();
        getOwner().increaseHappiness(20);
    }

    @Override
    public String toString() {
        return "Parthenon " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/parthenon_icon.PNG");
    }
}
