package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.GameState;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.List;

public class GameView {
    private WritableImage brickLayer;
    private boolean brickLayerDirty = true;

    public GameView() {
    }
    private static final double FPS = 60.0;
    private static final double INTERVAL = 1_000_000_000 / FPS;
    private long lastUpdate = 0;
    public void render(GameState state) {
        buildBrickLayer(state); // build once at start
        state.getGameRoot().getChildren().add(state.getBall().getBallGroup());
        state.getGameRoot().getChildren().add(state.getPaddle().getPaddleGroup());
        for(Bricks brick : state.getBricks()) {
            state.getGameRoot().getChildren().add(brick.getBrickGroup());
        }
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now - lastUpdate < INTERVAL) return;
                state.getBall().update();
                state.getPadControl().moveWithMouse(state.getPaddle());
                state.getPadControl().moveWithArrows(state.getPaddle());
                state.getPaddle().updatePaddle(state);
                lastUpdate = now;
                for(Bricks brick : state.getBricks()) {
                    if(brick.updateBrick(state)) {
                        state.getBricks().remove(brick);
                        state.getGameRoot().getChildren().remove(brick.getBrickGroup());
                        break;
                    }
                }

            }
        }.start();
    }

    private void renderFrame(GameState state) {
//        gc.setFill(Color.rgb(255, 0, 0, 0.3));
//        gc.fillRect(0, 0, 1200, 900);
//        // Transparent clear each frame
//        gc.setGlobalAlpha(1.0);
//        gc.setFill(Color.TRANSPARENT);
//        gc.clearRect(0, 0, 1200, 900); // important — doesn’t fill opaque
//        gc.fillRect(0, 0, 1200, 900);  // ensures transparent pixels
//
//        // Draw bricks layer (this image must preserve alpha)
//        gc.drawImage(brickLayer, 0, 0);
//
//        // Update dynamic objects
//        for (Bricks brick : state.getBricks()) {
//            if(brick.updateBrick(state)) {
//                break;
//            };
//        }
//        state.getBall().update();
//        state.getBall().render(gc);
//        state.getPaddle().render(gc);
    }

    private void buildBrickLayer(GameState state) {
//        if (!brickLayerDirty) return;
//
//        Canvas offscreen = new Canvas(1200, 900);
//        GraphicsContext bgc = offscreen.getGraphicsContext2D();
//
//        // Clear transparent
//        bgc.setFill(Color.TRANSPARENT);
//        bgc.clearRect(0, 0, 1200, 900);
//        bgc.fillRect(0, 0, 1200, 900);
//
//        // Draw bricks
//        List<Bricks> bricks = state.getBricks();
//        for (Bricks b : bricks) b.render(bgc);
//
//        // Take snapshot (preserve alpha)
//        brickLayer = offscreen.snapshot(null, null);
//
//        brickLayerDirty = false;
    }
}
