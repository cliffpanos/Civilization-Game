package model;

import controller.TileType;
import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a Coal Miner unit that can build a coal mine.
 *
 * @author Jim Harris
 * @version 1.0
 */
public class CoalMinerUnit extends Unit implements Convertable {

    /**
     * Public constructor
     *
     * @param owner The owner of this unit.
     */
    public CoalMinerUnit(Civilization owner) {
        super(owner);
    }

    @Override
    public Building convert() {
        return getOwner().getCoalMine();
    }

    @Override
    public boolean canConvert(TileType type) {
        return type == TileType.MOUNTAIN;
    }

    @Override
    public char symbol() {
        return 'c';
    }

    @Override
    public String toString() {
        return "Coal miners. " + super.toString();
    }
    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/coal_miner_unit_icon.PNG");
    }
}
