package com.Arkanoid.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL; // Phải import dòng này

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // loadfile
        // load status -> 0 -> mute mus, 1 -> open
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/choose-mode-game-play.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);
        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}