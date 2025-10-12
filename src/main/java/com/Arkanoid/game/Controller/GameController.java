package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.View.GameView;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameController {
    @FXML
    private Canvas GameCanvas;
    private final GameState model = new GameState();

    public void initialize(){
       GameView view = new GameView(GameCanvas.getGraphicsContext2D());
       System.out.println("Ok");
       view.render(model);
    }
}
