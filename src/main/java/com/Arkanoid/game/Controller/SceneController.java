package com.Arkanoid.game.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneTransitionController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoundController.playMusic("background.mp3", true);
        // Tải ảnh từ classpath
//        Image backgroundImage = new Image(getClass().getResourceAsStream("/fxml/main.png"));
//
//        // Tạo ảnh nền
//        BackgroundImage background = new BackgroundImage(
//                backgroundImage,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(1.0, 1.0, true, true, false, false)
//        );
//
//        // Đặt ảnh nền cho AnchorPane
//        rootPane.setBackground(new Background(background));
    }
}