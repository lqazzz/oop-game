package com.Arkanoid.game.Model;

import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class PaddleCollisionTest {

    private Paddle paddle;
    private Ball ball;
    private Group gameRoot;

    @BeforeAll
    static void initJavaFX() {
        try {
            javafx.application.Platform.startup(() -> {});
        } catch (IllegalStateException e) {}
    }

    @BeforeEach
    void setUp() {
        gameRoot = new Group();
        GlobalState.setCurrentBallPath("/images/Ball/defaultBall.png");
        GlobalState.setCurrentPadPath("/images/Paddle/defaultPad/normal.png");
        
        paddle = new Paddle(
                GameConfig.DEFAULT_PADDLE_LAYOUT_X,
                GameConfig.DEFAULT_PADDLE_LAYOUT_Y,
                GameConfig.DEFAULT_PADDLE_WIDTH,
                GameConfig.DEFAULT_PADDLE_HEIGHT
        );
        ball = new Ball(
                GameConfig.DEFAULT_BALL_LAYOUT_X,
                GameConfig.DEFAULT_BALL_LAYOUT_Y,
                -1
        );
        ball.setTrailEffect(gameRoot);
    }

    @Test
    @DisplayName("Test ball dropping directly to the top bounds")
    void testCollisionTopCenter() {
        double paddleCenterX = paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0;
        ball.getBallGroup().setLayoutX(paddleCenterX - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - GameConfig.DEFAULT_BALL_HEIGHT);

        ball.setAngleSpecific(270);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertEquals(90.0, ball.getAngle(), 0.1, "Angle of reflection must be 90");
    }

    @Test
    @DisplayName("Test ball dropping from the right to the top bounds -> must be oriented to the left")
    void testCollisionTopLeft() {
        ball.getBallGroup().setLayoutX(paddle.getPaddleGroup().getLayoutX() + 20);
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - GameConfig.DEFAULT_BALL_HEIGHT);

        ball.setAngleSpecific(270);
        boolean collision = paddle.collision(ball);

        assertTrue(collision, "Ball must be collided");
        assertTrue(ball.getAngle() > 90.0 && ball.getAngle() < 180, "Must be oriented to the left");
    }

    @Test
    @DisplayName("Test ball dropping from the left to the top bounds -> must be oriented to the right")
    void testCollisionTopRight() {
        ball.getBallGroup().setLayoutX(
                paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH - 20
        );
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertTrue(ball.getAngle() > 0 && ball.getAngle() < 90.0, "Must be oriented to the right");
    }

    @Test
    @DisplayName("Test ball colliding with top left corner -> reset angle to 165")
    void testCollisionTopLeftCorner() {
        ball.getBallGroup().setLayoutX(paddle.getPaddleGroup().getLayoutX() - 5);
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - 5);
        ball.setAngleSpecific(270);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertEquals(165.0, ball.getAngle(), "Reset angle to 165 in this case");
    }

    @Test
    @DisplayName("Test ball colliding with top right corner -> reset angle to 165")
    void testCollisionTopRightCorner() {
        ball.getBallGroup().setLayoutX(
                paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH - 5
        );
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - 5);
        ball.setAngleSpecific(270);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertEquals(15.0, ball.getAngle(), "Reset angle to 15 in this case");
    }

    @Test
    @DisplayName("Test ball colliding with left bounds")
    void testCollisionLeft() {
        ball.getBallGroup().setLayoutX(paddle.getPaddleGroup().getLayoutX() - GameConfig.DEFAULT_BALL_WIDTH);
        ball.getBallGroup().setLayoutY(
                paddle.getPaddleGroup().getLayoutY() + GameConfig.DEFAULT_PADDLE_HEIGHT / 2
        );
        ball.setAngleSpecific(0);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertEquals(255.0, ball.getAngle(), "Angle of reflection must be 255");
    }

    @Test
    @DisplayName("Test ball colliding with right bounds")
    void testCollisionRight() {
        ball.getBallGroup().setLayoutX(
                paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH
        );
        ball.getBallGroup().setLayoutY(
                paddle.getPaddleGroup().getLayoutY() + GameConfig.DEFAULT_PADDLE_HEIGHT / 2
        );
        ball.setAngleSpecific(180);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertEquals(285.0, ball.getAngle(),"Angle of reflection must be 285");
    }

    @Test
    @DisplayName("Test ball colliding with Bottom bounds")
    void testCollisionBottom() {
        double paddleCenterX = paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0;
        ball.getBallGroup().setLayoutX(paddleCenterX - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(
                paddle.getPaddleGroup().getLayoutY() + GameConfig.DEFAULT_PADDLE_HEIGHT
        );
        ball.setAngleSpecific(90);
        boolean collision = paddle.collision(ball);
        assertTrue(collision, "Ball must be collided");
        assertTrue(ball.getAngle() > 180 && ball.getAngle() < 360, "Balls move down");
    }

    @Test
    @DisplayName("No collision with paddle")
    void testNoCollisionFarAway() {
        ball.getBallGroup().setLayoutX(100);
        ball.getBallGroup().setLayoutY(100);
        double initialAngle = ball.getAngle();
        boolean collision = paddle.collision(ball);
        assertFalse(collision, "Not colliding");
        assertEquals(initialAngle, ball.getAngle(), "Angle must be the same as before");
    }

    @Test
    @DisplayName("Multiple of collisions")
    void testMultipleCollisions() {
        double paddleCenterX = paddle.getPaddleGroup().getLayoutX() + GameConfig.DEFAULT_PADDLE_WIDTH / 2.0;
        ball.getBallGroup().setLayoutX(paddleCenterX - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertTrue(paddle.collision(ball), "1st collision");
        double angle1 = ball.getAngle();

        ball.getBallGroup().setLayoutY(paddle.getPaddleGroup().getLayoutY() - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertTrue(paddle.collision(ball), "2nd collision");
        double angle2 = ball.getAngle();

        assertEquals(angle1, angle2, "Angle of reflection must be the same");
    }

    @Test
    @DisplayName("Test Paddle Bounds")
    void testPaddleBounds() {
        assertNotNull(paddle.getBounds(), "Bounds must be existed");
        assertNotNull(paddle.getBoundsTop(), "TopBounds must be existed");
        assertNotNull(paddle.getBoundsBottom(), "BottomBounds must be existed");
        assertNotNull(paddle.getBoundsLeft(), "LeftBounds must be existed");
        assertNotNull(paddle.getBoundsRight(), "RightBounds must be existed");
    }
}