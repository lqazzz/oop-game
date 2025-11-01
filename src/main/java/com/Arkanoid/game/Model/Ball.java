package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.FillTransition;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.LinkedList;

public class Ball extends MovableObject {
    protected double angle = 45;
    protected double frames = 600;
    protected double dx;
    protected double dy;
    protected boolean isFireMode;
    protected String typeBall;
    protected Image img;
    protected ImageView view;
    private LinkedList<Circle> trail = new LinkedList<>();
    private static final int MAX_TRAIL_SIZE = 15;
    Group ballGroup = new Group();
    public Ball(double x, double y, double radius) {
        super(x, y, GameConfig.DEFAULT_BALL_WIDTH, GameConfig.DEFAULT_BALL_HEIGHT);
        img = new Image(getClass().getResourceAsStream(GlobalState.getCurrentBallPath()));
        view = new ImageView(img);
        view.setFitHeight(GameConfig.DEFAULT_BALL_HEIGHT);
        view.setFitWidth(GameConfig.DEFAULT_BALL_WIDTH);


        ballGroup.getChildren().addAll(view);
        ballGroup.setLayoutX(x);
        ballGroup.setLayoutY(y);

        dx = GameConfig.DEFAULT_SPEED;//left to right
        dy = GameConfig.DEFAULT_SPEED;//left to right
//        isMoved = false;
    }

    private void updateTrail(Pane root) {

    }

    public double getAngle() {
        return angle;
    }
    public boolean update(GameState state) {
        if(ballGroup.getLayoutY() > 850) {
           // resetBall(state);
            return true;
        }
        if(GlobalState.isBallMoved()) {
            fireBall();
            move();
            if(isHitWindowVertical()) {
                setAngleVertical(true);
                if(ballGroup.getLayoutY() < 0) {
                    flashWall(GlobalState.getTopWallLine());
                    ballGroup.setLayoutY(1);
                } else if (ballGroup.getLayoutY() + ballGroup.getBoundsInParent().getHeight() > GameConfig.DEFAULT_PADDLE_HEIGHT) { // chạm tường dưới
                    ballGroup.setLayoutY(GameConfig.DEFAULT_PADDLE_HEIGHT - ballGroup.getBoundsInParent().getHeight() - 1);
                }
                return false;
            }
            if(isHitWindowHorizontal()){
                setAngleHorizontal(true);
                System.out.println(getLayoutX());
                if (ballGroup.getLayoutX() < 204) {
                    flashWall(GlobalState.getLeftWallLine());
                    ballGroup.setLayoutX(204);
                } else if (ballGroup.getLayoutX() + ballGroup.getBoundsInParent().getWidth() > GameConfig.DEFAULT_SCREEN_WIDTH - 204) { // tường phải
                    flashWall(GlobalState.getRightWallLine());
                    ballGroup.setLayoutX(GameConfig.DEFAULT_SCREEN_WIDTH - ballGroup.getBoundsInParent().getWidth() - 1 - 204);
                }
                return false;
            }
            return false;
        } else {
            GlobalState.getScene().setOnMouseClicked(e -> {
                GlobalState.setBallMoved(true);
            });
            moveWithPad(state);
            return false;
        }
    }
    public void clearTrail(Pane root) {

    }
    private void flashWall(Rectangle wall) {
        if (wall == null) return;
        Color defaultColor = Color.web("#00FFFF20");
        Color neonColor = Color.web("#00FFFF");
        wall.setEffect(new DropShadow(25, neonColor));
        FillTransition ft = new FillTransition(Duration.millis(200), wall, defaultColor, neonColor);
        ft.setAutoReverse(true);
        ft.setCycleCount(1);
        ft.setOnFinished(e -> {
            wall.setFill(defaultColor);
            wall.setEffect(null);
        });
        ft.play();
    }

    public int update(PongGameState state) {
        if(ballGroup.getLayoutY() > GameConfig.DEFAULT_SCREEN_HEIGHT - GameConfig.DEFAULT_BALL_HEIGHT) {
            return -1;
        }
        if (ballGroup.getLayoutY() < 0) {
            return 1;
        }
        if(GlobalState.isBallMoved()) {
            fireBall();
            move();
            if(isHitWindowVertical()) {
                setAngleVertical(true);
                if(getLayoutY() < 0) {
                    ballGroup.setLayoutY(GameConfig.DEFAULT_PADDLE_HEIGHT - ballGroup.getBoundsInParent().getHeight() - 1);
                } else if (getLayoutY() + ballGroup.getBoundsInParent().getHeight() > GameConfig.DEFAULT_PADDLE_HEIGHT) { // chạm tường dưới
                    ballGroup.setLayoutY(GameConfig.DEFAULT_PADDLE_HEIGHT - ballGroup.getBoundsInParent().getHeight() - 1);
                }
                return 0;
            }
            if(isHitWindowHorizontal()){
                setAngleHorizontal(true);
                if (getLayoutX() < 0) {
                    ballGroup.setLayoutX(1);
                } else if (getLayoutX() + ballGroup.getBoundsInParent().getWidth() > GameConfig.DEFAULT_SCREEN_WIDTH) { // tường phải
                    ballGroup.setLayoutX(GameConfig.DEFAULT_SCREEN_WIDTH - ballGroup.getBoundsInParent().getWidth() - 1);
                }
                return 0;
            }
            return 0;
        } else {
            GlobalState.getScene().setOnMouseClicked(e -> {
                GlobalState.setBallMoved(true);
            });
            moveWithPad(state);
            return 0;
        }
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
        updateTrail((Pane)GlobalState.getRoot());
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
        if(ballGroup.getLayoutX() > 204 && ballGroup.getLayoutX() < GameConfig.DEFAULT_SCREEN_WIDTH - getWidth() - 204) {
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

    public void setVelocityX(boolean check) {
        if(check == true) {
            if(dx > 0) dx = -dx;
        }
        else {
            if(dx < 0) dx = -dx;
        }
    }
    public void setVelocityY() {
        dy = -dy;
    }

    public void resetBall(GameState state) {
        ballGroup.setLayoutX(state.getPaddle().getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ballGroup.setLayoutY(GameConfig.DEFAULT_BALL_LAYOUT_Y);
        GlobalState.setBallMoved(false);
        angle = 45;
    }

    public void resetBall(PongGameState state) {
        ballGroup.setLayoutX(GameConfig.DEFAULT_SCREEN_WIDTH / 2);
        ballGroup.setLayoutY(GameConfig.DEFAULT_SCREEN_HEIGHT / 2 - GameConfig.DEFAULT_BALL_WIDTH / 2);
        GlobalState.setBallMoved(false);
        angle = 45;
    }


    public void moveWithPad(GameState state) {
        ballGroup.setLayoutX(state.getPaddle().getPaddleGroup().getLayoutX()
                + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
    }

    public void moveWithPad(PongGameState state) {
        ballGroup.setLayoutX(state.getPaddle().getPaddleGroup().getLayoutX()
                + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
    }


    public boolean isFireMode() {
        return isFireMode;
    }
    public void setFireMode(boolean fireMode) {
        isFireMode = fireMode;
    }

    public void setFrames(double frames) {
        this.frames = frames;
    }

    public void fireBall() {
        if(isFireMode) {
            if(frames > 0) {
                frames -= 1;
            } else {
                isFireMode = false;
            }
        }
    }
}
