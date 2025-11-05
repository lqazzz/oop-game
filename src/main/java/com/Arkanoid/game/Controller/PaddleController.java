package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Paddle;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.PauseMenu;
import javafx.scene.input.KeyCode;

public class PaddleController {

    public PaddleController(){}

    public void moveWithMouse(Paddle paddle) {
        GlobalState.getScene().setOnMouseMoved(e -> {
            if(e.getX() > GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 &&
            e.getX() < GameConfig.DEFAULT_SCREEN_WIDTH - GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 && !GlobalState.isGameOver()) {
                paddle.moveWithMouse(e.getX());
            }
        });
    }
    public void moveWithWASDMulti(Paddle paddle, Paddle paddle2) {
        GlobalState.getScene().getRoot().requestFocus();
        GlobalState.getScene().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A) {
                paddle.setMoveLeft(true);
            } else if(e.getCode() == KeyCode.D) {
                paddle.setMoveRight(true);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                paddle2.setMoveLeft(true);
            }
            else if(e.getCode() == KeyCode.RIGHT) {
                paddle2.setMoveRight(true);
            }
            else if(e.getCode() == KeyCode.ESCAPE) {
                if(!GlobalState.isGamePaused()) {
                    GlobalState.initPauseMenu();
                }
                GlobalState.setGamePaused(!GlobalState.isGamePaused());
            }
        });
        GlobalState.getScene().setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.A) {
                paddle.setMoveLeft(false);
            } else if(e.getCode() == KeyCode.D) {
                paddle.setMoveRight(false);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                paddle2.setMoveLeft(false);
            }
            else if(e.getCode() == KeyCode.RIGHT) {
                paddle2.setMoveRight(false);
            }
        });
    }
    public void moveWithWASDSingle(Paddle paddle) {
        GlobalState.getScene().getRoot().requestFocus();
        GlobalState.getScene().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A) {
                paddle.setMoveLeft(true);
            } else if(e.getCode() == KeyCode.D) {
                paddle.setMoveRight(true);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                paddle.setMoveLeft(true);
            }
            else if(e.getCode() == KeyCode.RIGHT) {
                paddle.setMoveRight(true);
            }
            else if(e.getCode() == KeyCode.ESCAPE) {
                if(!GlobalState.isGamePaused()) {
                    GlobalState.initPauseMenu();
                }
                GlobalState.setGamePaused(!GlobalState.isGamePaused());
            }
        });
        GlobalState.getScene().setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.A) {
                paddle.setMoveLeft(false);
            } else if(e.getCode() == KeyCode.D) {
                paddle.setMoveRight(false);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                paddle.setMoveLeft(false);
            }
            else if(e.getCode() == KeyCode.RIGHT) {
                paddle.setMoveRight(false);
            }
        });
    }
}
