package com.Arkanoid.game.Model;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);
    public double getX() {return x;}
    public double getY() {return y;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }


}
