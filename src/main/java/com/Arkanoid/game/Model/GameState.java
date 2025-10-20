package com.Arkanoid.game.Model;

import com.Arkanoid.game.Controller.PaddleController;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.Arkanoid.game.Utils.GameConfig.BRICK_HEIGHT;
import static com.Arkanoid.game.Utils.GameConfig.BRICK_WIDTH;

public class GameState {
    private Ball ball;
    private Paddle paddle;
    private List<Bricks> bricks = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private Group gameRoot;
    private PaddleController padControl = new PaddleController();
    public GameState(Group gameRoot) {
        this.gameRoot = gameRoot;
        ball = new Ball(GameConfig.DEFAULT_BALL_LAYOUT_X, GameConfig.DEFAULT_BALL_LAYOUT_Y, -1);
        paddle = new Paddle(GameConfig.DEFAULT_PADDLE_LAYOUT_X, GameConfig.DEFAULT_PADDLE_LAYOUT_Y, GameConfig.DEFAULT_PADDLE_WIDTH, GameConfig.DEFAULT_PADDLE_HEIGHT);
        // sau tao se lam load map o day

        int BRICK_COLS = 18;
        int BRICK_ROWS = 7;
        int startX = (1200 - BRICK_COLS * BRICK_WIDTH) / 2;
        int startY = 140;
        for (int row = 0; row < BRICK_ROWS; row++) {
            for (int col = 0; col < BRICK_COLS; col++) {
                int x = startX + col * (BRICK_WIDTH + 20);
                int y = startY + row * (BRICK_HEIGHT + 1);
                bricks.add(new Bricks(x, y, 1, BRICK_WIDTH, BRICK_HEIGHT, String.valueOf(row + 1)));
            }
        }
    }

    public Ball getBall() {
        return ball;
    }
    public List<Bricks> getBricks(){
        return bricks;
    }
    public Paddle getPaddle() {
        return paddle;
    }
    public List<PowerUp> getPowerUpList() {
        return powerUps;
    }
    public Group getGameRoot() {
        return gameRoot;
    }
    public PaddleController getPadControl() {
        return padControl;
    }
}
