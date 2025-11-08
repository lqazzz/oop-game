package com.Arkanoid.game.Model;
import com.Arkanoid.game.Controller.SoundController;
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
    protected int stretchFrames;
    protected int shootFrames;
    protected boolean isStretched = false;
    protected boolean isShooting = false;
    protected double speed;
    protected List<GameConfig.Powerup> powerups = new ArrayList<>();
    protected String typePaddle;
    protected Group paddleGroup = new Group();
    protected Image img;
    protected ImageView view;
    protected boolean isMoveLeft = false;
    protected boolean isMoveRight = false;
    protected boolean isMoveLeft2 = false;
    protected boolean isMoveRight2 = false;
    protected Ball ball;

    public Paddle(double x, double y, int width, int height) {
        super(x , y , width ,height );
        img = new Image(getClass().getResourceAsStream(GlobalState.getCurrentPadPath()));
        view = new ImageView(img);
        view.setFitHeight(height);
        view.setFitWidth(width);

        paddleGroup.getChildren().add(view);
        paddleGroup.setLayoutX(x);
        paddleGroup.setLayoutY(y);
    }

    public Bounds getBoundsTop() {
        return new BoundingBox(
                paddleGroup.getLayoutX(),
                paddleGroup.getLayoutY(),
                getWidth(),
                1
        );
    }

    public Bounds getBounds() {
        return new BoundingBox(
                paddleGroup.getLayoutX(),
                paddleGroup.getLayoutY(),
                getWidth(),
                getHeight()
        );
    }

    public Bounds getBoundsBottom() {
        return new BoundingBox(
                paddleGroup.getLayoutX(),
                paddleGroup.getLayoutY() + getHeight(),
                getWidth(),
                1
        );
    }

    public Bounds getBoundsLeft() {
        return new BoundingBox(
                paddleGroup.getLayoutX(),
                paddleGroup.getLayoutY(),
                5,
                getHeight()
        );
    }

    public Bounds getBoundsRight() {
        return new BoundingBox(
                paddleGroup.getLayoutX() + getWidth() - 5,
                paddleGroup.getLayoutY(),
                5,
                getHeight()
        );
    }

    @Override
    public void update() {
    }

    public boolean collision(Ball ball) {
        Bounds ballBounds = ball.getBallGroup().getBoundsInParent();

        if (!ballBounds.intersects(getBounds())) {
            return false;
        }

        double ballCenterX = ball.getBallGroup().getLayoutX() + GameConfig.DEFAULT_BALL_WIDTH / 2.0;
        double ballCenterY = ball.getBallGroup().getLayoutY() + GameConfig.DEFAULT_BALL_HEIGHT / 2.0;
        double paddleCenterY = paddleGroup.getLayoutY() + getHeight() / 2.0;

        if (ballBounds.intersects(getBoundsTop()) && ballBounds.intersects(getBoundsLeft())) {
            ball.setAngleSpecific(165);
            return true;
        }

        if (ballBounds.intersects(getBoundsTop()) && ballBounds.intersects(getBoundsRight())) {
            ball.setAngleSpecific(15);
            return true;
        }

        if (ballBounds.intersects(getBoundsLeft()) &&
                Math.abs(ballCenterY - paddleCenterY) < getHeight() / 2.0) {
            ball.setAngleSpecific(255);
            return true;
        }

        if (ballBounds.intersects(getBoundsRight()) &&
                Math.abs(ballCenterY - paddleCenterY) < getHeight() / 2.0) {
            ball.setAngleSpecific(285);
            return true;
        }

        if (ballBounds.intersects(getBoundsTop())) {
            double paddleCenterX = paddleGroup.getLayoutX() + getWidth() / 2.0;

            double hitPos = (ballCenterX - paddleCenterX) / (getWidth() / 2.0);

            // Normalization to the range [-1, 1]
            hitPos = Math.max(-1.0, Math.min(1.0, hitPos));

            // hitPos = -1 => angle = 90 - (-1 * 60) = 150°
            // hitPos = 0  => angle = 90 - (0 * 60) = 90°
            // hitPos = 1  => angle = 90 - (1 * 60) = 30°
            ball.setAngleSpecific(90 - hitPos * 60);
            return true;
        }

        if (ballBounds.intersects(getBoundsBottom())) {
            double paddleCenterX = paddleGroup.getLayoutX() + getWidth() / 2.0;
            double hitPos = (ballCenterX - paddleCenterX) / (getWidth() / 2.0);
            hitPos = Math.max(-1.0, Math.min(1.0, hitPos));

            ball.setAngleSpecific(270 + hitPos * 60);
            return true;
        }

        return false;
    }

    private double normalizeAngle(double angle) {
        angle %= 360;
        if (angle < 0) angle += 360;
        return angle;
    }

    public boolean collision(PowerUp power, GameState state) {
        if (power.getPowerUpGroup().getBoundsInParent().intersects(getBoundsTop())) {
            if (power.typePowerup == 6) {
                List<Ball> balls = new ArrayList<>(state.getBalls());
                if (balls.size() >= 6) return true;
                for (Ball ball : balls) {
                    if (state.getBalls().size() >= 6) break;
                    double x = ball.getLayoutX();
                    double y = ball.getLayoutY();
                    double angle = ball.getAngle();
                    double angleLeft = angle - 10;
                    double angleRight = angle + 10;

                    Ball leftBall = state.getFactory().createBall(x, y, state.getGameRoot());
                    leftBall.setAngleSpecific(normalizeAngle(angleLeft));

                    Ball rightBall = state.getFactory().createBall(x, y, state.getGameRoot());
                    rightBall.setAngleSpecific(normalizeAngle(angleRight));

                    state.getBalls().add(leftBall);
                    state.getBalls().add(rightBall);
                }
            }
            if (power.typePowerup == 3) {
                stretchFrames = 600;
                isStretched = true;
            }
            if (power.typePowerup == 1) {
                for (Ball ball : state.getBalls()) {
                    ball.setFireMode(true);
                    ball.setFrames(600);
                }
            }
            if (power.typePowerup == 0) {
                isShooting = true;
                shootFrames = 600;
            }
            if (power.typePowerup == 2) {
                HitPoint hp = state.getHitPoints().getLast();
                state.getHitPoints().add(new HitPoint(
                        hp.getHitPointGroup().getLayoutX() + 40 ,
                        hp.getHitPointGroup().getLayoutY())
                );
            }
            if (power.typePowerup == 4) {
                for (Ball ball : state.getBalls()) {
                    ball.setSlowerMode();
                }
            }
            if (power.typePowerup == 5) {
                for (Ball ball : state.getBalls()) {
                    ball.setSpeedupMode();
                }
            }
            return true;
        }
        return false;
    }

    public boolean collision(PowerUp power, PongGameState state) {
        if (power.getPowerUpGroup().getBoundsInParent().intersects(getBoundsTop())) {
            if (power.typePowerup == 8) {
                List<Ball> balls = new ArrayList<>(state.getBalls());
                if (balls.size() >= 30) return true;
                for (Ball ball : balls) {
                    if (state.getBalls().size() >= 30) break;
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
                stretchFrames = 600;
                isStretched = true;
            }
            if (power.typePowerup == 1) {
                for (Ball ball : state.getBalls()) {
                    ball.setFireMode(true);
                    ball.setFrames(600);
                }
            }
            if (power.typePowerup == 0) {
                isShooting = true;
                shootFrames = 600;
            }
            if (power.typePowerup == 2) {
                HitPoint hp = state.getHitPoints().getLast();
                state.getHitPoints().add(new HitPoint(
                        hp.getHitPointGroup().getLayoutX() + 40,
                        hp.getHitPointGroup().getLayoutY())
                );
            }
            return true;
        }
        return false;
    }

    public void paddleStretch() {
        if (isStretched) {
            String padPath = GlobalState.getCurrentPadPath();
            if (stretchFrames > 0) {
                String firstPart = padPath.substring(0, padPath.length() - 10);
                view.setImage(new Image(getClass().getResourceAsStream(firstPart + "large.png")));
                view.setFitWidth(GameConfig.DEFAULT_PADDLE_WIDTH * 1.2);
                stretchFrames -= 1;
            } else {
                isStretched = false;
                view.setImage(new Image(getClass().getResourceAsStream(padPath)));
                view.setFitWidth(GameConfig.DEFAULT_PADDLE_WIDTH);
            }
        }
    }

    public void shootBullets(GameState state) {
        if (isShooting) {
            if (shootFrames > 0) {
                if (shootFrames % 50 == 0) {
                    double x = paddleGroup.getLayoutX();
                    double y = paddleGroup.getLayoutY();
                    Bullet leftBullet = state.getFactory().createBullet(
                            x,
                            y + 10
                    );
                    Bullet rightBullet = state.getFactory().createBullet(
                            x + view.getFitWidth() - 5,
                            y + 10
                    );
                    state.getBullets().add(leftBullet);
                    state.getBullets().add(rightBullet);
                    SoundController.getInstance().playBulletSound();
                }
                shootFrames -= 1;
            }
        } else {
            isShooting = false;
            shootFrames = 600;
        }
    }

    public void shootBullets(PongGameState state) {
        if (isShooting) {
            if (shootFrames > 0) {
                if (shootFrames % 50 == 0) {
                    double x = paddleGroup.getLayoutX();
                    double y = paddleGroup.getLayoutY();
                    Bullet leftBullet = new Bullet(x, y + 10, 47, 27);
                    Bullet rightBullet = new Bullet(
                            x + view.getFitWidth() - 5,
                            y + 10,
                            47,
                            27
                    );
                    state.getBullets().add(leftBullet);
                    state.getBullets().add(rightBullet);
                }
                shootFrames -= 1;
            }
        } else {
            isShooting = false;
            shootFrames = 600;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
    }

    public void moveWithMouse(double x) {
        if (GlobalState.isGamePaused() == false) {
            paddleGroup.setLayoutX(x - GameConfig.DEFAULT_PADDLE_WIDTH / 2.0);
        }
    }

    public void moveLeft() {
        if (GlobalState.isGamePaused() == false && isMoveLeft && paddleGroup.getLayoutX() > 204)  {
            paddleGroup.setLayoutX(paddleGroup.getLayoutX() - GameConfig.DEFAULT_PADDLE_SPEED);
        }
    }

    public void moveRight() {
        if (GlobalState.isGamePaused() == false && isMoveRight &&
                paddleGroup.getLayoutX() + view.getFitWidth() < GameConfig.DEFAULT_SCREEN_WIDTH - 204) {
            paddleGroup.setLayoutX(paddleGroup.getLayoutX() + GameConfig.DEFAULT_PADDLE_SPEED);
        }
    }

    public void moveLeftPong() {
        if (GlobalState.isGamePaused() == false && isMoveLeft
                && paddleGroup.getLayoutX() > 233)  {
            paddleGroup.setLayoutX(paddleGroup.getLayoutX() - GameConfig.DEFAULT_PADDLE_SPEED);
        }
    }
    public void moveRightPong() {
        if (GlobalState.isGamePaused() == false && isMoveRight
                && paddleGroup.getLayoutX() + view.getFitWidth() < GameConfig.DEFAULT_SCREEN_WIDTH - 233) {
            paddleGroup.setLayoutX(paddleGroup.getLayoutX() + GameConfig.DEFAULT_PADDLE_SPEED);
        }
    }
    public void justMove(GameState state) {
        moveLeft();
        moveRight();
        shootBullets(state);
        paddleStretch();
    }
    public boolean updatePaddle(Ball ball, GameState state) {

        return collision(ball);
    }

    public boolean updatePaddle(Ball ball, PongGameState state) {
        moveLeftPong();
        moveRightPong();
        shootBullets(state);
        paddleStretch();
        return collision(ball);
    }

    public boolean updatePaddle(PowerUp power, GameState state) {
        return collision(power, state);
    }

    public Group getPaddleGroup() {
        return paddleGroup;
    }

    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        isMoveRight = moveRight;
    }

    public void setMoveLeft2(boolean moveLeft) {
        isMoveLeft2 = moveLeft;
    }

    public void setMoveRight2(boolean moveRight) {
        isMoveRight2 = moveRight;
    }

    public boolean updatePaddle(PowerUp power, PongGameState state) {
        return collision(power, state);
    }

    public void setStretched(boolean stretched) {
        stretchFrames = 600;
        isStretched = stretched;
    }

    public void setShooting(boolean shooting) {
        shootFrames = 600;
        isShooting = shooting;
    }
}