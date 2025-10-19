package com.Arkanoid.game.View;

import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PauseMenu {
    protected static Rectangle overlay = new Rectangle(GameConfig.DEFAULT_SCREEN_WIDTH, GameConfig.DEFAULT_SCREEN_HEIGHT, Color.BLACK);
    protected static Group root = new Group();
    protected static Group popUpBackground = new Group();
    protected static Image popUpImg;
    protected static ImageView popUpView;
    public PauseMenu() {
    }
    public static Group getRoot() {
        if(root.getChildren().isEmpty()) {
            popUpImg = new Image(PauseMenu.class.getResourceAsStream("/images/Notification/board2.png"));
            popUpView = new ImageView(popUpImg);
            popUpBackground.getChildren().add(popUpView);
            overlay.setOpacity(0.5);
            root.getChildren().add(overlay);
            root.getChildren().add(popUpBackground);
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
}
