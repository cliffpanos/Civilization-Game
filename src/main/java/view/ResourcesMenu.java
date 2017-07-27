package view;

import model.Civilization;
import model.Settlement;
import model.Skill;
import controller.GameController;
import runner.CivilizationGame;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Popup;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;



public class ResourcesMenu {

    private HBox layout; //This HBox will hold all of the Texts in ResourceMenu
    private Map<Label, LabelProperties> labels = new HashMap<>();
    private Label stratLevel = new Label();
    private Label resources = new Label();
    private Label settlements = new Label();
    private Label money = new Label();
    private Label food = new Label();
    private Label happiness = new Label();
    private Label technology = new Label();

    private Civilization civilization = GameController.getCivilization();
    private ArrayList<Settlement> arrSettlements;
    private String settlementString = "";
    private ArrayList<Skill> arrSkills;
    private String skillString = "";

    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {

        //Initialize structures
        int hBoxSpacing =
            (int) (65 * (CivilizationGame.getStartScreen().getWidth() / 1440));
        layout = new HBox(hBoxSpacing);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(12));


        //Establish LabelProperties for each Label
        labels.put(stratLevel, new LabelProperties(-630, false,
            new TitledPane("Strategy Level", new VBox(10)),
                new Label("You began with a Strategy Level of 0."),
                "This will be updated!"));
        labels.put(resources, new LabelProperties(-400, false,
            new TitledPane("Game Resources", new VBox(10)),
                new Label("You began the game with 50 Resources."),
                "Use your resources to perform actions such as:\n"
                    + "- Construct new Settlements\n- Attack Enemies\n"
                    + "- Convert Units into better units"));
        labels.put(settlements, new LabelProperties(-200, false,
            new TitledPane("Game Settlements", new VBox(10)),
                new Label("You began the game with 1 Settlement."),
                "Create Settlements to expand your empire.\n"
                    + "You have settled the following Settlements:\n"
                    + settlementString));
        labels.put(money, new LabelProperties(0, false,
            new TitledPane("Money in Treasury", new VBox(10)),
                new Label("You began the game with 50 Coins."),
                "Use money to perform actions such as:\n"
                    + "- Construct new Settlements\n- Invest in Units\n"
                    + "- Convert Units into better units"));
        labels.put(food, new LabelProperties(167, false,
            new TitledPane("Food in Civilization", new VBox(10)),
                new Label("You began the game with 50 Food."),
                "Keep your citizens well fed!\n"
                    + "Feed your citizens by recruiting:\n"
                    + "- Farmer Units\n"
                    + "- Anglers Units"));
        labels.put(happiness, new LabelProperties(315, false,
            new TitledPane("Citizen Happiness", new VBox(10)),
                new Label("You began the game with 100 happiness."),
                "Keep your citizens happy!\n"
                    + "Improve happiness by:\n"
                    + "- Philosophizing & considering life\n"
                    + "- Talking about how bad UGA is\n"
                    + "- Remembering that Tech just beat UGA\n"
                    + "- Reading, meditating, & sleeping"));
        labels.put(technology, new LabelProperties(402, false,
            new TitledPane("Technology Points", new VBox(10)),
                new Label("You began the game with 0 Tech Points."),
                "This will be updated!"));


        //, resources, settlements, money, food, happiness};
        //Handles the Font, Color, and Mouse hover properties of each Label
        for (Label label : labels.keySet()) {
            label.setFont(new Font("Times", 20));
            label.setTextFill(Color.web("#08611D"));
            label.setOnMouseEntered(e -> {
                    label.setTextFill(Color
                        .web("#A30820"));
                });
            label.setOnMouseExited(e -> label.setTextFill(Color
                    .web("#08611D")));
            label.setOnMousePressed(e -> label.setOpacity(0.4));
            label.setOnMouseReleased(e -> label.setOpacity(1.0));

            Popup popup = new Popup();
            TitledPane titledPane = labels.get(label).getTitledPane();
            popup.setHeight(700);
            popup.setWidth(300);
            popup.getContent().add(titledPane);

            label.setOnMouseClicked(e -> { //Mouse click to show/hide Popup

                    boolean skipPopupHideConditional = false;

                    if (!labels.get(label).getPopupShowing()) {
                        popup.show(CivilizationGame.getStage(),
                            ((layout.getWidth() * 0.5) + (labels.get(label)
                                .getNum() * (layout.getWidth() / 1410))),
                            layout.getHeight() + 51);
                        labels.get(label).setPopupShowing(true);
                        skipPopupHideConditional = true;
                    }

                    if (labels.get(label).getPopupShowing()
                        && !skipPopupHideConditional) {
                        popup.hide();
                        labels.get(label).setPopupShowing(false);
                    }
                });
        }

        //Sets the Background of the HBox
        Image backgroundImage = ImageResource.get("desert.jpg");
        layout.setBackground(new Background(new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, new BackgroundSize(layout.getWidth(),
            layout.getHeight(), false, false, true, false))));

        this.update();

        layout.getChildren().addAll(stratLevel, resources, settlements,
            money, food, happiness, technology);

    }

    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {

        int stratLevelNum = civilization.getStrategy().getStrategyLevel();
        int techPointsNum = civilization.getTechnology().getTechPoints();

        arrSettlements = civilization.getSettlements();
        settlementString = "";
        for (int s = 0; s < arrSettlements.size(); s++) {
            settlementString += ((s + 1) + ". "
                + arrSettlements.get(s).getName());
            settlementString += (s == arrSettlements.size() - 1 ? "" : "\n");
        }

        arrSkills = new ArrayList<>(civilization.getTechnology().getSkills());
        skillString = arrSkills.size() == 0 ? "-> You have no Tech Skills" : "";
        for (int s = 0; s < arrSkills.size(); s++) {
            skillString += ((s + 1) + ". "
                + arrSkills.get(s).toString());
            skillString += (s == arrSkills.size() - 1 ? "" : "\n");
        }

        stratLevel.setText("Strategy Level: " + stratLevelNum);
        resources.setText("Resources: " + civilization.getResources());
        settlements.setText("Settlements: " + civilization.getNumSettlements());
        money.setText("Money: " + civilization.getTreasury().getCoins());
        food.setText("Food: " + civilization.getFood());
        happiness.setText("Happiness: " + civilization.getHappiness());
        technology.setText("Technology: " + civilization.getTechnology()
            .getTechPoints());

        labels.get(stratLevel).getText().setText(
            "You need 500 Strategy Points to win the Game.\n"
                + "Gain Strategy Points by attacking enemies.\n"
                + "- You currently have " + stratLevelNum
                + " Strategy Points.\n* You need "
                + (500 - stratLevelNum) + " more Strategy Points to win!");
        labels.get(settlements).getText().setText(
            "Create Settlements to expand your empire.\n"
                + "You have settled the following Settlements:\n"
                + settlementString);
        labels.get(technology).getText().setText(
            "You need 800 Tech Points to win the Game.\n"
                + "- You currently have " + techPointsNum
                + " Tech Points.\n* You need " + (800 - techPointsNum)
                + " more Tech Points to win!\nYour current Tech Skills are:\n"
                + skillString);
    }

    private class LabelProperties { //Holds the properties for each Label

        private int num; //Used to properly arrange the Popup for each Label
        private boolean popupShowing;
        private TitledPane titledPane;
        private Text text;

        public LabelProperties(int num, boolean popupShowing, TitledPane t,
            Label label, String textBox) {
            this.num = num;
            this.popupShowing = popupShowing;
            this.titledPane = t;
            this.text = new Text(textBox);
            titledPane.setTextAlignment(TextAlignment.LEFT);
            titledPane.setTextFill(Color.web("#11627E"));
            titledPane.setFont(new Font("Helvetica", 18));

            label.setFont(new Font("Helvetica", 16));
            label.setTextFill(Color.web("#11627E"));

            VBox vBox = (VBox) titledPane.getContent();
            Image image = ImageResource.get("civIcon.png");
            ImageView imageView = new ImageView(image);
            imageView.resize(image.getRequestedWidth(),
                image.getRequestedHeight());
            text.setLineSpacing(10);
            text.setFont(new Font("Helvetica", 14));
            vBox.setAlignment(Pos.CENTER);
            vBox.setBackground(new Background(new BackgroundImage(
                ImageResource.get("popupBackground.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(vBox
                    .getWidth(), vBox.getHeight(), false, false,
                    false, true))));
            vBox.getChildren().addAll(label, text, imageView);
        }
        public int getNum() {
            return this.num;
        }
        public boolean getPopupShowing() {
            return this.popupShowing;
        }
        public void setPopupShowing(boolean popupShowing) {
            this.popupShowing = popupShowing;
        }
        public TitledPane getTitledPane() {
            return this.titledPane;
        }
        public Text getText() {
            return this.text;
        }
    }

    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        this.update();
        return layout;
    }
}
