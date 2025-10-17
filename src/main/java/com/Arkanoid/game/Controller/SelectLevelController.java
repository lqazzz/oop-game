package com.Arkanoid.game.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import com.Arkanoid.game.Model.Scene;
public class SelectLevelController extends Scene {
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        super.switchToMainPage(event);
    }
    @FXML
    public void switchToPrevious(ActionEvent event) throws IOException{
        super.switchToModeGame(event);
    }
    @FXML
    public void selectedLevel(ActionEvent event) throws IOException{
        Button selectedButton = (Button)event.getSource();
        String level = selectedButton.getId();
        super.switchToGamePlay(event, level);
    }
}
