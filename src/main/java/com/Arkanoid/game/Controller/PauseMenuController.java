package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Model.PongGameState;
import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;

import java.io.IOException;

public class PauseMenuController extends Scene {
    public void unPauseGame(GameState state) {
        SoundController.getInstance().playBtnClick();
        GlobalState.setGamePaused(false);
    }

    public void unPauseGame(PongGameState state) {
        SoundController.getInstance().playBtnClick();
        GlobalState.setGamePaused(false);
    }

    public void switchToSelectLevel(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        super.switchToSelectLevel(event);
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }

}
