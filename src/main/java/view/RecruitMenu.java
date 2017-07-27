package view;

import controller.GameController;
import model.Unit;
import model.SettlerUnit;

import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.Alert;


/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {

    private final int preferredWidth = 228;

    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    * here
    */
    public RecruitMenu() {
        ObservableList<String> listOfRecruits = FXCollections
            .observableArrayList("Melee Unit", "Ranged Unit", "Hybrid Unit",
            "Siege Unit", "Settlers", "Farmers", "Coal Miners", "Anglers",
            "Master Builders");

        ListView<String> recruits = new ListView<>(listOfRecruits);
        recruits.setPrefHeight(preferredWidth);
        addMenuItem(recruits);
        Button select = new Button("RECRUIT");
        addMenuItem(select);

        recruits.setOnMouseClicked(e -> {

                String option = recruits.getSelectionModel().getSelectedItem();

                select.setOnMouseClicked(ev -> {

                        String soundName = "start.mp3";
                        Unit unit;

                        switch (option) {

                        case "Melee Unit" :
                            unit = GameController.getCivilization()
                                .getMeleeUnit();
                            soundName = "recruitUnit.mp3";
                            break;

                        case "Ranged Unit" :
                            unit = GameController.getCivilization()
                                .getRangedUnit();
                            soundName = "recruitUnit.mp3";
                            break;

                        case "Hybrid Unit" :
                            unit = GameController.getCivilization()
                                .getHybridUnit();
                            soundName = "recruitUnit.mp3";
                            break;

                        case "Siege Unit" :
                            unit = GameController.getCivilization()
                                .getSiegeUnit();
                            soundName = "recruitUnit.mp3";
                            break;

                        case "Settlers" :
                            unit = GameController.getCivilization()
                                .getSettlerUnit("");
                            if (unit.isAffordable()) {
                                TextInputDialog dialogBox =
                                    new TextInputDialog("New Town Name");
                                dialogBox.setTitle("A New Settlement");
                                dialogBox.setHeaderText("This Settler Unit"
                                    + " will build a new Settlement.");
                                dialogBox.setContentText("Enter the name of "
                                    + "the settlement to be constructed:");
                                Optional<String> result =
                                    dialogBox.showAndWait();
                                result.ifPresent(name -> {
                                        Unit settlers =
                                            GameController
                                            .getCivilization()
                                            .getSettlerUnit(name);
                                        GameController.getLastClicked()
                                            .getTile().setOccupant(settlers);
                                        settlers.applyInitialCosts();
                                        SoundEffect.play("settlers.mp3");
                                    });
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText("This unit was not able to "
                                    + "be recruited;\n"
                                    + "you do not have sufficient assets.");
                                alert.setTitle("Could Not Recruit!");
                                alert.showAndWait();
                            }
                            break;

                        case "Farmers" :
                            unit = GameController.getCivilization()
                                .getFarmerUnit();
                            soundName = "farmers.mp3";
                            break;

                        case "Coal Miners" :
                            unit = GameController.getCivilization()
                                .getCoalMinerUnit();
                            soundName = "coalMiners.mp3";
                            break;

                        case "Anglers" :
                            unit = GameController.getCivilization()
                                .getAnglerUnit();
                            soundName = "anglers.mp3";
                            break;

                        default : //Instead of "Master Builders"
                            unit = GameController.getCivilization()
                                .getMasterBuilderUnit();
                            soundName = "masterBuilders.mp3";
                            break;
                        }

                        if (unit.isAffordable()
                            && (!(unit instanceof SettlerUnit))) {
                            unit.applyInitialCosts();
                            GameController.getLastClicked().getTile()
                                .setOccupant(unit);
                            SoundEffect.play(soundName);
                        } else if (!(unit instanceof SettlerUnit)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("This unit was not able to "
                                + "be recruited;\n"
                                + "you do not have sufficient assets.");
                            alert.setTitle("Could Not Recruit!");
                            alert.showAndWait();
                        }

                        GameController.getLastClicked().updateTileView();
                        GameController.updateResourcesBar();
                        GameController.setLastClicked(GameController
                            .getLastClicked());
                        //Ensure that you cannot double-recruit

                    }); //End of Button setOnMouseClicked

            }); //End of ListView setOnMouseClicked

    }

}
