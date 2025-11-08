package com.Arkanoid.game.View;

import com.Arkanoid.game.Controller.PauseMenuController;
import com.Arkanoid.game.Controller.RankingController;
import com.Arkanoid.game.Controller.SoundController;
import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Model.PongGameState;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PauseMenu {
    static PauseMenuController pauseMenuController = new PauseMenuController();
    protected static Rectangle overlay = new Rectangle(GameConfig.DEFAULT_SCREEN_WIDTH, GameConfig.DEFAULT_SCREEN_HEIGHT, Color.BLACK);
    protected static Group pauseMenu = new Group();
    protected static Group lostMenu = new Group();

    protected static Group wonMenu = new Group();
    protected static Group popUpBackground = new Group();
    protected static Image popUpImg;
    protected static Image continueImg;
    protected static Image homeImg;
    protected static ImageView popUpView;
    protected static Image backImg;
    protected static ImageView continueView;
    protected static ImageView backView;
    protected static ImageView homeView;
    protected static Button continueBtn = new Button();
    protected static Button backBtn = new Button();
    protected static Button homeBtn = new Button();
    protected static Text title;
    protected static TextField nameInput = new TextField();

    public PauseMenu() {
    }
    public static Group getPauseMenu() {
        GlobalState.getScene().getStylesheets().add(PauseMenu.class.getResource("/fxml/styles.css").toExternalForm());
        pauseMenu.getChildren().clear();
        if (pauseMenu.getChildren().isEmpty()) {
            title = new Text("Game paused");
            overlay.setOpacity(0.5);
            pauseMenu.getChildren().add(overlay);
            pauseMenu.getChildren().add(getPopUpBackground());
            pauseMenu.getChildren().add(getBackBtn());
            pauseMenu.getChildren().add(getContinueBtn());
            pauseMenu.getChildren().add(getTitleText());
        }
        return pauseMenu;
    }
    public static Group getLostMenu() {
        GlobalState.getScene().getStylesheets().add(PauseMenu.class.getResource("/fxml/styles.css").toExternalForm());
        lostMenu.getChildren().clear();
        if (lostMenu.getChildren().isEmpty()) {
            if (GlobalState.getLostSignal() == 0) {
                title = new Text("You lost :((");
            } else if (GlobalState.getLostSignal() == -1) {
                title = new Text("A won");
            } else if (GlobalState.getLostSignal() == 1) {
                title = new Text("B won");
            }
            overlay.setOpacity(0.5);
            lostMenu.getChildren().add(overlay);
            lostMenu.getChildren().add(getPopUpBackground());
            lostMenu.getChildren().add(getBackBtn());
            lostMenu.getChildren().add(getHomeBtn());
            lostMenu.getChildren().add(getTitleText());
        }
        return lostMenu;
    }

    public static Group getWonMenu() {
        GlobalState.getScene().getStylesheets().add(PauseMenu.class.getResource("/fxml/styles.css").toExternalForm());
        wonMenu.getChildren().clear();
        if (wonMenu.getChildren().isEmpty()) {
            title = new Text("You won!!!");
            overlay.setOpacity(0.5);
            wonMenu.getChildren().add(overlay);
            wonMenu.getChildren().add(getPopUpBackground());
            wonMenu.getChildren().add(getTitleText());
            wonMenu.getChildren().add(getHomeBtn());
            if (GlobalState.getLevel() == 12) {
                homeBtn.setLayoutX(500);
                wonMenu.getChildren().add(getNameInput());
            } else {
                wonMenu.getChildren().add(getBackBtn());
            }
        }
        return wonMenu;
    }

    public static void pause() {
        GlobalState.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                SoundController.getInstance().playBtnClick();
                System.out.println("Handle");
                if (GlobalState.isGameOver() || GlobalState.isGameWon()) {
                    return;
                }
                if (!GlobalState.isGamePaused()) {
                    GlobalState.initPauseMenu();
                }
                GlobalState.setGamePaused(!GlobalState.isGamePaused());
            }
        });
    }

    public static void back(Timeline timeline) {
        getBackBtn().setOnAction(e -> {
            try {
                SoundController.getInstance().playBtnClick();
                if (timeline != null) {
                    timeline.stop();
                }
                GlobalState.setGamePaused(false);
                GlobalState.setPauseAdded(false);
                GlobalState.setOverAdded(false);
                GlobalState.setGameOver(false);
                GlobalState.setBallMoved(false);
                GlobalState.setWonAdded(false);
                GlobalState.setGameWon(false);
                pauseMenuController.switchToSelectLevel(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void backPongGame(Timeline timeline) {
        getBackBtn().setOnAction(e -> {
            try {
                SoundController.getInstance().playBtnClick();
                if (timeline != null) {
                    timeline.stop();
                }
                GlobalState.setGamePaused(false);
                GlobalState.setPauseAdded(false);
                GlobalState.setOverAdded(false);
                GlobalState.setGameOver(false);
                GlobalState.setBallMoved(false);
                GlobalState.setWonAdded(false);
                GlobalState.setGameWon(false);
                pauseMenuController.switchToModeGame(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void unPause(GameState state) {
        getContinueBtn().setOnAction(e -> {
            SoundController.getInstance().playBtnClick();
            pauseMenuController.unPauseGame(state);
        });
    }

    public static void unPause(PongGameState state) {
        getContinueBtn().setOnAction(e -> {
            SoundController.getInstance().playBtnClick();
            pauseMenuController.unPauseGame(state);
        });
    }

    public static void saveAndBackHome(Timeline timeline) {
        homeBtn.setOnAction(e -> {
            try {
                SoundController.getInstance().playBtnClick();
                if (timeline != null) {
                    timeline.stop();
                }
                if(GlobalState.getLevel() == 12) {
                    System.out.println("Nig");
                    if (!nameInput.getText().isEmpty()) {
                        RankingController.updateRanking(nameInput.getText() + " " + GlobalState.getScore());
                    } else {
                        String dateFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        String timeFormat = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        RankingController.updateRanking(dateFormat + " " + timeFormat + " " + GlobalState.getScore());
                        GlobalState.setScore(0);
                    }
                }
                GlobalState.setGamePaused(false);
                GlobalState.setPauseAdded(false);
                GlobalState.setOverAdded(false);
                GlobalState.setGameOver(false);
                GlobalState.setBallMoved(false);
                GlobalState.setWonAdded(false);
                GlobalState.setGameWon(false);
                pauseMenuController.switchToMainPage(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static Button getBackBtn() {
        backImg = new Image(PauseMenu.class.getResourceAsStream("/images/Icon/back.png"));
        backView = new ImageView(backImg);
        backView.setFitWidth(150);
        backView.setFitHeight(150);
        backBtn.setGraphic(backView);
        backBtn.setLayoutX(375);
        backBtn.setLayoutY(460);
        backBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return backBtn;
    }

    public static Button getContinueBtn() {
        continueImg = new Image(PauseMenu.class.getResourceAsStream("/images/Icon/continue.png"));
        continueView = new ImageView(continueImg);
        continueView.setFitWidth(150);
        continueView.setFitHeight(150);
        continueBtn.setGraphic(continueView);
        continueBtn.setLayoutX(650);
        continueBtn.setLayoutY(460);
        continueBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return continueBtn;
    }

    public static Group getPopUpBackground() {
        popUpImg = new Image(PauseMenu.class.getResourceAsStream("/images/Notification/board2.png"));
        popUpView = new ImageView(popUpImg);
        popUpView.setLayoutX(131);
        popUpView.setLayoutY(154);
        popUpBackground.getChildren().add(popUpView);
        return popUpBackground;
    }

    public static Text getTitleText() {
        title.setLayoutX(445);
        title.setLayoutY(311);
        title.setScaleX(2);
        title.setScaleY(2);
        return title;
    }

    public static TextField getNameInput() {
        nameInput.setLayoutX(325);
        nameInput.setLayoutY(325);
        nameInput.setPrefHeight(100);
        nameInput.setPrefWidth(500);
        nameInput.setPromptText("Enter your name:");
        return nameInput;
    }

    public static Button getHomeBtn() {
        homeImg = new Image(PauseMenu.class.getResourceAsStream("/images/Icon/home.png"));
        homeView = new ImageView(homeImg);
        homeView.setFitWidth(150);
        homeView.setFitHeight(150);
        homeBtn.setGraphic(homeView);
        homeBtn.setLayoutX(650);
        homeBtn.setLayoutY(460);
        homeBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return homeBtn;
    }

    public static void addPauseMenu() {
        if (!GlobalState.isGamePaused()) {
            GlobalState.initPauseMenu();
        }
        GlobalState.setGamePaused(!GlobalState.isGamePaused());
    }

}
