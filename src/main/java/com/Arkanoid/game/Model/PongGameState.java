package com.Arkanoid.game.Model;

import com.Arkanoid.game.Controller.PaddleController;
import com.Arkanoid.game.Factory.DefaultGameObjectFactory;
import com.Arkanoid.game.Factory.GameObjectFactory;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.Group;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PongGameState {
    private Ball ball;
    private Paddle paddle;
    private Paddle paddle2;
    private List<Bricks> bricks = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private List<HitPoint> hps = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private Group gameRoot;
    private PaddleController padControl = new PaddleController();

    private GameObjectFactory factory = new DefaultGameObjectFactory();

    public PongGameState(Group gameRoot) {
        this.gameRoot = gameRoot;

        ball = factory.createBall(
                (GameConfig.DEFAULT_SCREEN_WIDTH - GameConfig.DEFAULT_BALL_HEIGHT) / 2,
                (GameConfig.DEFAULT_SCREEN_HEIGHT- GameConfig.DEFAULT_BALL_WIDTH) / 2,
                gameRoot
        );
        balls.add(ball);

        paddle = new Paddle(
                GameConfig.DEFAULT_PADDLE_LAYOUT_X,
                GameConfig.DEFAULT_PADDLE_LAYOUT_Y,
                GameConfig.DEFAULT_PADDLE_WIDTH,
                GameConfig.DEFAULT_PADDLE_HEIGHT
        );
        paddle2 = new Paddle(
                GameConfig.DEFAULT_PADDLE_LAYOUT_X,
                GameConfig.DEFAULT_PADDLE_LAYOUT_Y_REVERSE,
                GameConfig.DEFAULT_PADDLE_WIDTH,
                GameConfig.DEFAULT_PADDLE_HEIGHT
        );

        hps.add(new HitPoint(1500, 1500)); // move icon out of the screen

    }


    public List<Ball> getBalls() {
        return balls;
    }
    public Ball getBall() {
        return ball;
    }
    public List<Bricks> getBricks() {
        return bricks;
    }
    public Paddle getPaddle() {
        return paddle;
    }
    public Paddle getPaddle2() {
        return paddle2;
    }
    public List<PowerUp> getPowerUpList() {
        return powerUps;
    }
    public List<HitPoint> getHitPoints() {return hps;}
    public List<Bullet> getBullets() {return bullets;}
    public Group getGameRoot() {
        return gameRoot;
    }
    public PaddleController getPadControl() {
        return padControl;
    }
}
