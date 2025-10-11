package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void switchToSetting(ActionEvent event) throws IOException {
        loadScene(event, "/fxml/setting.fxml");
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        loadScene(event, "/fxml/main-page.fxml");
    }

    public void switchToModeGame(ActionEvent event) throws IOException {
        loadScene(event, "/fxml/choose-mode-game-play.fxml");
    }

    public void switchToGuide(ActionEvent event) throws IOException {
        loadScene(event, "/fxml/how-to-play.fxml");
    }

    public void switchToShop(ActionEvent event) throws IOException {
        loadScene(event, "/fxml/skin.fxml");
    }

    public void loadScene(ActionEvent event, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        GlobalState.setRoot(loader.load());
        GlobalState.setStage((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        GlobalState.setScene(GlobalState.getRoot());
        GlobalState.getStage().setScene(GlobalState.getScene());
//        GlobalState.getStage().show();
    }
}