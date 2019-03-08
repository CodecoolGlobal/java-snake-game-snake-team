package com.codecool.snake;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;



public class Audio {


    public static void playBlasterSound(){
        Media blasterSound = new Media(new File("blaster.mp3").toURI().toString());
        MediaPlayer media = new MediaPlayer(blasterSound);
        media.play();
    }


    public static void playExplosionSound(){
        Media explosionSound = new Media(new File("explosion.mp3").toURI().toString());
        MediaPlayer media = new MediaPlayer(explosionSound);
        media.play();
    }
}
