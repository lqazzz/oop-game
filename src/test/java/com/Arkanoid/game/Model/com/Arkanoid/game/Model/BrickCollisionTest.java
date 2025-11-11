package com.Arkanoid.game.Model;

import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class BrickCollisionTest {

    private Ball ball;
    private Bricks brick;
    private Group gameRoot;

    @BeforeAll
    static void initJavaFX() {
        try {
            javafx.application.Platform.startup(() -> {});
        } catch (IllegalStateException e) {
        }
    }

    @BeforeEach
    void setUp() {
        gameRoot = new Group();

        GlobalState.setCurrentBallPath("/images/Ball/default.png");
        GlobalState.newTheme = "default";

        brick = new Bricks(
                400,
                200,
                1, // hitPoint
                GameConfig.BRICK_WIDTH,
                GameConfig.BRICK_HEIGHT,
                "1" // type
        );

        ball = new Ball(
                GameConfig.DEFAULT_BALL_LAYOUT_X,
                GameConfig.DEFAULT_BALL_LAYOUT_Y,
                10
        );
        ball.setTrailEffect(gameRoot);
    }

    @Test
    @DisplayName("Upper bound of bricks")
    void testCollisionTop() {
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        double initialAngle = ball.getAngle();
        boolean collision = brick.collision(ball);

        assertTrue(collision, "Collision must be found");
        assertEquals(-270, ball.getAngle(), 0.1, "Reflection of angle must be found");
    }

    @Test
    @DisplayName("Bottom bound of bricks")
    void testCollisionBottom() {
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 + GameConfig.BRICK_HEIGHT);
        ball.setAngleSpecific(90);

        boolean collision = brick.collision(ball);

        assertTrue(collision, "Collision must be found");
        assertEquals(-90, ball.getAngle(), 0.1, "Reflection of angle must be found");
    }

    @Test
    @DisplayName("Left bound of bricks")
    void testCollisionLeft() {
        ball.getBallGroup().setLayoutX(400 - GameConfig.DEFAULT_BALL_WIDTH);
        ball.getBallGroup().setLayoutY(200 + GameConfig.BRICK_HEIGHT / 2.0 - GameConfig.DEFAULT_BALL_HEIGHT / 2.0);
        ball.setAngleSpecific(0);

        boolean collision = brick.collision(ball);

        assertTrue(collision, "Collision must be found");
        assertEquals(180.0, ball.getAngle(), 0.1, "Reflection of angle must be found");
    }

    @Test
    @DisplayName("Right bound of bricks")
    void testCollisionRight() {
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH);
        ball.getBallGroup().setLayoutY(200 + GameConfig.BRICK_HEIGHT / 2.0 - GameConfig.DEFAULT_BALL_HEIGHT / 2.0);
        ball.setAngleSpecific(180);

        boolean collision = brick.collision(ball);

        assertTrue(collision, "Collision must be found");
        assertEquals(0.0, ball.getAngle(), 0.1, "Reflection of angle must be found");
    }

    @Test
    @DisplayName("No collision")
    void testNoCollisionFarAway() {
        ball.getBallGroup().setLayoutX(100);
        ball.getBallGroup().setLayoutY(100);

        double initialAngle = ball.getAngle();
        boolean collision = brick.collision(ball);

        assertFalse(collision, "No collision must be found");
        assertEquals(initialAngle, ball.getAngle(), "Angle must be the same as before");
    }


    @Test
    @DisplayName("HitPoint test after collision")
    void testHitPointDecrease() {
        brick = new Bricks(400, 200, 3, GameConfig.BRICK_WIDTH, GameConfig.BRICK_HEIGHT, "3");

        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertEquals(3, brick.getHitPoint(), "Initial HitPoint must be the same as before");

        brick.collision(ball);
        assertEquals(2, brick.getHitPoint(), "HitPoint must equal to 2 after 1st collision");

        brick.collision(ball);
        assertEquals(1, brick.getHitPoint(), "HitPoint must equal to 1 after 2nd collision");

        brick.collision(ball);
        assertEquals(0, brick.getHitPoint(), "HitPoint must be equal to 0 after 3rd collision");
    }

    @Test
    @DisplayName("Unbreakable bricks")
    void testIndestructibleBrick() {
        brick = new Bricks(
                400,
                200,
                Integer.MAX_VALUE,
                GameConfig.BRICK_WIDTH,
                GameConfig.BRICK_HEIGHT,
                "9"
        );

        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        int initialHitPoint = brick.getHitPoint();
        brick.collision(ball);

        assertEquals(initialHitPoint, brick.getHitPoint(), "No HitPoint");
    }

    @Test
    @DisplayName("Fire Ball collision")
    void testFireModeDestroysBrick() {
        brick = new Bricks(400, 200, 5, GameConfig.BRICK_WIDTH, GameConfig.BRICK_HEIGHT, "5");

        ball.setFireMode(true);
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertEquals(5, brick.getHitPoint(), "Initial HitPoint must be the same as before");

        brick.collision(ball);
        assertEquals(0, brick.getHitPoint(), "HitPoint must be 0 after colliding with fire ball");
    }

    @Test
    @DisplayName("Fire Ball collides with unbreakable bricks")
    void testFireModeCannotDestroyIndestructibleBrick() {
        brick = new Bricks(
                400,
                200,
                Integer.MAX_VALUE,
                GameConfig.BRICK_WIDTH,
                GameConfig.BRICK_HEIGHT,
                "9"
        );

        ball.setFireMode(true);
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        int initialHitPoint = brick.getHitPoint();
        brick.collision(ball);

        assertEquals(initialHitPoint, brick.getHitPoint(), "Fire ball can't not destroy unbreakable bricks");
    }

    @Test
    @DisplayName("Multiple collision")
    void testMultipleCollisions() {
        ball.getBallGroup().setLayoutX(400 + GameConfig.BRICK_WIDTH / 2.0 - GameConfig.DEFAULT_BALL_WIDTH / 2.0);
        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertTrue(brick.collision(ball), "1st collision");
        double angle1 = ball.getAngle();

        ball.getBallGroup().setLayoutY(200 - GameConfig.DEFAULT_BALL_HEIGHT);
        ball.setAngleSpecific(270);

        assertTrue(brick.collision(ball), "2nd collision");
        double angle2 = ball.getAngle();

        assertEquals(angle1, angle2, "Angle of reflection must me the same");
    }

    @Test
    @DisplayName("Bound tester existed")
    void testBrickBounds() {
        assertNotNull(brick.getBounds(), "Bounds must be existed");
        assertNotNull(brick.getBoundsTop(), " TopBounds must be existed");
        assertNotNull(brick.getBoundsBottom(), "BottomBounds must be existed");
        assertNotNull(brick.getBoundsLeft(), "LeftBounds must be existed");
        assertNotNull(brick.getBoundsRight(), "RightBounds must be existed");
    }

    @Test
    @DisplayName("Test va chạm với Bullet")
    void testBulletCollision() {
        Bullet bullet = new Bullet(
                400 + GameConfig.BRICK_WIDTH / 2.0,
                200 - 10,
                47,
                27
        );

        assertEquals(1, brick.getHitPoint(), "HitPoint ban đầu phải là 1");

        boolean collision = brick.collision(bullet);

        assertTrue(collision, "Phải phát hiện va chạm với bullet");
        assertEquals(0, brick.getHitPoint(), "HitPoint phải giảm xuống 0");
        assertTrue(bullet.isDestroyed(), "Bullet phải bị destroy sau va chạm");
    }

    @Test
    @DisplayName("Test bullet không phá hủy brick bất tử")
    void testBulletCannotDestroyIndestructibleBrick() {
        brick = new Bricks(
                400,
                200,
                Integer.MAX_VALUE,
                GameConfig.BRICK_WIDTH,
                GameConfig.BRICK_HEIGHT,
                "9"
        );

        Bullet bullet = new Bullet(
                400 + GameConfig.BRICK_WIDTH / 2.0,
                200 - 10,
                47,
                27
        );

        int initialHitPoint = brick.getHitPoint();
        boolean collision = brick.collision(bullet);

        assertFalse(collision, "Không nên phát hiện va chạm với brick bất tử");
        assertEquals(initialHitPoint, brick.getHitPoint(), "HitPoint không thay đổi");
        assertFalse(bullet.isDestroyed(), "Bullet không bị destroy");
    }
}