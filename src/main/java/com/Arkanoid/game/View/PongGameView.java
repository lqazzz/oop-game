package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.*;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Iterator;

public class PongGameView {
    private static final double TARGET_FPS = 100.0;
    private static final double FRAME_INTERVAL = 1_000_000_000 / TARGET_FPS;

    private Timeline timeline;

    public PongGameView() {
        GlobalState.setLostSignal(-1);
    }

    public void render(PongGameState state) {
        GlobalState.setLostSignal(-1);
        double lineWidth = 5;
        Color defaultLineColor = Color.web("#ADD8E6", 0.3);
        Rectangle leftWall = new Rectangle(225, 0, lineWidth, GameConfig.DEFAULT_SCREEN_HEIGHT - 88);
        leftWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(leftWall);
        GlobalState.setLeftWallLine(leftWall);
        Rectangle rightWall = new Rectangle(
                GameConfig.DEFAULT_SCREEN_WIDTH - lineWidth - 225,
                0,
                lineWidth,
                GameConfig.DEFAULT_SCREEN_HEIGHT - 88
        );
        rightWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(rightWall);
        GlobalState.setRightWallLine(rightWall);
        Rectangle topWall = new Rectangle(
                200,
                0,
                GameConfig.DEFAULT_SCREEN_WIDTH - 400,
                lineWidth
        );
        topWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(topWall);
        GlobalState.setTopWallLine(topWall);
        state.getGameRoot().getChildren().add(state.getPaddle().getPaddleGroup());
        state.getGameRoot().getChildren().add(state.getPaddle2().getPaddleGroup());

        timeline = new Timeline(
                new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PauseMenu.pause();
                        PauseMenu.saveAndBackHome(timeline);
                        if (GlobalState.isGamePaused()) {
                            if (!GlobalState.isPauseAdded()) {
                                state.getGameRoot().getChildren().add(GlobalState.getPauseMenu());
                                GlobalState.setPauseAdded(true);
                            }
                        } else {
                            if (GlobalState.isPauseAdded()) {
                                GlobalState.getPauseMenu().getChildren().clear();
                                state.getGameRoot().getChildren().remove(GlobalState.getPauseMenu());
                                GlobalState.setPauseAdded(false);
                            }
                        }
                        for (HitPoint hp : state.getHitPoints()) {
                            if (!state.getGameRoot().getChildren().contains(hp.getHitPointGroup())) {
                                state.getGameRoot().getChildren().add(hp.getHitPointGroup());
                            }
                        }
                        if (GlobalState.isGameOver()) {
                            if (!GlobalState.isOverAdded()) {
                                GlobalState.initLostMenu();
                                state.getGameRoot().getChildren().add(GlobalState.getLostMenu());
                                GlobalState.setOverAdded(true);
                            }
                        } else {
                            if (GlobalState.isOverAdded()) {
                                state.getGameRoot().getChildren().remove(GlobalState.getLostMenu());
                                GlobalState.setOverAdded(false);
                            }
                        }

                        if (!GlobalState.isGamePaused() && !state.getHitPoints().isEmpty()) {
                            boolean isDied = false;
                            Iterator<Ball> iteratorBall = state.getBalls().iterator();
                            while(iteratorBall.hasNext()) {
                                Ball ball = iteratorBall.next();
                                if (!state.getGameRoot().getChildren().contains(ball.getBallGroup())) {
                                    state.getGameRoot().getChildren().add(ball.getBallGroup());
                                }
                                int signal = ball.update(state);
                                if (signal != 0) {
                                    if (state.getBalls().size() == 1) {
                                        ball.resetBall(state);
                                        isDied = true;
                                        GlobalState.setLostSignal(signal);
                                    }
                                    else {
                                        state.getGameRoot().getChildren().remove(ball.getBallGroup());
                                        iteratorBall.remove();
                                    }
                                    break;
                                }
                            }

                            state.getPadControl().moveWithWASDMulti(state.getPaddle(), state.getPaddle2());
                            for (int i = 0 ; i < state.getBalls().size(); i++) {
                                state.getPaddle2().updatePaddle(state.getBalls().get(i), state);
                                state.getPaddle().updatePaddle(state.getBalls().get(i), state);

                            }

                            if (isDied) {
                                System.out.println("remove");
                                System.out.println(state.getHitPoints().getLast());

                                state.getGameRoot().getChildren().remove(state.
                                        getHitPoints().
                                        getLast().
                                        getHitPointGroup()
                                );

                                state.getHitPoints().removeLast();
                                if (state.getHitPoints().isEmpty()) {
                                    GlobalState.setGameOver(true);
                                }
                            }

                        } else {
                            PauseMenu.backPongGame(timeline);
                            PauseMenu.unPause(state);
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
