package com.Arkanoid.game.Model;

import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends MovableObject {
    protected boolean isDestroyed = false;
    protected Group bulletGroup = new Group();
    protected ImageView view = new ImageView(
            new Image(getClass().getResourceAsStream("/images/default/Power/bullet.png")));
    public Bullet(double x, double y, double width, double height) {
        super(x, y, width, height);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setRotate(90);
        bulletGroup.getChildren().add(view);
        bulletGroup.setLayoutX(x);
        bulletGroup.setLayoutY(y);
    }


    @Override
    public void update() {
        move();
    }

    public void move() {
        bulletGroup.setLayoutY(bulletGroup.getLayoutY() - GameConfig.DEFAULT_SPEED);
    }

    public Group getBulletGroup() {
        return bulletGroup;
    }

    public boolean isDestroyed() {
        if (bulletGroup.getLayoutY() <= 0) {
            return true;
        }
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
