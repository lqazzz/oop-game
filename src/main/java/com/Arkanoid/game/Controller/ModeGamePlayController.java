package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.ButtonEffect;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.Arkanoid.game.Model.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModeGamePlayController extends Scene {
    public static String currentTheme;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button settingsButton;
    @FXML private Button pvpButton;
    @FXML private Button scoresButton;
    @FXML
    public void switchToLevel(ActionEvent event) throws IOException {
        super.switchToSelectLevel(event);
    }
    @FXML
    public void switchToPreviousPage(ActionEvent event) throws IOException{
        super.switchToMainPage(event);
    }
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        super.switchToMainPage(event);
    }


    public void updateTheme(Parent parent) {
        if(parent == null) return;
        currentTheme = GlobalState.newTheme;
        for(Node node : parent.getChildrenUnmodifiable()) {
            if(node instanceof ImageView imageView && imageView.getImage() != null) {
                updateImage(imageView);
            }
            if(node instanceof javafx.scene.control.Button button) {
                if(button.getGraphic() instanceof ImageView imageView && imageView.getImage() != null) {
                    updateImage(imageView);
                }
            }
            if(node instanceof Parent childParent) {
                updateTheme(childParent);
            }
        }
    }

    private void updateImage(ImageView imageView) {
        String url = imageView.getImage().getUrl();
        if (url != null && url.contains("/images/")) {
            String newUrl = url.replace("/images/default/", "/images/" + GlobalState.newTheme + "/");
            try {
                imageView.setImage(new Image(newUrl));
            } catch (Exception e) {
            }
        }
    }
    @FXML
    public void initialize() {
        updateTheme(rootPane);//   SoundController.playMusic("background.mp3", true);
        ButtonEffect.applyCuteIdle(settingsButton);
        ButtonEffect.applyCuteIdle(pvpButton);
        ButtonEffect.applyCuteIdle(scoresButton);
    }
}
