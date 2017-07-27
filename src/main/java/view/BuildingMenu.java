package view;

import controller.GameController;
import model.Building;
import model.Settlement;

import javafx.scene.control.Button;
import javafx.scene.control.Alert;


/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {

    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {

        Button invest = new Button("Invest");
        addMenuItem(invest);
        invest.setOnMouseClicked(e -> {

                Building building = (Building) GameController.getLastClicked()
                    .getTile().getOccupant();
                int coins = building.getOwner().getTreasury().getCoins();

                if (coins >= 25) {
                    building.invest();
                    building.getOwner().getTreasury().spend(25);
                    SoundEffect.play("chaChing.mp3");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("This building was not able to invest;"
                        + "\nyou have " + coins + " gold but need 25.");
                    alert.setTitle("Could not invest!");
                    alert.showAndWait();
                }
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });


        Button demolish = new Button("Demolish");
        addMenuItem(demolish);
        demolish.setOnMouseClicked(e -> {

                if (GameController.getLastClicked().getTile()
                        .getOccupant() instanceof Settlement) {

                    if (GameController.getCivilization()
                        .getNumSettlements() > 1) {

                        ((Building) GameController.getLastClicked().getTile()
                            .getOccupant()).demolish();
                        SoundEffect.play("demolish.mp3");
                        GameController.getLastClicked().getTile().getOccupant()
                            .getOwner().getSettlements().remove(
                                (Settlement) GameController.getLastClicked()
                                    .getTile().getOccupant());
                        GameController.getLastClicked().getTile()
                            .getOccupant().getOwner().decrementNumSettlements();
                        GameController.getLastClicked().getTile()
                            .setOccupant(null);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(
                            "You only have one Settlement at the moment;"
                            + "\nyou must always have at least one "
                            + "Settlement.");
                        alert.setTitle("Could Not Demolish!");
                        alert.showAndWait();
                    }

                } else {
                    ((Building) GameController.getLastClicked().getTile()
                        .getOccupant()).demolish();
                    GameController.getLastClicked().getTile()
                        .setOccupant(null);
                    SoundEffect.play("demolish.mp3");

                }
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
                GameScreen.switchMenu(GameController.GameState.NEUTRAL);

            });

    }


}
