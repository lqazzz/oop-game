package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.GameView;
import com.Arkanoid.game.View.PauseMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameController {
    public static String currentTheme;
    @FXML private AnchorPane rootPane;
    @FXML
    private Group gameGroup;
    @FXML private Text scores;
    public void pauseGame(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        PauseMenu.addPauseMenu();
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
    @FXML
    public void initialize() {
        updateTheme(rootPane);
        GameState model = new GameState(gameGroup);
        GameView view = new GameView();
        view.render(model, scores);
    }
}
