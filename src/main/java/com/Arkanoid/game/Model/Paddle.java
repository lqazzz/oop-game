package com.Arkanoid.game.Model;
import javafx.scene.canvas.GraphicsContext;
import com.Arkanoid.game.Utils.GameConfig;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Paddle extends MovableObject{
    protected double speed;
    protected List<GameConfig.Powerup> powerups = new ArrayList<>();
    protected String typePaddle;
    public Paddle(double x, double y, int width, int height) {
        super(x , y , width ,height );
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        Image img = new Image(getClass().getResourceAsStream("/images/Paddle/gray.png"));
        gc.drawImage(img, x, y, width,height);
    }
}
