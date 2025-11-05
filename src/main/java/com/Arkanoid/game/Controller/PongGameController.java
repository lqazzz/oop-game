package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.PongGameState;
import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.PauseMenu;
import com.Arkanoid.game.View.PongGameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class PongGameController extends Scene {
    public static String theme = "";
    @FXML public Group gameGroup;

    @FXML
    public void pauseGame(ActionEvent event) throws IOException {
        PauseMenu.addPauseMenu();
    }

    @FXML
    public void initialize() {
        PongGameState model = new PongGameState(gameGroup);
        PongGameView view = new PongGameView();
        view.render(model);
    }
    public void updateTheme(Parent parent) {
//        if (parent == null) return;
//        currentTheme = GlobalState.newTheme;
//        for(Node node : parent.getChildrenUnmodifiable()) {
//            if(node instanceof ImageView imageView && imageView.getImage() != null) {
//                updateImage(imageView);
//            }
//            if(node instanceof javafx.scene.control.Button button) {
//                if(button.getGraphic() instanceof ImageView imageView && imageView.getImage() != null) {
//                    updateImage(imageView);
//                }
//            }
//            if(node instanceof Parent childParent) {
//                updateTheme(childParent);
//            }
        }
    }

