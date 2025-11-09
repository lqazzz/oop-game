package com.Arkanoid.game.Utils;

import com.Arkanoid.game.View.PauseMenu;
import com.Arkanoid.game.application.Main;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class GlobalState {
    private static Stage stage;
    private static Parent root;
    private static Scene scene;
    private static Group pauseMenu = new Group();
    private static Group lostMenu = new Group();
    private static Group wonMenu = new Group();
    private static int lostSignal = 0;

    private static double musicVolume = 100.0;
    private static double soundVolume = 100.0;

    private static boolean musicMuted = false;
    public static String newTheme = "default";
    private static boolean soundMuted = false;
    private static double lastMusicTime = 0;
    private static int currentBall = 0;
    private static String currentBallPath = "/images/Ball/default.png";
    private static String currentPadPath = "/images/Paddle/default/normal.png";
    private static int currentPad = 0;
    private static boolean ballMoved = false;
    private static boolean gamePaused = false;
    private static boolean pauseAdded = false;
    private static int level = 0;
    private static boolean gameOver = false;
    private static boolean gameWon = true;
    private static boolean wonAdded = false;
    private static boolean overAdded = false;
    private static Rectangle leftWallLine;
    private static Rectangle rightWallLine;
    private static Rectangle topWallLine;
    private static String rankingPath;
    private static int score = 0;

    private static Random rand = new Random();

    public static void initRankingPath() {
        rankingPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        StringBuilder sb = new StringBuilder(rankingPath);
        int index = sb.indexOf("/target/classes/");
        if (index != -1) {
            sb.replace(index, index + "/target/classes/".length(), "/src/main/resources/ranking/ranking.txt");
        }
        rankingPath = sb.toString();
    }

    public static String getRankingPath() {
        return rankingPath;
    }

    public static Rectangle getLeftWallLine() {
        return leftWallLine;
    }
    public static void setLeftWallLine(Rectangle leftWallLine) {
        GlobalState.leftWallLine = leftWallLine;
    }
    public static Rectangle getRightWallLine() {
        return rightWallLine;
    }
    public static void setRightWallLine(Rectangle rightWallLine) {
        GlobalState.rightWallLine = rightWallLine;
    }
    public static Rectangle getTopWallLine() {
        return topWallLine;
    }
    public static void setTopWallLine(Rectangle topWallLine) {
        GlobalState.topWallLine = topWallLine;
    }
    public static void setLevel(int level) {
        GlobalState.level = level;
    }
    public static int getLevel() {
        return level;
    }
    public static void initPauseMenu() {
        GlobalState.pauseMenu = PauseMenu.getPauseMenu();
    }
    public static void setLostSignal(int lostSignal) {
        GlobalState.lostSignal = lostSignal;
    }

    public static int getLostSignal() {
       return GlobalState.lostSignal;
    }
    public static void initLostMenu() {
        GlobalState.lostMenu = PauseMenu.getLostMenu();
    }

    public static void initWonMenu() { GlobalState.wonMenu = PauseMenu.getWonMenu(); }

    public static String getCurrentBallPath() {
        return currentBallPath;
    }

    public static void setCurrentBallPath(String currentBallPath) {
        GlobalState.currentBallPath = currentBallPath;
    }

    public static String getCurrentPadPath() {
        return currentPadPath;
    }

    public static void setCurrentPadPath(String currentPadPath) {
        GlobalState.currentPadPath = currentPadPath;
    }

    public static int getCurrentBall() {
        return currentBall;
    }

    public static void setCurrentBall(int currentBall) {
        GlobalState.currentBall = currentBall;
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

    public static Group getWonMenu() { return wonMenu; }

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

    public static boolean isGameWon() {
        return gameWon;
    }

    public static void setGameWon(boolean gameWon) {
        GlobalState.gameWon = gameWon;
    }

    public static boolean isWonAdded() {
        return wonAdded;
    }

    public static void setWonAdded(boolean wonAdded) {
        GlobalState.wonAdded = wonAdded;
    }

    public static Random getRand() {
        return rand;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GlobalState.score = score;
    }

    public static double getMusicVolume() {
        return musicVolume;
    }

    public static void setMusicVolume(double musicVolume) {
        GlobalState.musicVolume = musicVolume;
    }

    public static double getSoundVolume() {
        return soundVolume;
    }

    public static void setSoundVolume(double soundVolume) {
        GlobalState.soundVolume = soundVolume;
    }
}