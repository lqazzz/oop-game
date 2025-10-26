package com.Arkanoid.game.Utils;

import com.Arkanoid.game.View.PauseMenu;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GlobalState {
    private static Stage stage;
    private static Parent root;
    private static Scene scene;
    public static Group gameRoot = new Group();
    public static Group pauseMenu = new Group();
    public static Group lostMenu = new Group();
    public static Group wonMenu = new Group();
    private static boolean musicMuted = false;
    private static boolean soundMuted = false;
    private static double lastMusicTime = 0;
    private static boolean[] isBallBought = {true, false, false, false, false, false};
    private static int currentBall = 0;
    private static boolean[] isPadBought = {true, false, false, false, false, false};
    private static int currentPad = 0;
    private static boolean ballMoved = false;
    private static boolean gamePaused = false;
    private static boolean pauseAdded = false;
    private static boolean gameOver = false;
    private static boolean overAdded = false;

    public static void initPauseMenu() {
        GlobalState.pauseMenu = PauseMenu.getPauseMenu();
    }

    public static void initLostMenu() {
        GlobalState.lostMenu = PauseMenu.getLostMenu();
    }



    public static boolean getIsBallBought(int index) {
        return isBallBought[index];
    }

    public static void setIsBallBought(int index) {
        GlobalState.isBallBought[index] = true;
    }

    public static int getCurrentBall() {
        return currentBall;
    }

    public static void setCurrentBall(int currentBall) {
        GlobalState.currentBall = currentBall;
    }

    public static boolean getIsPadBought(int index) {
        return isPadBought[index];
    }

    public static void setIsPadBought(int index) {
        GlobalState.isPadBought[index] = true;
    }

    public static int getCurrentPad() {
        return currentPad;
    }

    public static void setCurrentPad(int currentPad) {
        GlobalState.currentPad = currentPad;
    }

    public static boolean isMusicMuted() {
        return musicMuted;
    }

    public static void setMusicMuted(boolean musicMuted) {
        GlobalState.musicMuted = musicMuted;
    }

    public static boolean isSoundMuted() {
        return soundMuted;
    }

    public static void setSoundMuted(boolean soundMuted) {
        GlobalState.soundMuted = soundMuted;
    }

    public static double getLastMusicTime() {
        return lastMusicTime;
    }

    public static void setLastMusicTime(double lastMusicTime) {
        GlobalState.lastMusicTime = lastMusicTime;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GlobalState.stage = stage;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        GlobalState.root = root;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Parent root) {
        GlobalState.scene = new Scene(root, 1200, 900);
    }

    public static boolean isBallMoved() {
        return ballMoved;
    }

    public static void setBallMoved(boolean ballMoved) {
        GlobalState.ballMoved = ballMoved;
    }

    public static boolean isGamePaused() {
        return gamePaused;
    }

    public static void setGamePaused(boolean gamePaused) {
        GlobalState.gamePaused = gamePaused;
    }

    public static boolean isPauseAdded() {
        return pauseAdded;
    }

    public static void setPauseAdded(boolean pauseAdded) {
        GlobalState.pauseAdded = pauseAdded;
    }

    public static Group getPauseMenu() {
        return pauseMenu;
    }

    public static Group getLostMenu() {
        return lostMenu;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GlobalState.gameOver = gameOver;
    }

    public static boolean isOverAdded() {
        return overAdded;
    }

    public static void setOverAdded(boolean overAdded) {
        GlobalState.overAdded = overAdded;
    }
}