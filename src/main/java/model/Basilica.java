package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represnts a Basilica that can increase strategy, philosophy, and happiness
 *
 * @version 1.0
 * @author Cliff Panos
 */
class Basilica extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public Basilica(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getStrategy().battle();
        getOwner().getTechnology().philosophize();
        getOwner().increaseHappiness(25);
        getOwner().dockResources(25);
    }

    @Override
    public String toString() {
        return "Basilica " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/basilica_icon.PNG");
    }
}
