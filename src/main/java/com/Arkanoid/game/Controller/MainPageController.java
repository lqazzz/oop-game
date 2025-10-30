package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Utils.GlobalState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import com.Arkanoid.game.Model.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainPageController extends Scene {
   // @FXML private ImageView mainBGR;
    public static String currentTheme;
    @FXML
    public void quitGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void switchToSetting(ActionEvent event) throws IOException {
        super.switchToSetting(event);
    }
    @FXML
    public void switchToModeGame(ActionEvent event) throws IOException {
        super.switchToModeGame(event);
    }
    @FXML
    public void switchToShop(ActionEvent event) throws IOException {
        super.switchToShop(event);
    }
    public void switchToGuide(ActionEvent event) throws IOException {
        super.switchToGuide(event);
    }

    @FXML private AnchorPane rootPane;

    @FXML
    public void switchToTheme(ActionEvent event) throws IOException {
        super.switchToTheme(event);
    }

    @FXML void initialize() {
        updateTheme(rootPane);
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
}