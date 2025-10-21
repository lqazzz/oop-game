package com.Arkanoid.game.View;

import com.Arkanoid.game.Controller.PauseMenuController;
import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class PauseMenu {
    static PauseMenuController pauseMenuController = new PauseMenuController();
    protected static Rectangle overlay = new Rectangle(GameConfig.DEFAULT_SCREEN_WIDTH, GameConfig.DEFAULT_SCREEN_HEIGHT, Color.BLACK);
    protected static Group root = new Group();
    protected static Group popUpBackground = new Group();
    protected static Image popUpImg;
    protected static Image replayImg;
    protected static Image continueImg;
    protected static ImageView popUpView;
    protected static Image backImg;
    protected static ImageView replayView;
    protected static ImageView continueView;
    protected static ImageView backView;
    protected static Button replayBtn = new Button();
    protected static Button continueBtn = new Button();
    protected static Button backBtn = new Button();
    protected static Text pauseText = new Text("Game paused");

    public PauseMenu() {
    }
    public static Group getRoot() {
        GlobalState.getScene().getStylesheets().add(PauseMenu.class.getResource("/fxml/styles.css").toExternalForm());
        if(root.getChildren().isEmpty()) {
            popUpImg = new Image(PauseMenu.class.getResourceAsStream("/images/Notification/board2.png"));
            popUpView = new ImageView(popUpImg);
            popUpView.setLayoutX(131);
            popUpView.setLayoutY(154);
            popUpBackground.getChildren().add(popUpView);
            overlay.setOpacity(0.5);
            root.getChildren().add(overlay);
            root.getChildren().add(popUpBackground);
            root.getChildren().add(getReplayBtn());
            root.getChildren().add(getBackBtn());
            root.getChildren().add(getContinueBtn());
            root.getChildren().add(getPauseText());

        }
        return root;
    }
    public static void pause() {
        GlobalState.getScene().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE) {
                if(!GlobalState.isGamePaused()) {
                    GlobalState.initPauseMenu();
                }
                GlobalState.setGamePaused(!GlobalState.isGamePaused());
            }
        });
    }

    public static void back(Timeline timeline) {
        getBackBtn().setOnAction(e -> {
            try {
                if (timeline != null) {
                    timeline.stop();
                }
                GlobalState.setGamePaused(false);
                GlobalState.setPauseAdded(false);
                pauseMenuController.switchToSelectLevel(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void unPause(GameState state) {
        getContinueBtn().setOnAction(e -> {
            pauseMenuController.unPauseGame(state);
        });
    }

    public static Button getReplayBtn() {
        replayImg = new Image(PauseMenu.class.getResourceAsStream("/images/Icon/replay.png"));
        replayView = new ImageView(replayImg);
        replayView.setFitWidth(150);
        replayView.setFitHeight(150);
        replayBtn.setGraphic(replayView);
        replayBtn.setLayoutX(785);
        replayBtn.setLayoutY(460);
        replayBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return replayBtn;
    }
    public static Button getBackBtn() {
        backImg = new Image(PauseMenu.class.getResourceAsStream("/images/Icon/back.png"));
        backView = new ImageView(backImg);
        backView.setFitWidth(150);
        backView.setFitHeight(150);
        backBtn.setGraphic(backView);
        backBtn.setLayoutX(266);
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
        continueBtn.setLayoutX(525);
        continueBtn.setLayoutY(460);
        continueBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return continueBtn;
    }

    public static Text getPauseText() {
        pauseText.setLayoutX(435);
        pauseText.setLayoutY(311);
        pauseText.setScaleX(2);
        pauseText.setScaleY(2);
        return pauseText;
    }
}
