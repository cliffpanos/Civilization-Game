package view;


import controller.GameController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TerrainTile;
import model.Settlement;
import runner.CivilizationGame;

import javafx.stage.Popup;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class TerrainTileFX extends StackPane {
    private Rectangle overlay;
    private ImageView background;
    private TerrainTile tile;
    private ImageView icon = new ImageView("File:./bologna");
    private Popup popup = new Popup();
    private Rectangle highlight = new Rectangle();
    private double xcoord = (CivilizationGame.getStage().getWidth() / 2.0);
    private boolean highlightHover = false;
    private boolean iconHover = false;
    private boolean colorHover = false;
    private static Popup currentPopupShowing = new Popup();
    private static TerrainTileFX terrainTileFXselected = null;
    private static TerrainTileFX currentPopupOwner = null;

    /**
     * Constructor for TerrainTileFX.
     * Creates a Rectangle for the highlighting and overlay
     * Creates ImageViews for the background terrain and icon
     * Transitions states when a tile is clicked
     * @param tile
     */
    public TerrainTileFX(TerrainTile tile) {
        this.tile = tile;
        overlay = new Rectangle(70, 70, Color.rgb(0, 0, 0, 0.0));
        overlay.setStroke(Color.BLACK);
        this.background = new ImageView(tile.getImage());
        this.getChildren().addAll(background, overlay);
        updateTileView();
        this.setOnMousePressed(event -> {
                GameController.setLastClicked(this);
            });

        this.setOnMouseEntered(e -> {
                terrainTileFXselected = GameController.getLastClicked();
                if (this.getTile().getOccupant() == null
                    && terrainTileFXselected != null
                    && terrainTileFXselected.getPopup()
                    != currentPopupShowing) {
                    currentPopupShowing.hide();
                } else if (!(this.getTile().getOccupant() instanceof Settlement
                    && this == currentPopupOwner)
                    && terrainTileFXselected != null
                    && terrainTileFXselected.getPopup()
                    != currentPopupShowing) {
                    currentPopupShowing.hide();
                }
                if (terrainTileFXselected == null) {
                    currentPopupShowing.hide();
                }
            });
    }

    /**
     * gets the TerrainTile of this TerrainTileFX
     * @return TerrainTile
     */
    public TerrainTile getTile() {
        return tile;
    }

    /**
     * this method updates the view of this TerrainTileFX.
     * It should check if the TerrainTile is empty. If it is empty,
     * set the overlay to be transparent. If it is not empty, fill
     * the overlay with the color of the occupant on the terrain tile
     * If the TerrainTileFX contains an icon, remove it. If the tile is
     * not empty, get the image of the occupant of the tile and add the
     * image of the occupant to the tile.
     */
    public void updateTileView() {

        this.getChildren().clear(); //Remove all elements from the tile
        if (this.tile.isEmpty()) {
            overlay = new Rectangle(70, 70, Color.rgb(0, 0, 0, 0.0));
            overlay.setStroke(Color.BLACK);
            this.getChildren().addAll(background, overlay);
        } else {
            overlay = new Rectangle(70, 70, tile.getOccupant().getColor());
            overlay.setStroke(Color.BLACK);
            icon = new ImageView(tile.getOccupant().getImage());
            this.getChildren().addAll(background, overlay, icon);
        }
        //Memory address comparison is necesary, not field comparison
        if (this == GameController.getLastClicked()) {
            highlight =
                new Rectangle(70, 70, Color.web("0749FB", 0.5));
            highlight.setStroke(Color.BLACK);
            this.getChildren().add(highlight);
            colorHover = false;
            iconHover = false;
            if (this.popup == currentPopupShowing) {
                currentPopupOwner = this;
            }
            terrainTileFXselected = this;

        } else if (currentPopupOwner != null && this == currentPopupOwner) {
            currentPopupShowing.hide();
        }
        if (this.tile.getOccupant() instanceof Settlement) {
            setUp(icon);
        }

    }

    private void setUp(ImageView iv) {

        String name = ((Settlement) this.tile.getOccupant()).getName();
        Label label = new Label(name);
        label.setFont(new Font("Times", 18));
        label.setTextFill(Color.web("#11627E"));

        StackPane sp = new StackPane();
        xcoord = ((double) 13 * name.length());
        Rectangle whiteBackground = new Rectangle(xcoord, 25,
            Color.web("#F7F7F7", 0.72));
        whiteBackground.setArcWidth(7);
        whiteBackground.setArcHeight(7);
        sp.getChildren().addAll(whiteBackground, label);
        popup.getContent().add(sp);

        iv.setOnMouseEntered(e -> {
                iconHover = true;
                if (this != currentPopupOwner) {
                    currentPopupShowing.hide();
                }
                show();
            });

        highlight.setOnMouseEntered(e -> {
                highlightHover = true;
                if (this != currentPopupOwner) {
                    currentPopupShowing.hide();
                }
                show();
            });

        overlay.setOnMouseEntered(e -> {
                colorHover = true;
                if (this != currentPopupOwner) {
                    currentPopupShowing.hide();
                }
                show();
            });

        iv.setOnMouseExited(e -> {
                iconHover = false;
            });

        highlight.setOnMouseExited(e -> {
                highlightHover = false;
                if (iconHover == false && colorHover == false
                    && this != GameController.getLastClicked()) {
                    popup.hide();
                }
            });

        overlay.setOnMouseExited(e -> {
                colorHover = false;
                terrainTileFXselected = GameController.getLastClicked();
                if (GameController.getLastClicked() != null
                    && GameController.getLastClicked().getTile().getOccupant()
                    != null && GameController.getLastClicked().getTile()
                    .getOccupant() instanceof Settlement) {
                    if (terrainTileFXselected != null
                        && terrainTileFXselected.getPopup()
                        != currentPopupShowing) {
                        currentPopupShowing.hide();
                        terrainTileFXselected.show();
                    }
                }
            });
    }

    public void show() {
        popup.show(CivilizationGame.getStage(),
            ((double) ((CivilizationGame.getStage().getWidth() / 2)
            - (xcoord / 2)) + 77), 108);
        currentPopupShowing = popup;
        currentPopupOwner = this;

    }

    public Popup getPopup() {
        return this.popup;
    }
}
