package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.GameState;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class GameView {
    private GraphicsContext gc;
    public GameView(GraphicsContext gc) {
        this.gc = gc;
    }
    public void render(GameState state) {
        state.getBall().render(gc);
        state.getPaddle().render(gc);
        List<Bricks> bricks = state.getBricks();
        for(int i = 0; i < bricks.size(); i++) {
            bricks.get(i).render(gc);
        }
    }

}
