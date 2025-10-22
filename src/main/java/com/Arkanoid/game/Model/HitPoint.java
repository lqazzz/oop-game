package com.Arkanoid.game.Model;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HitPoint extends GameObject{
    protected Image img;
    protected ImageView view;
    protected Group hitPointGroup = new Group();
    public HitPoint(double x, double y) {
        super(x, y , 40, 30);
        img =  new Image(getClass().getResourceAsStream("/images/Icon/heart.png"));
        view = new ImageView(img);
        view.setFitWidth(40);
        view.setFitHeight(30);
        hitPointGroup.getChildren().add(view);
        hitPointGroup.setLayoutX(x);
        hitPointGroup.setLayoutY(y);
        System.out.println(x + " " + y);
    }
    public Group getHitPointGroup() {
        return hitPointGroup;
    }

    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(img, getX(), getY(), getWidth(), getHeight());
    }
    public void update() {

    }
}
