package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.PongGameState;
import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.View.PauseMenu;
import com.Arkanoid.game.View.PongGameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;

import java.io.IOException;


public class PongGameController extends Scene {
    @FXML public Group gameGroup;

    @FXML
    public void pauseGame(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        PauseMenu.addPauseMenu();
    }

    @FXML
    public void initialize() {
        PongGameState model = new PongGameState(gameGroup);
        PongGameView view = new PongGameView();
        view.render(model);
    }
}

