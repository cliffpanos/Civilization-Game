package view;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class SoundEffect {

    private static MediaPlayer playing = null;

    private static StartScreen classPath = new StartScreen();

    public static void play(String soundName) {

        if (playing != null) {
            playing.stop(); //Stop last sound from continuing to play
        }

        //filePath
        String filePath = "sounds/" + soundName;


        playing = new MediaPlayer(new Media(classPath.getClass()
            .getResource(filePath).toExternalForm()));

        playing.play();

    }

}
