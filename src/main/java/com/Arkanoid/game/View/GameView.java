package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Model.PowerUp;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.AnimationTimer;

public class GameView {
    public GameView() {
    }
    private static final double FPS = 60.0;
    private static final double INTERVAL = 1_000_000_000 / FPS;
    private long lastUpdate = 0;
    private AnimationTimer animationTimer;
    public void render(GameState state) {
        state.getGameRoot().getChildren().add(state.getBall().getBallGroup());
        state.getGameRoot().getChildren().add(state.getPaddle().getPaddleGroup());
        for(Bricks brick : state.getBricks()) {
            state.getGameRoot().getChildren().add(brick.getBrickGroup());
        }
        animationTimer = new AnimationTimer() {
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
                        GlobalState.getPauseMenu().getChildren().clear();
                        state.getGameRoot().getChildren().remove(GlobalState.getPauseMenu());
                        GlobalState.setPauseAdded(false);
                    }
                }
                if(!GlobalState.isGamePaused()) {
                    if(now - lastUpdate < INTERVAL) return;
                    state.getBall().update(state);
                    state.getPadControl().moveWithMouse(state.getPaddle());
//                    state.getPadControl().moveWithArrows(state.getPaddle());
                    state.getPaddle().updatePaddle(state);
                    lastUpdate = now;
                    for(PowerUp powerup: state.getPowerUpList()) {
                        powerup.move();
                        if(state.getGameRoot().getChildren().contains(powerup.getPowerUpGroup()) == false) {
                            System.out.println(state.getPowerUpList().size());
                            state.getGameRoot().getChildren().add(powerup.getPowerUpGroup());
                        }
                        if(powerup.isDestroyed()){
                            state.getGameRoot().getChildren().remove(powerup.getPowerUpGroup());
                        }
                    }
                    state.getPowerUpList().removeIf(PowerUp::isDestroyed);
                    for(Bricks brick : state.getBricks()) {
                        if(brick.updateBrick(state)) {
                            PowerUp newPow = new PowerUp(brick.getBrickGroup().getLayoutX(), brick.getBrickGroup().getLayoutY());
                            state.getPowerUpList().add(newPow);
                            newPow.getRandomPowerUp();
                            state.getBricks().remove(brick);
                            state.getGameRoot().getChildren().remove(brick.getBrickGroup());
                            break;
                        }
                    }
                } else {
                    PauseMenu.back(animationTimer);
                    PauseMenu.unPause(state);
                }
            }
        };
        animationTimer.start();
    }
}
