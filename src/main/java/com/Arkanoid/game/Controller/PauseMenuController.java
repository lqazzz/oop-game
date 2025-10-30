package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Model.PongGameState;
import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;

import java.io.IOException;

public class PauseMenuController extends Scene {
    public void unPauseGame(GameState state) {
        GlobalState.setGamePaused(false);
    }

    public void unPauseGame(PongGameState state) {
        GlobalState.setGamePaused(false);
    }

    public void switchToSelectLevel(ActionEvent event) throws IOException {
        super.switchToSelectLevel(event);
    }

}
