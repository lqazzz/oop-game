//package com.Arkanoid.game.Controller;
//import com.Arkanoid.game.Utils.GlobalState;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Slider;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.util.Duration;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class SoundController {
//    @FXML
//    private static MediaPlayer currentPlayer;
//
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        SoundController.playMusic("background.mp3", true);
////    }
//
//    public static void playMusic(String fileName, boolean loop) {
//        stopMusic();
//        URL resourceUrl = SoundController.class.getResource("/sounds/" + fileName);
//        if (resourceUrl == null) {
//            System.err.println("Can't find media: /sounds/" + fileName);
//            return;
//        }
//        Media media = new Media(resourceUrl.toExternalForm());
//        currentPlayer = new MediaPlayer(media);
//        if (loop) {
//            currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        }
//        currentPlayer.play();
//    }
//    public static void stopMusic() {
//        if (currentPlayer != null) {
//            currentPlayer.stop();
//        }
//    }
//
//    public static void toggleMusic(Slider musicSlider) {
//        if (currentPlayer != null){
//            if(GlobalState.isMusicMuted() == false) {
//                currentPlayer.setVolume(0);
//                musicSlider.setValue(0);
//            } else {
//                currentPlayer.setVolume(100);
//                musicSlider.setValue(100);
//            }
//            GlobalState.setMusicMuted(!GlobalState.isMusicMuted());
//        }
//
//    }
//
//    public static void changeMusicVolume(Slider musicSlider) {
//        currentPlayer.setVolume(musicSlider.getValue() / 100.0);
//
//        musicSlider.valueProperty().addListener((obs, oldVal, newVal) ->{
//            currentPlayer.setVolume(newVal.doubleValue() / 100.0);
//        });
//        GlobalState.setMusicMuted(currentPlayer.getVolume() == 0);
//    }
//
//}

package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundController {

    private MediaPlayer currentPlayer;
    private static SoundController instance = new SoundController();

    private SoundController() {
    }

    public static SoundController getInstance() {
        return instance;
    }

    public void playMusic(String fileName, boolean loop) {
        stopMusic();
        URL resourceUrl = SoundController.class.getResource("/sounds/" + fileName);
        if (resourceUrl == null) {
            System.err.println("Can't find media: /sounds/" + fileName);
            return;
        }
        Media media = new Media(resourceUrl.toExternalForm());
        currentPlayer = new MediaPlayer(media);
        if (loop) {
            currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        currentPlayer.play();
    }

    public void stopMusic() {
        if (currentPlayer != null) {
            currentPlayer.stop();
        }
    }

    public void toggleMusic(Slider musicSlider) {
        if (currentPlayer != null) {
            if (!GlobalState.isMusicMuted()) {
                currentPlayer.setVolume(0);
                musicSlider.setValue(0);
            } else {
                currentPlayer.setVolume(1.0);
                musicSlider.setValue(100);
            }
            GlobalState.setMusicMuted(!GlobalState.isMusicMuted());
        }
    }

    public void changeMusicVolume(Slider musicSlider) {
        if (currentPlayer != null) {
            currentPlayer.setVolume(musicSlider.getValue() / 100.0);

            musicSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                currentPlayer.setVolume(newVal.doubleValue() / 100.0);
            });

            GlobalState.setMusicMuted(currentPlayer.getVolume() == 0);
        }
    }

    public MediaPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isPlaying() {
        return currentPlayer != null &&
                currentPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public void pauseMusic() {
        if (currentPlayer != null && isPlaying()) {
            currentPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (currentPlayer != null) {
            currentPlayer.play();
        }
    }
}