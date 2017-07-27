package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a Greek Hoplites unit.
 *
 * @version 1.0
 * @author Cliff Panos
 */
class HoplitesUnit extends RangedUnit {

    /**
     * Public constructor.
     *
     * @param owner the owner of this unit.
     */
    public HoplitesUnit(Civilization owner) {
        super(owner);
        this.setBaseEndurance((int) (this.getBaseEndurance() * 2.5));
        this.regenerateEndurance();
    }

    @Override
    public char symbol() {
        return 'H';
    }


    @Override
    public String toString() {
        return "Hoplites Unit. " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/hoplites_unit_icon.PNG");
    }
}
