package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.Utils.RippleEffect;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends MovableObject {
    protected double angle = 45;
    protected double dx;
    protected double dy;
    protected boolean isMoved;
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
//        isMoved = false;
        System.out.println(GameConfig.DEFAULT_BALL_WIDTH + " " + GameConfig.DEFAULT_BALL_HEIGHT);
    }

    public void update(GameState state) {
        if(isHitWindowVertical()) {
            setAngleVertical(true);
        }
        if(isHitWindowHorizontal()){
            setAngleHorizontal(true);
        }

        if(GlobalState.isBallMoved()) {
            move();
        } else {
            GlobalState.getScene().setOnMouseClicked(e -> {
                GlobalState.setBallMoved(true);
            });
            moveWithPad(state);
        }
        if(ballGroup.getLayoutY() > 850) {
            resetBall(state);
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
        RippleEffect.wave((Pane) GlobalState.getRoot(), ballGroup.getLayoutX() , ballGroup.getLayoutY());
        return true;
    }
    public boolean isHitWindowVertical() {
        if(ballGroup.getLayoutY() > 0 && (ballGroup.getLayoutY() < GameConfig.DEFAULT_SCREEN_HEIGHT - getHeight())) {
            return false;
        }
        RippleEffect.wave((Pane) GlobalState.getRoot(), ballGroup.getLayoutX() , ballGroup.getLayoutY());
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

    public void resetBall(GameState state) {
        ballGroup.setLayoutX(state.getPaddle().getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ballGroup.setLayoutY(GameConfig.DEFAULT_BALL_LAYOUT_Y);
        GlobalState.setBallMoved(false);
        angle = 45;
    }

    public void moveWithPad(GameState state) {
        ballGroup.setLayoutX(state.getPaddle().getPaddleGroup().getLayoutX()
                + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
    }
}
