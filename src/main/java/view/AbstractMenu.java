package view;

import runner.CivilizationGame;
import controller.GameController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Created by RuYiMarone on 11/12/2016.
 */
public class AbstractMenu {

    public static final int PREFWIDTH = 145;
    private Button exploreButton = new Button("Explore");
    private Button endTurnButton = new Button("End Turn");
    private Label civLabel = new Label("Civilization");
    private Text civName = new Text(GameController.getCivilization().getName());
    private Label terrain = new Label();
    private Label l = new Label("------------");
    private Label s = new Label("------------");
    private Text unitStatus = new Text();
    private Text space = new Text(" ");
    private VBox duoMenu = new VBox(5);
    private VBox menu = new VBox(10, civLabel, civName, l, terrain, s,
        unitStatus, space);

    public AbstractMenu() {

        duoMenu.setBackground(new Background(new BackgroundImage(
            ImageResource.get("ice.jpg"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, new BackgroundSize(duoMenu.getWidth(),
            duoMenu.getHeight(), false, false, true, false))));

        menu.setPrefWidth(PREFWIDTH);
        menu.setPrefHeight(CivilizationGame.getStartScreen().getHeight() - 68);

        duoMenu.setPadding(new Insets(12));
        duoMenu.setAlignment(Pos.TOP_CENTER);
        menu.setAlignment(Pos.TOP_CENTER);

        Button infoButton = new Button("How To Play");
        infoButton.setFont(new Font("Helvetica", 16));
        infoButton.setTextFill(Color.web("#0A59AD"));
        infoButton.setPrefWidth(220);

        infoButton.setOnMouseClicked(e -> {
                Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
                newAlert.setTitle("How To Play Civilization");
                newAlert.setHeaderText(
                    "In order to get the most out of this game:\n"
                    + "- Click on tiles near your Settlement to recruit units\n"
                    + "* Click on labels in the Resources Menu to learn more\n"
                    + "- Turn your sound on to hear sound effects\n"
                    + "- Convert convertable units into buildings\n"
                    + "- Invest in buildings, and attack enemies\n"
                    + "* Check out the water tiles which are gifs & not jpegs\n"
                    + "* Gain Strategy Points and Technology Points to win!");
                SoundEffect.play("start.mp3");
                newAlert.showAndWait();
            });

        duoMenu.getChildren().addAll(menu, infoButton);

        addMenuItem(exploreButton);
        addMenuItem(endTurnButton);

        unitStatus.setWrappingWidth(130);
        unitStatus.setFont(new Font("Times", 14));
        unitStatus.setLineSpacing(5.5);
        unitStatus.setTextAlignment(TextAlignment.CENTER);
        l.setFont(new Font("Times", 20));
        l.setTextFill(Color.web("#8E0C3A"));
        s.setFont(new Font("Times", 20));
        s.setTextFill(Color.web("#674551"));
        terrain.setFont(new Font("Times", 20));
        terrain.setTextFill(Color.web("#077E05"));
        civLabel.setFont(new Font("Times", 22));
        civLabel.setTextFill(Color.web("#12398A"));
        civLabel.setTextAlignment(TextAlignment.CENTER);
        civName.setFont(new Font("Times", 20));
        civName.setWrappingWidth(120);
        civName.setTextAlignment(TextAlignment.CENTER);

        exploreButton.setOnMousePressed(e -> {
                GameController.getCivilization().explore();
                if (endTurn()) {
                    Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    newAlert.setHeaderText("Congratulations on your victory.");
                    newAlert.setTitle("You Won!");
                    SoundEffect.play("hornOfPlenty.m4a");
                    newAlert.showAndWait();
                    System.exit(0);
                }
            });

        endTurnButton.setOnAction(event -> {
                if (endTurn()) {
                    Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    newAlert.setHeaderText("Congratulations on your victory.");
                    newAlert.setTitle("You Won!");
                    SoundEffect.play("hornOfPlenty.m4a");
                    newAlert.showAndWait();
                    System.exit(0);
                }
            });

        duoMenu.setPrefWidth(PREFWIDTH);
        updateItems();
    }

    /**
     * This method updates the items and return the vbox as
     * the menu
     */
    public VBox getRootNode() {
        updateItems();
        return duoMenu;
    }
    /**
     * This method takes in a node and added the node as
     * a child of the vbox menu
     */
    public void addMenuItem(Node node) {
        if (node instanceof Button) {
            ((Button) node).setFont(new Font("Helvetica", 16));
            ((Button) node).setTextFill(Color.web("#0A59AD"));
            ((Button) node).setPrefWidth(220);
        }
        menu.getChildren().add(node);
    }
    /**
     * ends the player's turn and check for winning condition
     */
    public boolean endTurn() {
        GameController.setLastClicked(null);
        GameController.tick();
        GameController.ai();
        GridFX.update();
        GameController.updateResourcesBar();
        if (GameController.getCivilization().getNumSettlements() <= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Your last settlement has been destroyed!");
            alert.setTitle("Game Over");
            alert.showAndWait();
            System.exit(0);
        }
        return GameController.getCivilization()
                .getStrategy().conqueredTheWorld()
                || GameController.getCivilization()
                .getTechnology().hasTechnologyWin();
    }

    private void updateItems() {
        unitStatus.setText("");
        if (GameController.getLastClicked() != null) {
            terrain.setText(GameController.getLastClicked()
                    .getTile().getType().toString());
            if (!GameController.getLastClicked().getTile().isEmpty()) {
                unitStatus.setText(GameController.getLastClicked()
                        .getTile().getOccupant().getStatusString());
            }
        }
    }

}
