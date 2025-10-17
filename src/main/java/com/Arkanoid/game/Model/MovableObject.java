package com.Arkanoid.game.Model;

import javafx.scene.canvas.GraphicsContext;

public abstract class MovableObject extends GameObject {
    double dx, dy;
    public MovableObject(double x, double y, double width, double height) {
        super(x, y, width ,height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
