package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends MovableObject {
    protected double angle = 45;
    protected double dx;
    protected double dy;
    protected String typeBall;
    protected Image img = new Image(getClass().getResourceAsStream("/images/Ball/planet.png"));
    protected ImageView view = new ImageView(img);
    Group ballGroup = new Group();
    public Ball(double x, double y, double radius) {
        super(x, y, GameConfig.DEFAULT_BALL_WIDTH, GameConfig.DEFAULT_BALL_HEIGHT);
        view.setFitHeight(GameConfig.DEFAULT_BALL_HEIGHT);
        view.setFitWidth(GameConfig.DEFAULT_BALL_WIDTH);
        ballGroup.getChildren().addAll(view);
        ballGroup.setLayoutX(GameConfig.DEFAULT_BALL_LAYOUT_X);
        ballGroup.setLayoutY(GameConfig.DEFAULT_BALL_LAYOUT_Y);
        dx = GameConfig.DEFAULT_SPEED;//left to right
        dy = GameConfig.DEFAULT_SPEED;//left to right
        System.out.println(GameConfig.DEFAULT_BALL_WIDTH + " " + GameConfig.DEFAULT_BALL_HEIGHT);
    }
    @Override
    public void update() {
//        System.out.println(ballGroup.getBoundsInParent());
        if(isHitWindowVertical()) {
            setAngleVertical(true);
        }
        if(isHitWindowHorizontal()){
            setAngleHorizontal(true);
        }
        move();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, getX(), getY(), getWidth(), getHeight());
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeOval(getX(), getY(), getWidth(), getHeight());  // same as circle

    }

    public void move() {
        dx = GameConfig.DEFAULT_SPEED * Math.cos(Math.toRadians(angle));
        dy = -GameConfig.DEFAULT_SPEED * Math.sin(Math.toRadians(angle));
        ballGroup.setLayoutX(ballGroup.getLayoutX() + GameConfig.DEFAULT_SPEED * Math.cos(Math.toRadians(angle)));
        ballGroup.setLayoutY(ballGroup.getLayoutY() - GameConfig.DEFAULT_SPEED * Math.sin(Math.toRadians(angle)));
    }
    public void setAngleSpecific(double newAngle) {
        angle = newAngle;
    }
    public void setAngleVertical(boolean isOppositeDir) {
        if(isOppositeDir) {
            angle = -angle;
        }
    }
    public void setAngleHorizontal(boolean isOppositeDir) {
        if(isOppositeDir) {
            angle = 180 - angle;
        }
    }

    public boolean isHitWindowHorizontal() {
        if(ballGroup.getLayoutX() > 0 && ballGroup.getLayoutX() < GameConfig.DEFAULT_SCREEN_WIDTH - getWidth()) {
            return false;
        }
        return true;
    }
    public boolean isHitWindowVertical() {
        if(ballGroup.getLayoutY() > 0 && (ballGroup.getLayoutY() < GameConfig.DEFAULT_SCREEN_HEIGHT - getHeight())) {
            return false;
        }
        return true;
    }

    public Group getBallGroup() {
        return ballGroup;
    }

    public double getVelocityX() {
        return dx;
    }

    public double getVelocityY() {
        return dy;
    }
}
