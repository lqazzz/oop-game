package com.Arkanoid.game.Utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GlobalState {
    private static Stage stage;
    private static Parent root;
    private static Scene scene;

    private static boolean musicMuted = false;
    private static boolean soundMuted = false;
    private static double lastMusicTime = 0;
    private static boolean[] isBallBought = {true, false, false, false, false, false};
    private static int currentBall = 0;
    private static boolean[] isPadBought = {true, false, false, false, false, false};
    private static int currentPad = 0;

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
}