package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.Arkanoid.game.Model.Scene;
import java.io.IOException;

public class SettingController extends Scene {
    @FXML
    private ImageView musicBtn;
    @FXML
    private ImageView soundBtn;
    @FXML
    private Slider musicSlider;
    private final Image soundOn = new Image(getClass().getResource("/images/Icon/audioOn.png").toExternalForm());
    private final Image soundOff = new Image(getClass().getResource("/images/Icon/audioOff.png").toExternalForm());

    @FXML
    public void initialize() {
        updateSoundIcon();
        musicSlider.setValue((GlobalState.isMusicMuted() ? 0 : 1) * 100);
        musicBtn.setImage(GlobalState.isMusicMuted() ? soundOff : soundOn);
        soundBtn.setImage(GlobalState.isSoundMuted() ? soundOff : soundOn);
    }

    @FXML
    public void changeMusicState(ActionEvent event) throws IOException {
        SoundController.toggleMusic(musicSlider);
        updateMusicIcon();
    }

    @FXML
    public void changeSoundState(ActionEvent event) throws IOException {
        updateSoundIcon();
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        super.switchToMainPage(event);
    }

    @FXML
    public void changeMusicVolume() {
        SoundController.changeMusicVolume(musicSlider);
    }

    @FXML
    private void updateSoundIcon() {
        musicBtn.setImage(GlobalState.isSoundMuted() ? soundOff : soundOn);
    }

    @FXML
    private void updateMusicIcon() {
        musicBtn.setImage(GlobalState.isMusicMuted() ? soundOff : soundOn);
    }
}