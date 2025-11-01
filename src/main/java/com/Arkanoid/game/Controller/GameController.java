package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.GameState;
import com.Arkanoid.game.Utils.ButtonEffect;
import com.Arkanoid.game.Utils.GlobalState;
import com.Arkanoid.game.View.GameView;
import com.Arkanoid.game.View.PongGameView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameController {
    public static String currentTheme;
    @FXML private AnchorPane rootPane;
    @FXML
    private Group gameGroup;
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
        updateTheme(rootPane);//
        GameState model = new GameState(gameGroup);
        GameView view = new GameView();
        view.render(model);
        // Giờ tạo thêm 1 biến ở globalstate xem đang chơi ch độ nào
        // if(gs.type == 'regular') {
        // GameState model = new GameState(gameGroup);
        // GameView view = new GameView(); /
        // else if(gs.type == 'pong')
        // PongGameState model = new...
        // PongGameView view = new ..



        System.out.println("Ok");
    }
}
