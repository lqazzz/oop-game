package com.Arkanoid.game.Model;

import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends MovableObject {
    protected int typeIdx;
    protected String[] powerType = {"bulletPower.png", "fireBall.png", "heartBonus.png", "paddleIncrease.png",
            "shieldProtect.png", "slowDown.png", "speedUp.png", "speedUp.png", "threeBall.png"};
    protected int hitPoint;
    protected String typeBrick;
    protected Image img;
    protected int typePowerup;
    protected ImageView view;
    protected Group powerUpGroup = new Group();
    public PowerUp(double x, double y) {
        super(x, y, GameConfig.BRICK_WIDTH, GameConfig.BRICK_HEIGHT);
        dx = GameConfig.DEFAULT_SPEED;//left to right
        dy = GameConfig.DEFAULT_SPEED;//left to right
        setLayoutX(x);
        setLayoutY(y);
    }

    public void render() {

    }

    public void getRandomPowerUp(GameState state) {
        int randomPowerUp = (int)Math.random() * 9;
        if(powerUpGroup.getChildren().isEmpty()) {
            img = new Image(getClass().getResourceAsStream("/images/default/Power/" + powerType[randomPowerUp]));
            view = new ImageView(img);
            view.setFitHeight(GameConfig.BRICK_HEIGHT);
            view.setFitWidth(GameConfig.BRICK_WIDTH);
            view.setLayoutX(getLayoutX());
            view.setLayoutY(getLayoutY());
            powerUpGroup.getChildren().add(view);
            this.typePowerup = randomPowerUp;
        }
    }
    @Override
    public void update() {

    }
    public Group getPowerUpGroup() {
        return powerUpGroup;
    }
    public void move() {
        powerUpGroup.setLayoutY(powerUpGroup.getLayoutY() + GameConfig.DEFAULT_SPEED);
    }

    public boolean isDestroyed() {
        return powerUpGroup.getLayoutY() >= 850;
    }
}
