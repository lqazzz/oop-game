package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Paddle extends MovableObject{
    protected int frames;
    protected boolean isStretched = false;
    protected double speed;
    protected List<GameConfig.Powerup> powerups = new ArrayList<>();
    protected String typePaddle;
    protected Group paddleGroup = new Group();
    protected Image img;
    protected ImageView view;
    protected Ball ball;
    public Paddle(double x, double y, int width, int height) {
        super(x , y , width ,height );
        img = new Image(getClass().getResourceAsStream("/images/Paddle/red.png"));
        view = new ImageView(img);
        view.setFitHeight(GameConfig.DEFAULT_PADDLE_HEIGHT);
        view.setFitWidth(GameConfig.DEFAULT_PADDLE_WIDTH);
        paddleGroup.getChildren().add(view);
        paddleGroup.setLayoutX(GameConfig.DEFAULT_PADDLE_LAYOUT_X);
        paddleGroup.setLayoutY(GameConfig.DEFAULT_PADDLE_LAYOUT_Y);
    }
    //Fix ball goes inside paddle at speed 1
    public Bounds getBoundsTop(){
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(paddleGroup.getLayoutX(), paddleGroup.getLayoutY(), getWidth(), 1);
    }
    public Bounds getBounds(){
        return new BoundingBox(paddleGroup.getLayoutX(), paddleGroup.getLayoutY(), getWidth(), getHeight());
    }
    // canh duoi
    public Bounds getBoundsBottom(){
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(paddleGroup.getLayoutX(), paddleGroup.getLayoutY() + getHeight(), getWidth(), 1);
    }
    public Bounds getBoundsLeft() {
        double inset = Math.max(1, Math.round(getWidth() * 0.1));
        return new BoundingBox(paddleGroup.getLayoutX() - 5, paddleGroup.getLayoutY(), 1.5, paddleGroup.getLayoutY() + getHeight());
    }
    public Bounds getBoundsRight() {
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(paddleGroup.getLayoutX() + getWidth() + 5, paddleGroup.getLayoutY(), 1.5, getHeight());
    }
    @Override
    public void update() {
    }
    public boolean collision(Ball ball){
        Bounds ballBounds = ball.getBallGroup().getBoundsInParent();
        if(ballBounds.intersects(getBounds())) {
            if(ballBounds.intersects(getBoundsTop())) {
                double paddleCenterX = paddleGroup.getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0;
                double ballCenterX = ball.getBallGroup().getLayoutX() + GameConfig.DEFAULT_BALL_WIDTH / 2.0;
                double hitPos = (ballCenterX - paddleCenterX) / (GameConfig.DEFAULT_PADDLE_WIDTH / 2.0);
                ball.setAngleSpecific(90 - hitPos * 60);
                return true;
            }
            if(ballBounds.intersects(getBoundsTop()) && ballBounds.intersects(getBoundsLeft())) {
                ball.setAngleSpecific(165);
                return true;
            }
            if(ballBounds.intersects(getBoundsTop()) && ballBounds.intersects(getBoundsRight())) {
                ball.setAngleSpecific(15);
                return true;
            }
            if(ballBounds.intersects(getBoundsLeft())) {
                ball.setAngleSpecific(255);
                return true;
            }
            if(ballBounds.intersects(getBoundsRight())) {
                ball.setAngleSpecific(285);
                return true;
            }
        }
        return false;
    }
    private double normalizeAngle(double angle) {
        angle %= 360;
        if (angle < 0) angle += 360;
        return angle;
    }
    public boolean collision(PowerUp power, GameState state){
        if(power.getPowerUpGroup().getBoundsInParent().intersects(getBoundsTop())){
            if(power.typePowerup == 8) {
                List<Ball> balls = new ArrayList<>(state.getBalls());
                if(balls.size() >= 30) return true;
                for(Ball ball : balls) {
                    if(state.getBalls().size() >= 30) break;;
                    double x = ball.getLayoutX();
                    double y = ball.getLayoutY();
                    double angle = ball.getAngle();
                    double angleLeft = angle - 10;
                    double angleRight = angle + 10;
                    Ball leftBall = new Ball(x, y, -1);
                    leftBall.setAngleSpecific(normalizeAngle(angleLeft));
                    Ball rightBall = new Ball(x, y, -1);
                    rightBall.setAngleSpecific(normalizeAngle(angleRight));
                    state.getBalls().add(leftBall);
                    state.getBalls().add(rightBall);
                }
            }
            if (power.typePowerup == 3) {
                frames = 600;
                isStretched = true;
            }
            if(power.typePowerup == 2) {
                HitPoint hp = state.getHitPoints().getLast();
                state.getHitPoints().add(new HitPoint(hp.getHitPointGroup().getLayoutX() + 40 , hp.getHitPointGroup().getLayoutY()));
            }
            return true;
        }
        return false;
    }
    public void paddleStretch() {
        if(isStretched) {
            if(frames > 0) {
                view.setFitWidth(GameConfig.DEFAULT_PADDLE_WIDTH * 1.2);
                frames -= 1;
            } else {
                isStretched = false;
                view.setFitWidth(GameConfig.DEFAULT_PADDLE_WIDTH);
            }
        }
    }
    @Override
    public void render(GraphicsContext gc) {
//        Image img = new Image(getClass().getResourceAsStream("/images/Paddle/gray.png"));
//        gc.drawImage(img, paddleGroup.getLayoutX(), paddleGroup.getLayoutY(), getWidth(),getHeight());
    }

    public void moveWithMouse(double x) {
        if(GlobalState.isGamePaused() == false) paddleGroup.setLayoutX(x - GameConfig.DEFAULT_PADDLE_WIDTH / 2.0);
    }

    public void moveWithKeys(int angle) {
        if(GlobalState.isGamePaused() == false) setLayoutX(paddleGroup.getLayoutX() + GameConfig.DEFAULT_SPEED * Math.cos(Math.toRadians(angle)));
    }

    public boolean updatePaddle(Ball ball) {
        return collision(ball);
    }
    public boolean updatePaddle(PowerUp power, GameState state) {
        return collision(power, state);
    }
    public Group getPaddleGroup() {
        return paddleGroup;
    }
}
