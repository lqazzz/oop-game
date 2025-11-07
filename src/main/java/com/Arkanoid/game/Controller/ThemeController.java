package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class ThemeController extends Scene {
    public static String currentTheme;
    @FXML private ImageView bgr;
    @FXML private  AnchorPane rootPane;
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException{
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }
    @FXML void initialize() {
        updateTheme();
    }
    public void updateTheme() {
      //  super.switchToMainPage();
    }
    public void applyTheme(Parent root, ActionEvent event) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), root);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            try {
                super.switchToMainPage(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }
    @FXML
    public void switchToXmas(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        GlobalState.newTheme = "xmas";
        SoundController.getInstance().playMusic("xmas.mp3" , true);
        applyTheme(rootPane, event);
    }

    @FXML public void  switchToHalloween(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        GlobalState.newTheme = "halloween";
     //  SoundController.stopMusic();
        SoundController.getInstance().playMusic("halloween.mp3" , true);
        applyTheme(rootPane, event);

    }

    @FXML public void switchToDefault(ActionEvent event) throws  IOException {
        SoundController.getInstance().playBtnClick();
        GlobalState.newTheme = "default";
        SoundController.getInstance().playMusic("background.mp3" , true);
        applyTheme(rootPane, event);

    }
}

