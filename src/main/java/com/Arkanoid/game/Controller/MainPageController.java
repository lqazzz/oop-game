package com.Arkanoid.game.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import com.Arkanoid.game.Model.Scene;
public class MainPageController extends Scene {
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
    @FXML
    public void switchToShop(ActionEvent event) throws IOException {
        super.switchToShop(event);
    }
    public void switchToGuide(ActionEvent event) throws IOException {
        super.switchToGuide(event);
    }
}