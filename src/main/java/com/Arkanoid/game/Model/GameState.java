package com.Arkanoid.game.Model;

import com.Arkanoid.game.Utils.GameConfig;

import java.util.ArrayList;
import java.util.List;

import static com.Arkanoid.game.Utils.GameConfig.BRICK_HEIGHT;
import static com.Arkanoid.game.Utils.GameConfig.BRICK_WIDTH;

public class GameState {
    private Ball ball;
    private Paddle paddle;
    private List<Bricks> bricks = new ArrayList<>();
    public GameState() {
        ball = new Ball(GameConfig.DEFAULT_BALL_LAYOUT_X, GameConfig.DEFAULT_BALL_LAYOUT_Y, -1);
        paddle = new Paddle(GameConfig.DEFAULT_PADDLE_LAYOUT_X, GameConfig.DEFAULT_PADDLE_LAYOUT_Y, GameConfig.DEFAULT_PADDLE_WIDTH, GameConfig.DEFAULT_PADDLE_HEIGHT);

        // sau tao se lam load map o day
        int BRICK_COLS = 20;
        int BRICK_ROWS = 8;
        double startX = (1200 - BRICK_COLS * BRICK_WIDTH) / 2;
        double startY = 50;
        for (int row = 0; row < BRICK_ROWS; row++) {
            for (int col = 0; col < BRICK_COLS; col++) {
                double x = startX + col * BRICK_WIDTH;
                double y = startY + row * BRICK_HEIGHT;
                bricks.add(new Bricks((int)x, (int)y, BRICK_WIDTH, BRICK_HEIGHT, String.valueOf(row + 1)));
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
}
