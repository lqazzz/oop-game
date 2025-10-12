package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Map;

public class ShopController extends Scene {
    //For balls
    @FXML ImageView defaultBallSelect;
    @FXML ImageView basketballSelect;
    @FXML ImageView footballSelect;
    @FXML ImageView planetSelect;
    @FXML ImageView vietnamSelect;
    @FXML ImageView tireSelect;
    @FXML Button buyBasketball;
    @FXML Button buyFootball;
    @FXML Button buyPlanet;
    @FXML Button buyVietnam;
    @FXML Button buyTire;
    @FXML Button basketball;
    @FXML Button football;
    @FXML Button planet;
    @FXML Button vietnam;
    @FXML Button tire;
    private final Map<String, Integer> ballMap = Map.of("defaultBall",0,
            "basketball", 1,
            "football", 2,
            "planet", 3,
            "vietnam", 4,
            "tire", 5
    );
    //For pads
    @FXML ImageView defaultPadSelect;
    @FXML ImageView greenPadSelect;
    @FXML ImageView pinkPadSelect;
    @FXML ImageView tealPadSelect;
    @FXML ImageView lavenderPadSelect;
    @FXML ImageView grayPadSelect;
    @FXML Button buyGreenPad;
    @FXML Button buyPinkPad;
    @FXML Button buyTealPad;
    @FXML Button buyLavenderPad;
    @FXML Button buyGrayPad;
    @FXML Button greenPad;
    @FXML Button pinkPad;
    @FXML Button tealPad;
    @FXML Button lavenderPad;
    @FXML Button grayPad;
    private final Map<String, Integer> padMap = Map.of("defaultpad",0,
            "greenpad", 1,
            "pinkpad", 2,
            "tealpad", 3,
            "lavenderpad", 4,
            "graypad", 5
    );
    private final Image selectImage = new Image(getClass().getResource("/images/Other/Yes.png").toExternalForm());
    @FXML
    public void initialize() {
        setCurrentBall();
        setCurrentPad();
        unlockBall();
        unlockPad();
        GlobalState.setIsBallBought(0);
        GlobalState.setIsPadBought(0);
    }
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        super.switchToMainPage(event);
    }
    //Balls
    @FXML
    public void getCurrentBall(ActionEvent event) throws IOException {
        Button clicked = (Button) event.getSource();
        if(GlobalState.getIsBallBought(ballMap.get(clicked.getId())) == true) {
            GlobalState.setCurrentBall(ballMap.get(clicked.getId()));
            setCurrentBall();
        }
    }
    @FXML
    public void buyBall(ActionEvent event) throws IOException {
        Button clicked = (Button) event.getSource();
        String ballName = clicked.getId().substring(3).toLowerCase();
        if(GlobalState.getIsBallBought(ballMap.get(ballName)) == false) {
            GlobalState.setIsBallBought(ballMap.get(ballName));
            unlockBall();
        }
    }
    public void setCurrentBall() {
        deselectAllBall();
        switch (GlobalState.getCurrentBall()) {
            case 0 -> defaultBallSelect.setImage(selectImage);
            case 1 -> basketballSelect.setImage(selectImage);
            case 2 -> footballSelect.setImage(selectImage);
            case 3 -> planetSelect.setImage(selectImage);
            case 4 -> vietnamSelect.setImage(selectImage);
            case 5 -> tireSelect.setImage(selectImage);
        };
    }
    public void deselectAllBall() {
        defaultBallSelect.setImage(null);
        basketballSelect.setImage(null);
        footballSelect.setImage(null);
        planetSelect.setImage(null);
        vietnamSelect.setImage(null);
        tireSelect.setImage(null);
    }
    public void unlockBall() {
        if(GlobalState.getIsBallBought(ballMap.get("basketball")) == true) {
            basketball.setDisable(false);
            buyBasketball.setDisable(true);
            buyBasketball.setOpacity(0);
        }
        if(GlobalState.getIsBallBought(ballMap.get("football")) == true) {
            football.setDisable(false);
            buyFootball.setDisable(true);
            buyFootball.setOpacity(0);
        }
        if(GlobalState.getIsBallBought(ballMap.get("planet")) == true) {
            planet.setDisable(false);
            buyPlanet.setDisable(true);
            buyPlanet.setOpacity(0);
        }
        if(GlobalState.getIsBallBought(ballMap.get("vietnam")) == true) {
            vietnam.setDisable(false);
            buyVietnam.setDisable(true);
            buyVietnam.setOpacity(0);
        }
        if(GlobalState.getIsBallBought(ballMap.get("tire")) == true) {
            tire.setDisable(false);
            buyTire.setDisable(true);
            buyTire.setOpacity(0);
        }
    }
    //Pads
    @FXML
    public void getCurrentPad(ActionEvent event) throws IOException {
        Button clicked = (Button) event.getSource();
        String padName = clicked.getId().toLowerCase();
        if(GlobalState.getIsPadBought(padMap.get(padName)) == true) {
            GlobalState.setCurrentPad(padMap.get(padName));
            setCurrentPad();
        }
    }
    @FXML
    public void buyPad(ActionEvent event) throws IOException {
        Button clicked = (Button) event.getSource();
        String padName = clicked.getId().substring(3).toLowerCase();
        if(GlobalState.getIsPadBought(padMap.get(padName)) == false) {
            GlobalState.setIsPadBought(padMap.get(padName));
            unlockPad();
        }
    }
    public void setCurrentPad() {
        deselectAllPad();
        switch (GlobalState.getCurrentPad()) {
            case 0 -> defaultPadSelect.setImage(selectImage);
            case 1 -> greenPadSelect.setImage(selectImage);
            case 2 -> pinkPadSelect.setImage(selectImage);
            case 3 -> tealPadSelect.setImage(selectImage);
            case 4 -> lavenderPadSelect.setImage(selectImage);
            case 5 -> grayPadSelect.setImage(selectImage);
        };
    }
    public void deselectAllPad() {
        defaultPadSelect.setImage(null);
        greenPadSelect.setImage(null);
        pinkPadSelect.setImage(null);
        tealPadSelect.setImage(null);
        lavenderPadSelect.setImage(null);
        grayPadSelect.setImage(null);
    }
    public void unlockPad() {
        if(GlobalState.getIsPadBought(padMap.get("greenpad")) == true) {
            greenPad.setDisable(false);
            buyGreenPad.setDisable(true);
            buyGreenPad.setOpacity(0);
        }
        if(GlobalState.getIsPadBought(padMap.get("pinkpad")) == true) {
            pinkPad.setDisable(false);
            buyPinkPad.setDisable(true);
            buyPinkPad.setOpacity(0);
        }
        if(GlobalState.getIsPadBought(padMap.get("tealpad")) == true) {
            tealPad.setDisable(false);
            buyTealPad.setDisable(true);
            buyTealPad.setOpacity(0);
        }
        if(GlobalState.getIsPadBought(padMap.get("lavenderpad")) == true) {
            lavenderPad.setDisable(false);
            buyLavenderPad.setDisable(true);
            buyLavenderPad.setOpacity(0);
        }
        if(GlobalState.getIsPadBought(padMap.get("graypad")) == true) {
            grayPad.setDisable(false);
            buyGrayPad.setDisable(true);
            buyGrayPad.setOpacity(0);
        }
    }
}