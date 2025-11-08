package com.Arkanoid.game.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class GameObject extends Rectangle {

    public GameObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);
}
