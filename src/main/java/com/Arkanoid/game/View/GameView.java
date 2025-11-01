package com.Arkanoid.game.View;

import com.Arkanoid.game.Model.*;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Iterator;

public class GameView {
    private static final double TARGET_FPS = 100.0;
    private static final double FRAME_INTERVAL = 1_000_000_000 / TARGET_FPS;

    private long lastUpdate = 0;
    private AnimationTimer animationTimer;
    private Timeline timeline;

    public GameView() {
        GlobalState.setLostSignal(0);
    }


    public void render(GameState state) {
        GlobalState.setLostSignal(0);

        // Add initial objects to the scene
        //   state.getGameRoot().getChildren().add(state.getBall().getBallGroup());
        state.getGameRoot().getChildren().add(state.getPaddle().getPaddleGroup());
        state.getGameRoot().getChildren().add(state.getPaddle2().getPaddleGroup());
        GlobalState.setLostSignal(0);

        for (Bricks brick : state.getBricks()) {
            state.getGameRoot().getChildren().add(brick.getBrickGroup());
        }
        double lineWidth = 3;
        Color defaultLineColor = Color.web("#ADD8E6", 0.3); // Xanh nhạt bán trong suốt
        Rectangle leftWall = new Rectangle(200, 0, lineWidth, GameConfig.DEFAULT_SCREEN_HEIGHT - 88);
        leftWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(leftWall);
        GlobalState.setLeftWallLine(leftWall);
        Rectangle rightWall = new Rectangle(GameConfig.DEFAULT_SCREEN_WIDTH - lineWidth - 200, 0, lineWidth, GameConfig.DEFAULT_SCREEN_HEIGHT - 88);
        rightWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(rightWall);
        GlobalState.setRightWallLine(rightWall);
        Rectangle topWall = new Rectangle(200, 0, GameConfig.DEFAULT_SCREEN_WIDTH - 400, lineWidth);
        topWall.setFill(defaultLineColor);
        state.getGameRoot().getChildren().add(topWall);
        GlobalState.setTopWallLine(topWall);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
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
                        for(HitPoint hp : state.getHitPoints()) {
                            if(state.getGameRoot().getChildren().contains(hp.getHitPointGroup()) == false) {
                                state.getGameRoot().getChildren().add(hp.getHitPointGroup());
                            }
                        }
                        if(GlobalState.isGameOver()) {
                            if(!GlobalState.isOverAdded()) {
                                GlobalState.initLostMenu();
                                state.getGameRoot().getChildren().add(GlobalState.getLostMenu());
                                GlobalState.setOverAdded(true);
                            }
                        } else {
                            if(GlobalState.isOverAdded()) {
                                GlobalState.getLostMenu().getChildren().clear();
                                state.getGameRoot().getChildren().remove(GlobalState.getLostMenu());
                                GlobalState.setOverAdded(false);
                            }
                        }
//                        if(state.getHitPoints().size() == 0) {
//                            System.out.println("Die");
//                            return;
//                        }
                        if(!GlobalState.isGamePaused() && state.getHitPoints().size() > 0) {
                            boolean isDied = false;
                            Iterator<Ball> iteratorBall = state.getBalls().iterator();
                            while(iteratorBall.hasNext()) {
                                Ball ball = iteratorBall.next();
                                if(state.getGameRoot().getChildren().contains(ball.getBallGroup()) == false) {
                                    state.getGameRoot().getChildren().add(ball.getBallGroup());
                                }
                                if(ball.update(state) == true) {
                                    if(state.getBalls().size() == 1) {
                                        ball.resetBall(state);
                                        ball.clearTrail((Pane) GlobalState.getRoot());
                                        isDied = true;
                                    }
                                    else {
                                        state.getGameRoot().getChildren().remove(ball.getBallGroup());
                                        iteratorBall.remove();
                                    }
                                    break;
                                }
                            }
                            state.getPadControl().moveWithWASD(state.getPaddle(), state.getPaddle2());
                            for(int i = 0 ; i < state.getBalls().size(); i++) {
                                state.getPaddle2().updatePaddle(state.getBalls().get(i), state);
                                state.getPaddle().updatePaddle(state.getBalls().get(i), state);

                            }
                            Iterator<PowerUp> iterator = state.getPowerUpList().iterator();
                            while(iterator.hasNext()) {
                                PowerUp powerup = iterator.next();
                                powerup.move();
                                if(!state.getGameRoot().getChildren().contains(powerup.getPowerUpGroup())) {
                                    state.getGameRoot().getChildren().add(powerup.getPowerUpGroup());
                                }
                                if(powerup.isDestroyed()) {
                                    state.getGameRoot().getChildren().remove(powerup.getPowerUpGroup());
                                    iterator.remove();
                                }
                                if(state.getPaddle().updatePaddle(powerup, state)){
                                    state.getGameRoot().getChildren().remove(powerup.getPowerUpGroup());
                                    iterator.remove();
                                }
                            }
                            Iterator<Bullet> bulletIterator = state.getBullets().iterator();
                            while(bulletIterator.hasNext()) {
                                Bullet bullet = bulletIterator.next();
                                if(!state.getGameRoot().getChildren().contains(bullet.getBulletGroup())) {
                                    state.getGameRoot().getChildren().add(bullet.getBulletGroup());
                                }
                                if(bullet.isDestroyed()) {
                                    state.getGameRoot().getChildren().remove(bullet.getBulletGroup());
                                    bulletIterator.remove();
                                }
                                bullet.update();
                            }
                            Iterator<Bricks> brickIterator = state.getBricks().iterator();
                            while(brickIterator.hasNext()) {
                                int collides = 0;
                                Bricks brick = brickIterator.next();
                                for(int i = 0 ; i < state.getBalls().size(); i++) {
                                    if(brick.updateBrick(state.getBalls().get(i))) {
                                        if((int)(Math.random() * 10) == 0) {
                                            PowerUp newPow = new PowerUp(
                                                    brick.getBrickGroup().getLayoutX(),
                                                    brick.getBrickGroup().getLayoutY()
                                            );
                                            newPow.getRandomPowerUp(state);
                                            state.getPowerUpList().add(newPow);
                                        }
                                        state.getGameRoot().getChildren().remove(brick.getBrickGroup());
                                        brickIterator.remove();
                                        collides = 1;
                                        break;
                                    }
                                }
                                for(int i = 0 ; i < state.getBullets().size(); i++) {
                                    if(brick.updateBrick(state.getBullets().get(i))) {
                                        if((int)(Math.random() * 10) == 0) {
                                            PowerUp newPow = new PowerUp(
                                                    brick.getBrickGroup().getLayoutX(),
                                                    brick.getBrickGroup().getLayoutY()
                                            );
                                            newPow.getRandomPowerUp(state);
                                            state.getPowerUpList().add(newPow);
                                        }
                                        state.getGameRoot().getChildren().remove(brick.getBrickGroup());
                                        brickIterator.remove();
                                        collides = 1;
                                        break;
                                    }
                                }
                                if(collides == 1) break;
                            }
                            if(isDied) {
                                System.out.println("remove");
                                System.out.println(state.getHitPoints().getLast());
                                state.getGameRoot().getChildren().remove(state.getHitPoints().getLast().getHitPointGroup());
                                state.getHitPoints().removeLast();
                                if(state.getHitPoints().isEmpty()) {
                                    GlobalState.setGameOver(true);
                                }
                            }
                        } else {
                            PauseMenu.back(timeline);
                            PauseMenu.unPause(state);
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}