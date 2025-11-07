package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bricks extends GameObject {
    protected int hitPoint;
    protected String typeBrick;
    protected Image img;
    protected ImageView view;
    protected Group brickGroup = new Group();
    private int width;
    private int height;
    public Bricks(int x, int y, int hitPoint,  int width, int height, String typeBrick ) {
        super(x , y , width , height);
        this.hitPoint = hitPoint;
        if(typeBrick == "9") typeBrick = "9";
        this.typeBrick = typeBrick;
        img = new Image(getClass().getResourceAsStream("/images/" + GlobalState.newTheme + "/Brick/" + typeBrick + ".png"));
        view = new ImageView(img);
        this.width = width;
        this.height = height;
        view.setFitWidth(width);
        view.setFitHeight(height);
        brickGroup.getChildren().add(view);
        brickGroup.setLayoutX(x);
        brickGroup.setLayoutY(y);
    }

    public void setNewBrick(int typeBrick) {

        img = new Image(getClass().getResourceAsStream("/images/" + GlobalState.newTheme + "/Brick/" + typeBrick + ".png"));
        view = new ImageView(img);
        view.setFitWidth(width);
        view.setFitHeight(height);
        brickGroup.getChildren().remove(view);
        brickGroup.getChildren().add(view);
    }
    public Bounds getBounds(){
        return new BoundingBox(getX(), getY(), getWidth(), getHeight());
    }
    // canh tren
    public Bounds getBoundsTop(){
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(getX(), getY(), getWidth(), 1);
    }
    // canh duoi
    public Bounds getBoundsBottom(){
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(getX(), getY() + getHeight(), getWidth(), 1);
    }
    public Bounds getBoundsLeft() {
        double inset = Math.max(1, Math.round(getWidth() * 0.1));
        return new BoundingBox(getX(), getY(), 1, getY() + getHeight());
    }
    public Bounds getBoundsRight() {
        double inset = Math.max(1, Math.round(getHeight() * 0.15));
        return new BoundingBox(getX() + getWidth(), getY(), 1, getHeight());
    }
    public Bricks(int x, int y, int width, int height ) {
        super(x , y , width , height);
        this.typeBrick = "1";
    }
 //   public void collision(
    public boolean collision(Ball ball) {
        Bounds ballBounds = ball.getBallGroup().getBoundsInParent();
       // System.out.println(getBoundsTop());
        /**
         * Fix angle when collide, make y revert only when hit bottom/top, x revert only when hit left/right
         *
         */
        boolean isOppositeDirX = ball.getVelocityX() < 0;
        boolean isOppositeDirY = ball.getVelocityY() < 0;

        if(ballBounds.intersects((getBounds()))){
            if(!ball.isFireMode()) {
                if(!typeBrick.equals("9")) {
                    hitPoint--;
                    setNewBrick(hitPoint);
                    System.out.println("call");
                }
                if ((ballBounds.intersects(getBoundsLeft()) && ballBounds.intersects(getBoundsBottom()))) {
                    ball.setAngleVertical(isOppositeDirY);
                    ball.setAngleHorizontal(!isOppositeDirX);
                    return true;
                }
                if (ballBounds.intersects(getBoundsRight()) && ballBounds.intersects(getBoundsBottom())) {
                    ball.setAngleVertical(isOppositeDirY);
                    ball.setAngleHorizontal(isOppositeDirX);
                    return true;
                }
                if (ballBounds.intersects(getBoundsRight()) && ballBounds.intersects(getBoundsTop())) {
                    ball.setAngleVertical(!isOppositeDirY);
                    ball.setAngleHorizontal(isOppositeDirX);
                    return true;
                }
                if (ballBounds.intersects(getBoundsLeft()) && ballBounds.intersects(getBoundsTop())) {
                    ball.setAngleVertical(!isOppositeDirY);
                    ball.setAngleHorizontal(!isOppositeDirX);
                    return true;
                }
                if (ballBounds.intersects(getBoundsLeft())) {
                    ball.setAngleHorizontal(true);
                    return true;
                }
                if (ballBounds.intersects(getBoundsRight())) {
                    ball.setAngleHorizontal(true);
                    return true;
                }
                if (ballBounds.intersects(getBoundsTop())) {
                    ball.setAngleVertical(true);
                    return true;
                }
                if (ballBounds.intersects(getBoundsBottom())) {
                    ball.setAngleVertical(true);
                    return true;
                }
            } else {
                if (!typeBrick.equals("9")) {
                    hitPoint = 0;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collision(Bullet bullet) {
        Bounds bulletBounds = bullet.getBulletGroup().getBoundsInParent();
        if(bulletBounds.intersects(getBounds())) {
            if(!typeBrick.equals("9")) {
                bullet.setDestroyed(true);
                hitPoint--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(img, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update() {

    }

    public boolean updateBrick(Ball ball) {
        return collision(ball);
    }

    public boolean updateBrick(Bullet bullet) {
        return collision(bullet);
    }

    public Group getBrickGroup() {
        return brickGroup;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public String getType() {
        return typeBrick;
    }

}
