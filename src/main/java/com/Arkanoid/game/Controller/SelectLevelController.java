package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;
import com.Arkanoid.game.Model.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SelectLevelController extends Scene {
    public static String currentTheme;
    @FXML private AnchorPane rootPane;
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }
    @FXML
    public void switchToPrevious(ActionEvent event) throws IOException{
        SoundController.getInstance().playBtnClick();
        super.switchToModeGame(event);
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
    @FXML
    public void selectedLevel(ActionEvent event) throws IOException{
        SoundController.getInstance().playBtnClick();
        Button selectedButton = (Button)event.getSource();
        int level = Integer.parseInt(selectedButton.getId());
        GlobalState.setLevel(level);
        super.switchToGamePlay(event, selectedButton.getId());
    }
}
