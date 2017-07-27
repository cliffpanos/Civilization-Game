package view;

import controller.GameController;
import runner.CivilizationGame;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {

    private static ResourcesMenu resourcesMenu = new ResourcesMenu();
    private static BuildingMenu buildingMenu = new BuildingMenu();
    private static MilitaryMenu militaryMenu = new MilitaryMenu();
    private static RecruitMenu recruitMenu = new RecruitMenu();
    private static StatusMenu statusMenu = new StatusMenu();
    private static WorkerMenu workerMenu = new WorkerMenu();
    private static VBox menuBox = new VBox();

    /**
     * Creates a new view into the game. this layout should include
     * the resource bar, grid map, and action menus
     *
     */
    public GameScreen() {

        //Sets the Background of this BorderPane
        this.setBackground(new Background(new BackgroundImage(
            ImageResource.get("gameBackground.png"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, new BackgroundSize(this.getWidth(),
            this.getHeight(), false, false, false, true))));

        this.setTop(resourcesMenu.getRootNode());
        this.setMinHeight(CivilizationGame.getStartScreen().getHeight());
        this.setMinWidth(CivilizationGame.getStartScreen().getWidth());

        GridFX grid = GridFX.getInstance();
        this.setCenter(grid);
        grid.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(6));

        this.switchMenu(GameController.GameState.NEUTRAL);
        this.setLeft(menuBox);

    }

    /**
     * This method should update the gridfx and the resource bar
     */
    public void update() {
        GridFX.update();
        resourcesMenu.update();
    }


    /**
    * this method should return the resource menu
    * @return resource menu
    */
    public static ResourcesMenu getResources() {
        return resourcesMenu;
    }


    /**
     * This method switches menus based on passed in game state.
     * GameController.java calls this to control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {

        VBox toAdd = new VBox();
        switch (state) {
        case MILITARY :
            toAdd = militaryMenu.getRootNode();
            break;
        case WORKER :
            toAdd = workerMenu.getRootNode();
            break;
        case RECRUITING :
            toAdd = recruitMenu.getRootNode();
            break;
        case BUILDING :
            toAdd = buildingMenu.getRootNode();
            break;
        default : //for NEUTRAL
            toAdd = statusMenu.getRootNode();
            break;
        }
        menuBox.getChildren().setAll(toAdd);

    }

}
