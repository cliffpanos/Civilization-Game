package runner;

import controller.GameController;
import view.StartScreen;
import view.CivEnum;
import view.GameScreen;
import view.GridFX;
import view.SoundEffect;
import view.ImageResource;
import model.Map;
import model.QinDynasty;
import model.RomanEmpire;
import model.Egypt;
import model.Greece;
import model.VenetianFederation;
import model.IncanEmpire;
import model.Bandit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.Alert;

import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

/* javac -cp src/main/java src/main/java/runner/*.java
   src/main/java/model/*.java src/main/java/view/*.java
   src/main/java/controller/*.java */


/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {

    private static StartScreen startScreen = new StartScreen();
    private static Stage stage;
    private static boolean mapWasResized = false;

    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    public void start(Stage primaryStage) {

        stage = primaryStage;
        Scene startScene = new Scene(startScreen);

        stage.getIcons().add(ImageResource.get("civIcon.png"));
        stage.setTitle("Civilization: Brave New Implementation");

        stage.setScene(startScene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());


        stage.show();
        stage.toFront();
        stage.setResizable(false);

        SoundEffect.play("victory.mp3");
        startGame();
    }

    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */
    public void startGame() {
        stage.toFront();
        startScreen.getCivList().setOnMouseClicked(e -> {
                CivEnum civEnum = startScreen.getCivList()
                    .getSelectionModel().getSelectedItem();

                startScreen.getStartButton().setOnMouseClicked(ev -> {
                        TextInputDialog dialogBox =
                            new TextInputDialog("Town Name");
                        //default text in dialogBox
                        dialogBox.setTitle("A New Settlement");
                        dialogBox.setHeaderText("You have built a Settlement!");
                        dialogBox.setContentText("Enter the name of "
                            + "your first town:");

                        Optional<String> result = dialogBox.showAndWait();
                        result.ifPresent(name -> {

                                SoundEffect.play("start.mp3");

                                TextInputDialog dialog2 =
                                    new TextInputDialog("ex: 10 x 12");
                                dialog2.setTitle("A New Map");
                                dialog2.setHeaderText("Construct a new Map for "
                                    + "your Civilization!\nThis Map will be re"
                                    + "sized automatically to fit the screen.");
                                dialog2.setContentText("Enter the dimensions "
                                    + "for your\nnew Map in the form r x c:");
                                //The new Map will be of dimension m x n

                                dialog2.setX(100);
                                dialog2.setHeight(250);
                                dialog2.setWidth(300);
                                dialog2.setResizable(false);

                                Optional<String> result2 = dialog2
                                    .showAndWait();

                                result2.ifPresent(dim -> {

                                        int dimRow =
                                            processDimension(dim, "Row");
                                        int dimCol =
                                            processDimension(dim, "Col");
                                        //Ensure that the dimensions are ints
                                        //and that it is between 5 and 20

                                        GridFX.setMapDimensions(dimRow, dimCol);

                                        if (mapWasResized) {
                                            Alert alert = new Alert(
                                                Alert.AlertType.CONFIRMATION);
                                            alert.setHeaderText("The Game Map "
                                                + "was resized to a \n"
                                                + dimRow + " by " + dimCol
                                                + " map to fit your screen "
                                                + "size.");
                                            alert.setTitle("Map Resized");
                                            alert.showAndWait();
                                        }

                                        switch (civEnum) {
                                        case ANCIENT_EGYPT :
                                            GameController.setCivilization(
                                                new Egypt());
                                            break;
                                        case QIN_DYNASTY :
                                            GameController.setCivilization(
                                                new QinDynasty());
                                            break;
                                        case ROMAN_EMPIRE :
                                            GameController.setCivilization(
                                                new RomanEmpire());
                                            break;
                                        case ANCIENT_GREECE :
                                            GameController.setCivilization(
                                                new Greece());
                                            break;
                                        case VENETIAN_FEDERATION :
                                            GameController.setCivilization(
                                                new VenetianFederation());
                                            break;
                                        default :
                                        //Instead of INCAN_EMPIRE
                                        /* This was made from a case into a
                                           default statement to satisfy the
                                           default checkstyle requirement */
                                            GameController.setCivilization(
                                                new IncanEmpire());
                                            break;
                                        }

                                        Map.putSettlement(name, GameController
                                            .getCivilization(), 0,
                                            (dimCol - 1));

                                        Bandit bandit = new Bandit();
                                        GameController.setBandits(bandit);
                                        Map.addEnemies(bandit, (int)
                                            ((dimRow + dimCol) / 8));

                                        GameScreen gameScreen =
                                            new GameScreen();
                                        gameScreen.update();
                                        Scene gameScene = new Scene(gameScreen);
                                        stage.setScene(gameScene);
                                        SoundEffect.play("start.mp3");

                                    }); //End of Dimension Dialog Box

                            }); //End of Settlement Name Dialog Box

                    }); //End of Start Button

            }); //End of CivEnum ListView

    }

    public static StartScreen getStartScreen() {
        return startScreen;
    }

    public static Stage getStage() {
        return stage;
    }

    private int processDimension(String input, String rowOrCol) {

        String inputWOSpaces = "";
        for (int i = 0; i < input.length(); i++) {
            inputWOSpaces +=
                (input.charAt(i) == ' ' ? "" : input.charAt(i));
        }
        input = inputWOSpaces;

        int xAt = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'x') {
                xAt = i;
            }
        }
        //If the user did not enter input in the form r x c
        if (xAt == -1 || input.equals("ex:10x12")) {
            return (rowOrCol.equals("Row")) ? 10 : 12;
        }

        String rowString = input.substring(0, xAt);
        String colString = input.substring(xAt + 1, input.length());
        int dimRow = 10;
        int dimCol = 12;

        int maxPossibleRowDim = (int) ((startScreen.getHeight() - 70) / 71);
        int maxPossibleColDim = (int) ((startScreen.getWidth() - 150) / 71);

        try {
            dimRow = (int) Double.parseDouble(rowString);
        } catch (NumberFormatException e) {
            dimRow = 10;
            mapWasResized = true;
        } finally {
            mapWasResized = (dimRow < 5 ? true : mapWasResized);
            dimRow = (dimRow < 5 ? 5 : dimRow);
            mapWasResized = (dimRow > maxPossibleRowDim ? true : mapWasResized);
            dimRow = (dimRow > maxPossibleRowDim ? maxPossibleRowDim : dimRow);
        }
        try {
            dimCol = (int) Double.parseDouble(colString);
        } catch (NumberFormatException e) {
            dimCol = 12;
            mapWasResized = true;
        } finally {
            mapWasResized = (dimCol < 5 ? true : mapWasResized);
            dimCol = (dimCol < 5 ? 5 : dimCol);
            mapWasResized = (dimCol > maxPossibleColDim ? true : mapWasResized);
            dimCol = (dimCol > maxPossibleColDim ? maxPossibleColDim : dimCol);
        }
        return (rowOrCol.equals("Row")) ? dimRow : dimCol;
    }

}
