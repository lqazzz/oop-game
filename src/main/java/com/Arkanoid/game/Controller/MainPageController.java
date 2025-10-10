package com.Arkanoid.game.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainPageController extends SceneController {
    @FXML
    public void quitGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void switchToSetting(ActionEvent event) throws IOException {
        super.switchToSetting(event);
    }
    @FXML
    public void switchToModeGame(ActionEvent event) throws IOException {
        super.switchToModeGame(event);
    }
}