package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundController {

    private MediaPlayer currentPlayer;
    private static SoundController instance = new SoundController();

    private static AudioClip btnClick;
    private static AudioClip bulletSound;
    private static AudioClip normalBrickSound;
    private static AudioClip supermanBrickSound;
    private static AudioClip powerUpSound;

    private SoundController() {
        new Thread(() -> {
            try {
                btnClick = new AudioClip(getClass()
                        .getResource("/sfx/buttonClick.m4a").toExternalForm());
                bulletSound = new AudioClip(getClass()
                        .getResource("/sfx/laserShoot.wav").toExternalForm());
                normalBrickSound = new AudioClip(getClass()
                        .getResource("/sfx/bonk.m4a").toExternalForm());
                supermanBrickSound = new AudioClip(getClass()
                        .getResource("/sfx/bonk.m4a").toExternalForm());
                powerUpSound = new AudioClip(getClass()
                        .getResource("/sfx/getPower.m4a").toExternalForm());
                System.out.println("All sounds loaded!");
            } catch (Exception e) {
                System.err.println("Error loading sounds: " + e.getMessage());
            }
        }).start();
    }

    public static SoundController getInstance() {
        return instance;
    }

    public void playBtnClick() {
        if (!GlobalState.isSoundMuted() && btnClick != null) {
            new Thread(() -> btnClick.play()).start();
        }
    }

    public void playBulletSound() {
        if (!GlobalState.isSoundMuted() && bulletSound != null) {
            new Thread(() -> bulletSound.play()).start();
        }
    }

    public void playNormalBrickSound() {
        if (!GlobalState.isSoundMuted() && normalBrickSound != null) {
            new Thread(() -> normalBrickSound.play()).start();
        }
    }

    public void playSupermanBrickSound() {
        if (!GlobalState.isSoundMuted() && supermanBrickSound != null) {
            new Thread(() -> {
                supermanBrickSound.setVolume(1.2);
                supermanBrickSound.play();
            }).start();
        }
    }

    public void playPowerUpSound() {
        if (!GlobalState.isSoundMuted() && powerUpSound != null) {
            new Thread(() -> powerUpSound.play()).start();
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
            double volume = soundSlider.getValue() / 100.0;
            btnClick.setVolume(volume);
            bulletSound.setVolume(volume);
            normalBrickSound.setVolume(volume);
            supermanBrickSound.setVolume(volume);
            powerUpSound.setVolume(volume);

            soundSlider.valueProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        double vol = newVal.doubleValue() / 100.0;
                        btnClick.setVolume(vol);
                        bulletSound.setVolume(vol);
                        normalBrickSound.setVolume(vol);
                        supermanBrickSound.setVolume(vol);
                        powerUpSound.setVolume(vol);
                    });

            GlobalState.setSoundMuted(volume == 0);
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