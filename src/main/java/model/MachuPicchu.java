package model;

import javafx.scene.image.Image;
import view.ImageResource;

/**
 * Represents a Machu Picchu landmark that can increase food.
 *
 * @version 1.0
 * @author Cliff Panos
 */
class MachuPicchu extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public MachuPicchu(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().makeFood(40);
    }


    @Override
    public String toString() {
        return "Machu Picchu " + super.toString();
    }

    @Override
    public Image getImage() {
        return ImageResource.get("Civ_Icon/machu_picchu_icon.PNG");
    }
}
