package view;

import javafx.scene.image.Image;

public class ImageResource {

    private static StartScreen classPath = new StartScreen();

    public static Image get(String fileName) {

        return new Image(classPath.getClass().getResource(
            fileName).toExternalForm());

    }

}
