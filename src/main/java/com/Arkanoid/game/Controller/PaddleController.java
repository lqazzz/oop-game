package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Paddle;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.input.KeyCode;

public class PaddleController {

    public PaddleController(){}

    public void moveWithMouse(Paddle paddle) {
        /**
         * Might want to consider pad follow cursor at certain speed rather than instant tp at cursor X pos
         * since moving cursor too fast outside window cause paddle to not fully go left/right
         */
        GlobalState.getScene().setOnMouseMoved(e -> {
            if(e.getX() > GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 &&
            e.getX() < GameConfig.DEFAULT_SCREEN_WIDTH - GameConfig.DEFAULT_PADDLE_WIDTH / 2.0) {
                paddle.moveWithMouse(e.getX());
            }
        });
    }
    public void moveWithArrows(Paddle paddle) {
        GlobalState.getScene().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                paddle.moveWithKeys(180);
            } else if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                paddle.moveWithKeys(0);
            }
        });
    }
}
