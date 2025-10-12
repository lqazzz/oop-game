package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ball extends MovableObject{
    protected String typeBall;
    public Ball(double x, double y, double radius) {
        super(x, y, GameConfig.DEFAULT_BALL_WIDTH, GameConfig.DEFAULT_BALL_HEIGHT);
        System.out.println(GameConfig.DEFAULT_BALL_WIDTH + " " + GameConfig.DEFAULT_BALL_HEIGHT);

    }
    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        Image img = new Image(getClass().getResourceAsStream("/images/Ball/planet.png"));
        gc.drawImage(img, x, y, width,height);
        System.out.println("Test");
    }
}
