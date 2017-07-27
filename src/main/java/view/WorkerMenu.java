package view;

import controller.GameController;
import model.Convertable;
import model.TerrainTile;

import javafx.scene.control.Button;
import javafx.scene.control.Alert;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {

    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {

        Button move = new Button("Move");
        addMenuItem(move);
        move.setOnMouseClicked(e -> {
                GameController.moving();
                //Cannot move message handled in GameController
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });

        Button convert = new Button("Convert");
        addMenuItem(convert);
        convert.setOnMouseClicked(ev -> {

                TerrainTile tile = GameController.getLastClicked().getTile();

                Convertable selected = (Convertable) tile.getOccupant();

                if (selected.canConvert(tile.getType())) {
                    tile.setOccupant(selected.convert());
                    SoundEffect.play("build.mp3");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You cannot convert this unit on this "
                        + "tile type.");
                    alert.setTitle("Could Not Convert!");
                    alert.showAndWait();
                }

                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
                GameScreen.switchMenu(GameController.GameState.NEUTRAL);

            }); //End of Convert setOnMouseClicked

    }

}
