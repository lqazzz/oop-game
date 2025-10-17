package com.Arkanoid.game.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class GameObject extends Rectangle {
//    protected double x;
//    protected double y;
//    protected double width;
//    protected double height;

    public GameObject(double x, double y, double width, double height) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
        super(x, y, width, height);
//        setX(x);
//        setY(y);
//        setWidth(width);
//        setHeight(height);
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);
//    public void setWidth(int width) {
//        this.width = width;
//    }
//    public void setHeight(int height) {
//        this.height = height;
//    }
}
