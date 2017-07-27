package model;

import javafx.scene.image.Image;
import javafx.scene.control.Alert;

import view.ImageResource;

/**
 * Represents a Siege unit.
 *
 * @version 1.0
 * @author Jim Harris
 */
class SiegeUnit extends MilitaryUnit {

    /**
     * Public constructor.
     *
     * @param owner the owner of this unit.
     */
    public SiegeUnit(Civilization owner) {
        super(200, owner, 5, 10, 14, 5, 10, 60);
    }

    @Override
    public void attack(MapObject o) {
        getOwner().getStrategy().siege();
        battle(o);
    }

    @Override
    public void battle(MapObject o) {
        if (o instanceof Building) {
            o.damage(this.getDamage());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("This unit was not able to siege;\n"
                + "you can only siege a building.");
            alert.setTitle("Unit Could Not Siege!");
            alert.showAndWait();
        }
    }

    @Override
    public char symbol() {
        return 'S';
    }

    @Override
    public String toString() {
        return "Siege Unit. " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get(
                "Civ_Icon/siege_unit_icon.PNG");
    }
}
