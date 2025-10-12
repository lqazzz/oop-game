package com.Arkanoid.game.Model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bricks extends GameObject{
    protected String typeBrick;
    public Bricks(int x, int y, int width, int height, String typeBrick ) {
        super(x , y , width , height);
        if(typeBrick == "9") typeBrick = "unbreakable";
        this.typeBrick = typeBrick;
    }
    public Bricks(int x, int y, int width, int height ) {
        super(x , y , width , height);
        this.typeBrick = "1";
    }
    @Override
    public void render(GraphicsContext gc) {
        System.out.println("/images/Brick/" + typeBrick + ".png");
        Image img = new Image(getClass().getResourceAsStream("/images/Brick/" + typeBrick + ".png"));
        gc.drawImage(img, x, y, width,height);
        System.out.println("Test");
    }

    @Override
    public void update() {

    }
}
