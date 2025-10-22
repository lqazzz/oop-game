package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.ButtonEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.Arkanoid.game.Model.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModeGamePlayController extends Scene {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button settingsButton;
    @FXML private Button pvpButton;
    @FXML private Button scoresButton;
    @FXML
    public void switchToLevel(ActionEvent event) throws IOException {
        super.switchToSelectLevel(event);
    }
    @FXML
    public void switchToPreviousPage(ActionEvent event) throws IOException{
        super.switchToMainPage(event);
    }
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        super.switchToMainPage(event);
    }
    public void initialize() {
     //   SoundController.playMusic("background.mp3", true);
        ButtonEffect.applyCuteIdle(settingsButton);
        ButtonEffect.applyCuteIdle(pvpButton);
        ButtonEffect.applyCuteIdle(scoresButton);
    }
}
