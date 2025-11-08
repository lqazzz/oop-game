package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundController {

    private MediaPlayer currentPlayer;
    private static AudioClip btnClick = new AudioClip(SoundController.class.
            getResource("/sfx/buttonClick.m4a").toExternalForm());
    private static AudioClip bulletSound = new AudioClip(SoundController.class.
            getResource("/sfx/laserShoot.wav").toExternalForm());
    private static AudioClip normalBrickSound = new AudioClip(SoundController.class.
            getResource("/sfx/bonk.m4a").toExternalForm());
    private static AudioClip supermanBrickSound = new AudioClip(SoundController.class.
            getResource("/sfx/bonk.m4a").toExternalForm());
    private static AudioClip powerUpSound = new AudioClip(SoundController.class.
            getResource("/sfx/getPower.m4a").toExternalForm());
    private static SoundController instance = new SoundController();

    private SoundController() {
    }

    public static SoundController getInstance() {
        return instance;
    }

    public void playBtnClick() {
        if (!GlobalState.isSoundMuted()) {
            btnClick.stop();
            btnClick.play();
        }
    }

    public void playBulletSound() {
        if (!GlobalState.isSoundMuted()) {
            bulletSound.stop();
            bulletSound.play();
        }
    }

    public void playNormalBrickSound() {
        if (!GlobalState.isSoundMuted()) {
            normalBrickSound.stop();
            normalBrickSound.play();
        }
    }

    public void playSupermanBrickSound() {
        if (!GlobalState.isSoundMuted()) {
            supermanBrickSound.stop();
            supermanBrickSound.play();
        }
    }

    public void playPowerUpSound() {
        if (!GlobalState.isSoundMuted()) {
            powerUpSound.stop();
            powerUpSound.play();
        }
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

            musicSlider.valueProperty().addListener(
                    (obs, oldVal, newVal) -> {
                currentPlayer.setVolume(newVal.doubleValue() / 100.0);
            });

            GlobalState.setMusicMuted(currentPlayer.getVolume() == 0);
        }
    }

    public void toggleSound(Slider soundSlider) {
        if (btnClick != null) {
            if (!GlobalState.isSoundMuted()) {
                soundSlider.setValue(0);
            } else {
                soundSlider.setValue(100);
            }
            GlobalState.setSoundMuted(!GlobalState.isSoundMuted());
        }
    }

    public void changeSoundVolume(Slider soundSlider) {
        if (btnClick != null) {
            btnClick.setVolume(soundSlider.getValue() / 100.0);

            soundSlider.valueProperty().addListener(
                    (obs, oldVal, newVal) -> {
                btnClick.setVolume(newVal.doubleValue() / 100.0);
            });
            GlobalState.setSoundMuted(btnClick.getVolume() == 0);
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