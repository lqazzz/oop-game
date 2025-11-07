package com.Arkanoid.game.Factory;

import com.Arkanoid.game.Model.Ball;
import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.Bullet;
import javafx.scene.Group;

public interface GameObjectFactory {
    Ball createBall(double x, double y, Group gameRoot);
    Bricks createBrick(int x, int y, int hitPoint, String type);
    Bullet createBullet(double x, double y);
}