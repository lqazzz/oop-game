package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class HowToPlayController extends Scene {
    @FXML AnchorPane rootPane;
    @FXML ImageView background;
    public static String currentTheme;
    @FXML
    public void initialize() {
        updateTheme(rootPane);
        String url = background.getImage().getUrl();
        if (url != null && !url.contains("/images/default")) {
            for (Node node : rootPane.getChildrenUnmodifiable()) {
                if (node instanceof Text text) {
                    System.out.println(text.getText());
                    text.setStyle("-fx-fill: white;");
                }
            }
        }
    }
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }
    public void updateTheme(Parent parent) {
        if (parent == null) return;
        currentTheme = GlobalState.newTheme;

        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof ImageView imageView && imageView.getImage() != null) {
                updateImage(imageView);
            }
            if (node instanceof javafx.scene.control.Button button) {
                if (button.getGraphic() instanceof ImageView imageView && imageView.getImage() != null) {
                    updateImage(imageView);
                }
            }
            if (node instanceof Parent childParent) {
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
                System.out.println("Error");
            }
        }
    }
}
