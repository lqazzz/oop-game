package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Paddle;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.PauseMenu;
import javafx.scene.input.KeyCode;

public class PaddleController {

    public PaddleController(){}

    public void moveWithMouse(Paddle paddle) {
        /**
         * Might want to consider pad follow cursor at certain speed rather than instant tp at cursor X pos
         * since moving cursor too fast outside window cause paddle to not fully go left/right
         */
        /**
         * your mom fat!
         */
        //M vt dc cmt nhu nay chua ma chui
        //Full tieng anh ra
        //Xuong xa vcl, dung kid
        GlobalState.getScene().setOnMouseMoved(e -> {
            if(e.getX() > GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 &&
            e.getX() < GameConfig.DEFAULT_SCREEN_WIDTH - GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 && !GlobalState.isGameOver()) {
                paddle.moveWithMouse(e.getX());
            }
        });
    }
    public void moveWithWASD(Paddle paddle2, Paddle paddle) {
        GlobalState.getScene().getRoot().requestFocus();
        // switch
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
              //  PauseMenu.
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
                System.out.println("up move left");
            }
            else if(e.getCode() == KeyCode.RIGHT) {
                paddle2.setMoveRight(false);
                System.out.println("up move right");
            }
        });
        //The h xai phim hay chuot
        //Gi co, noi lai di
        //T chua limit vi co xai dau
        //An may
        //The bo chuot a u

//Dai t        // noob lowtech cha biet cdg ve cong nghe
    }
}
