package com.Arkanoid.game.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;
import com.Arkanoid.game.Utils.ButtonEffect;
public class SceneController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML private Button settingsButton;
    @FXML private Button pvpButton;
    @FXML private Button scoresButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoundController.playMusic("background.mp3", true);
        ButtonEffect.applyCuteIdle(settingsButton);
        ButtonEffect.applyCuteIdle(pvpButton);
        ButtonEffect.applyCuteIdle(scoresButton);
    }
}