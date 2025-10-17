package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.GameView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.BlendMode;

public class GameController {
    @FXML
    private Canvas GameCanvas;
    @FXML
    private Group gameGroup;

    public void initialize(){
//        GameCanvas.setStyle("-fx-background-color: transparent;");
//        GameCanvas.toFront(); // ensure above ImageView
//        GameCanvas.setBlendMode(BlendMode.SRC_OVER);
        GameState model = new GameState(gameGroup); // chỗ này
        GameView view = new GameView();
       // GlobalState.gameRoot = gameGroup;
        System.out.println("Ok");
        view.render(model);
    }
}
