package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.ButtonEffect;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModeGamePlayController extends SceneController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button settingsButton;
    @FXML private Button pvpButton;
    @FXML private Button scoresButton;
    @FXML
    public void initialize() {
     //   SoundController.playMusic("background.mp3", true);
        ButtonEffect.applyCuteIdle(settingsButton);
        ButtonEffect.applyCuteIdle(pvpButton);
        ButtonEffect.applyCuteIdle(scoresButton);
    }
}
