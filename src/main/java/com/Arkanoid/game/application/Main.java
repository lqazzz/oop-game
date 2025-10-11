package com.Arkanoid.game.application;

import com.Arkanoid.game.Controller.SoundController;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;



import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/font/PaytoneOne-Regular.ttf"), 20);
        SoundController.playMusic("background.mp3", true);
        GlobalState.setRoot(FXMLLoader.load(getClass().getResource("/fxml/main-page.fxml")));
        GlobalState.setScene(GlobalState.getRoot());
        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(GlobalState.getScene());
        primaryStage.setFullScreen(false);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}