package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class ShopController extends Scene {
    //For balls
    @FXML ImageView defaultBallSelect;
    @FXML ImageView circuitSelect;
    @FXML ImageView castleSelect;
    @FXML ImageView pumpkinSelect;
    @FXML ImageView vietnamSelect;
    @FXML ImageView baseballSelect;
    @FXML Button circuit;
    @FXML Button castle;
    @FXML Button pumpkin;
    @FXML Button vietnam;
    @FXML Button baseball;
    private final Map<String, Integer> ballMap = Map.of("defaultBall",0,
            "circuit", 1,
            "castle", 2,
            "pumpkin", 3,
            "vietnam", 4,
            "baseball", 5
    );
    //For pads
    @FXML ImageView defaultPadSelect;
    @FXML ImageView aeroSelect;
    @FXML ImageView futureSelect;
    @FXML ImageView seaSelect;
    @FXML ImageView halloweenSelect;
    @FXML ImageView watermelonSelect;
    @FXML Button aero;
    @FXML Button futuristic;
    @FXML Button sea;
    @FXML Button halloween;
    @FXML Button watermelon;
    private final Map<String, Integer> padMap = Map.of("defaultpad",0,
            "aero", 1,
            "futuristic", 2,
            "sea", 3,
            "halloween", 4,
            "watermelon", 5
    );
    @FXML AnchorPane rootPane;
    @FXML ImageView background;
    public static String currentTheme;
    private final Image selectImage = new Image(getClass().getResource("/images/Other/Yes.png").toExternalForm());
    @FXML
    public void initialize() {
        updateTheme(rootPane);
        String url = background.getImage().getUrl();
        if (url != null && !url.contains("/images/default")) {
            for (Node node : rootPane.getChildrenUnmodifiable()) {
                if (node instanceof Text text) {
                    text.setStyle("-fx-fill: white;");
                }
            }
        }
        setCurrentBall();
        setCurrentPad();
    }
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }
    //Balls
    @FXML
    public void getCurrentBall(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        Button clicked = (Button) event.getSource();
        GlobalState.setCurrentBall(ballMap.get(clicked.getId()));
        GlobalState.setCurrentBallPath("/images/Ball/" + clicked.getId() + ".png");
        setCurrentBall();
    }
    public void setCurrentBall() {
        deselectAllBall();
        switch (GlobalState.getCurrentBall()) {
            case 0 -> defaultBallSelect.setImage(selectImage);
            case 1 -> circuitSelect.setImage(selectImage);
            case 2 -> castleSelect.setImage(selectImage);
            case 3 -> pumpkinSelect.setImage(selectImage);
            case 4 -> vietnamSelect.setImage(selectImage);
            case 5 -> baseballSelect.setImage(selectImage);
        };
    }
    public void deselectAllBall() {
        defaultBallSelect.setImage(null);
        circuitSelect.setImage(null);
        castleSelect.setImage(null);
        pumpkinSelect.setImage(null);
        vietnamSelect.setImage(null);
        baseballSelect.setImage(null);
    }
    //Pads
    @FXML
    public void getCurrentPad(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        Button clicked = (Button) event.getSource();
        String padName = clicked.getId().toLowerCase();
        GlobalState.setCurrentPadPath("/images/Paddle/" + padName + "/normal.png");
        GlobalState.setCurrentPad(padMap.get(padName));
        setCurrentPad();
    }
    public void setCurrentPad() {
        deselectAllPad();
        switch (GlobalState.getCurrentPad()) {
            case 0 -> defaultPadSelect.setImage(selectImage);
            case 1 -> aeroSelect.setImage(selectImage);
            case 2 -> futureSelect.setImage(selectImage);
            case 3 -> seaSelect.setImage(selectImage);
            case 4 -> halloweenSelect.setImage(selectImage);
            case 5 -> watermelonSelect.setImage(selectImage);
        };
    }
    public void deselectAllPad() {
        defaultPadSelect.setImage(null);
        aeroSelect.setImage(null);
        futureSelect.setImage(null);
        seaSelect.setImage(null);
        halloweenSelect.setImage(null);
        watermelonSelect.setImage(null);
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