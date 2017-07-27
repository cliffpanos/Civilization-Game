package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a Venetian Mediterranean unit.
 *
 * @author Cliff Panos
 * @version 1.0
 */
class MediterraneanUnit extends SiegeUnit {

    /**
     * Public constructor
     *
     * @param owner The owner of this unit.
     */
    MediterraneanUnit(Civilization owner) {
        super(owner);
    }

    @Override
    public void battle(MapObject o) {
        o.damage(this.getDamage());
    }

    @Override
    public char symbol() {
        return 'M';
    }


    @Override
    public String toString() {
        return "Mediterranean Unit. " + super.toString();
    }
    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/mediterranean_unit_icon.PNG");
    }
}
