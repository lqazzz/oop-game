package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.ButtonEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModeGamePlayController extends SceneController {
    @FXML
    private Button settingsButton;
    @FXML
    private Button pvpButton;
    @FXML
    private Button scoresButton;

    @FXML
    public void initialize() {
        ButtonEffect.applyCuteIdle(settingsButton);
        ButtonEffect.applyCuteIdle(pvpButton);
        ButtonEffect.applyCuteIdle(scoresButton);
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        super.switchToMainPage(event);
    }
}
