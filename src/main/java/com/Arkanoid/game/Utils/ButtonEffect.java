package com.Arkanoid.game.Utils;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;
public class ButtonEffect {
    public static void applyCuteIdle(Button button) {
        ScaleTransition stX = new ScaleTransition(Duration.millis(400), button);
        stX.setFromX(1.0);
        stX.setToX(1.1);
        stX.setCycleCount(2);
        stX.setAutoReverse(true);
        ScaleTransition stY = new ScaleTransition(Duration.millis(400), button);
        stY.setFromY(1.0);
        stY.setToY(1.1);
        stY.setCycleCount(2);
        stY.setAutoReverse(true);
        SequentialTransition sequence = new SequentialTransition(button, stX, stY);
        sequence.setCycleCount(SequentialTransition.INDEFINITE);
        sequence.setInterpolator(Interpolator.EASE_BOTH);
        sequence.play();
        button.setOnMouseEntered(e -> {
            sequence.pause();
        });
        button.setOnMouseExited(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            sequence.play();
        });

    }
}