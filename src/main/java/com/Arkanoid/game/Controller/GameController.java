package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.View.GameView;
import javafx.fxml.FXML;
import javafx.scene.Group;

public class GameController {
    @FXML
    private Group gameGroup;

    public void initialize(){
        GameState model = new GameState(gameGroup); // chỗ này
        GameView view = new GameView();
        System.out.println("Ok");
        view.render(model);
    }
}
