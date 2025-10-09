package com.Arkanoid.game.Controller;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SoundController {
    private static MediaPlayer currentPlayer;

    public static void playMusic(String fileName, boolean loop) {
        stopMusic();
        URL resourceUrl = SoundController.class.getResource("/sounds/" + fileName);
        if (resourceUrl == null) {
            System.err.println("LỖI: Không tìm thấy file âm thanh tại: /sounds/" + fileName);
            return;
        }
        Media media = new Media(resourceUrl.toExternalForm());
        currentPlayer = new MediaPlayer(media);
        if (loop) {
            currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        currentPlayer.play();
    }
    public static void stopMusic() {
        if (currentPlayer != null) {
            currentPlayer.stop();
        }
    }
}