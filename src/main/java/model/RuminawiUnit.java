package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents an Incan Ruminawi unit.
 *
 * @author Cliff Panos
 * @version 1.0
 */
class RuminawiUnit extends MeleeUnit {

    /**
     * Public constructor
     *
     * @param owner The owner of this unit.
     */
    public RuminawiUnit(Civilization owner) {
        super(owner);
        this.setDamage((int) (this.getDamage() * 0.8));
    }

    @Override
    public void battle(MapObject o) {
        o.damage(this.getDamage());
        if (!o.isDestroyed() && o instanceof MeleeUnit) {
            damage(((MilitaryUnit) o).getDamage());
        }
    }

    @Override
    public char symbol() {
        return 'R';
    }

    @Override
    public String toString() {
        return "Ruminawi. " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/ruminawi_icon.PNG");
    }

}
