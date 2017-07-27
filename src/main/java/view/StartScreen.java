package view;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {

    private Button startButton;
    private ListView<CivEnum> civEnum;

    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {

        startButton = new Button("START");
        startButton.setFont(Font.font("Century Gothic", 16));
        startButton.setTextFill(Color.web("#089C18"));
        startButton.setPadding(new Insets(5));
        startButton.setMinWidth(230);
        ObservableList<CivEnum> listOfCivEnum = FXCollections
            .observableArrayList(CivEnum.ANCIENT_EGYPT, CivEnum.QIN_DYNASTY,
            CivEnum.ROMAN_EMPIRE, CivEnum.ANCIENT_GREECE,
            CivEnum.VENETIAN_FEDERATION, CivEnum.INCAN_EMPIRE);
        civEnum = new ListView<>(listOfCivEnum);


        Label selectLabel = new Label("Select a Civilization to Begin");
        selectLabel.setTextFill(Color.web("#FF0000"));
        selectLabel.setFont(Font.font("Century Gothic", 16));

        ImageView imageView = new ImageView();
        imageView.setImage(new Image(this.getClass().getResource(
            "civ_background.png").toExternalForm()));

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setTranslateY(150);
        vBox.setMaxHeight(216);
        vBox.setMaxWidth(230);
        vBox.getChildren().addAll(selectLabel, civEnum, startButton);
        this.getChildren().addAll(imageView, vBox);

    }

    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return startButton;
    }

    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        return civEnum;
    }

}
