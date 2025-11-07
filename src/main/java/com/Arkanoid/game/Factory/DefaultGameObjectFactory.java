package com.Arkanoid.game.Factory;

import com.Arkanoid.game.Model.Ball;
import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.Bullet;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.Group;

public class DefaultGameObjectFactory implements GameObjectFactory {

    @Override
    public Ball createBall(double x, double y, Group gameRoot) {
        Ball ball = new Ball(x, y, -1);
        ball.setTrailEffect(gameRoot);
        return ball;
    }

    @Override
    public Bricks createBrick(int x, int y, int hitPoint, String type) {
        return new Bricks(
                x,
                y,
                hitPoint, // hitPoint
                GameConfig.BRICK_WIDTH,
                GameConfig.BRICK_HEIGHT,
                type
        );
    }

    @Override
    public Bullet createBullet(double x, double y) {
        return new Bullet(x, y,47,27);
    }
}
