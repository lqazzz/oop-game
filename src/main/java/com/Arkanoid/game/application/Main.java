package com.Arkanoid.game.application;

import com.Arkanoid.game.Controller.*;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.net.URL;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
        MainPageController.currentTheme = "default";
        SelectLevelController.currentTheme = "default";
        ThemeController.currentTheme = "default";
        SettingController.currentTheme = "default";
        GameController.currentTheme = "default";
        SoundController.getInstance().playMusic("background.mp3", true);
        Font.loadFont(getClass().getResource("/font/PaytoneOne-Regular.ttf").toExternalForm(), 12);
        GlobalState.initRankingPath();
        GlobalState.setRoot(FXMLLoader.load(getClass().getResource("/fxml/main-page.fxml")));
        GlobalState.setScene(GlobalState.getRoot());
        GlobalState.getScene().getStylesheets().add(getClass().getResource("/fxml/styles.css").toExternalForm());
        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(GlobalState.getScene());
        primaryStage.setFullScreen(false);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}