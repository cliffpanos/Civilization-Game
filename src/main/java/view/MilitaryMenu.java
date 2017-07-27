package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {

    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {

        Button move = new Button("Move");
        Button attack = new Button("Attack");
        addMenuItem(move);
        addMenuItem(attack);

        attack.setOnMouseClicked(e -> {
                GameController.attacking();
                //Cannot attack message handled in GameController
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });

        move.setOnMouseClicked(e -> {
                GameController.moving();
                //Cannot move message handled in GameController
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });
    }
}
