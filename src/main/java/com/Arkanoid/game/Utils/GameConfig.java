package com.Arkanoid.game.Utils;

public class GameConfig {
    public static final int DEFAULT_PADDLE_HEIGHT = 28;
    public static final int DEFAULT_PADDLE_WIDTH = 124;
    public static final int DEFAULT_BALL_HEIGHT = 32;
    public static final int DEFAULT_BALL_WIDTH = 32;
    public static final int DEFAULT_PADDLE_LAYOUT_X = (1200 - DEFAULT_PADDLE_WIDTH) / 2;
    public static final int DEFAULT_PADDLE_LAYOUT_Y = (900 - DEFAULT_PADDLE_HEIGHT - 40) - 60;
    public static final int DEFAULT_BALL_LAYOUT_X = (1200 - DEFAULT_BALL_WIDTH) / 2;
    public static final int DEFAULT_BALL_LAYOUT_Y =  DEFAULT_PADDLE_LAYOUT_Y - DEFAULT_BALL_HEIGHT - 10;
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 40;
    public static enum Powerup {
        bulletPower, fireBall, heartBonus, paddleIncrease, shieldProtect, slowDown, speedUp, threeBall
    }
}
