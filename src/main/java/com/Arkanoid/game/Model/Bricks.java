package com.Arkanoid.game.Model;
import com.Arkanoid.game.Utils.GameConfig;
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
    public Bricks(int x, int y, int hitPoint,  int width, int height, String typeBrick ) {
        super(x , y , width , height);
        this.hitPoint = hitPoint;
        if(typeBrick == "9") typeBrick = "unbreakable";
        this.typeBrick = typeBrick;
        img = new Image(getClass().getResourceAsStream("/images/Brick/" + typeBrick + ".png"));
        view = new ImageView(img);
        view.setFitWidth(width);
        view.setFitHeight(height);
        brickGroup.getChildren().add(view);
        brickGroup.setLayoutX(x);
        brickGroup.setLayoutY(y);
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
            hitPoint--;
            if((ballBounds.intersects(getBoundsLeft()) && ballBounds.intersects(getBoundsBottom()))) {
                ball.setAngleVertical(isOppositeDirY);
                ball.setAngleHorizontal(!isOppositeDirX);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsRight()) && ballBounds.intersects(getBoundsBottom())) {
                ball.setAngleVertical(isOppositeDirY);
                ball.setAngleHorizontal(isOppositeDirX);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsRight()) && ballBounds.intersects(getBoundsTop())) {
                ball.setAngleVertical(!isOppositeDirY);
                ball.setAngleHorizontal(isOppositeDirX);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsLeft()) && ballBounds.intersects(getBoundsTop())){
                ball.setAngleVertical(!isOppositeDirY);
                ball.setAngleHorizontal(!isOppositeDirX);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsLeft())) {
                ball.setAngleHorizontal(true);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsRight())) {
                ball.setAngleHorizontal(true);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsTop())) {
                ball.setAngleVertical(true);
                return (hitPoint <= 0);
            }
            if(ballBounds.intersects(getBoundsBottom())) {
                ball.setAngleVertical(true);
                return (hitPoint <= 0);
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

    public Group getBrickGroup() {
        return brickGroup;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

}
