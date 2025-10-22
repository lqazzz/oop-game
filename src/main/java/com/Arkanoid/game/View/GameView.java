package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.AnimationTimer;

public class GameView {
    public GameView() {
    }
    private static final double FPS = 60.0;
    private static final double INTERVAL = 1_000_000_000 / FPS;
    private long lastUpdate = 0;
    public void render(GameState state) {
        state.getGameRoot().getChildren().add(state.getBall().getBallGroup());
        state.getGameRoot().getChildren().add(state.getPaddle().getPaddleGroup());
        for(Bricks brick : state.getBricks()) {
            state.getGameRoot().getChildren().add(brick.getBrickGroup());
        }
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                PauseMenu.pause();
                if(GlobalState.isGamePaused()) {
                    if(!GlobalState.isPauseAdded()) {
                        state.getGameRoot().getChildren().add(GlobalState.getPauseMenu());
                        GlobalState.setPauseAdded(true);
                    }
                } else {
                    if(GlobalState.isPauseAdded()) {
                        state.getGameRoot().getChildren().remove(GlobalState.getPauseMenu());
                        GlobalState.setPauseAdded(false);
                    }
                }
                if(!GlobalState.isGamePaused()) {
                    if(now - lastUpdate < INTERVAL) return;
                    state.getBall().update(state);
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
            }
        }.start();
    }
}
