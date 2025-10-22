package com.Arkanoid.game.Utils;

import javafx.animation.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class RippleEffect {
    public static void wave(Pane root, double x, double y) {
        // Fix sound wave where colliding right screen broder cause full circle instead of half
        Circle wave = new Circle(x, y, 0);
        wave.setStroke(new RadialGradient(0, 0, x, y, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.CYAN), new Stop(1, Color.DEEPSKYBLUE)
        ));
        wave.setStrokeWidth(3);
        wave.setFill(Color.TRANSPARENT);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUA);
        glow.setRadius(20);
        glow.setSpread(0.5);
        wave.setEffect(glow);
        root.getChildren().add(wave);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(wave.radiusProperty(), 0), new KeyValue(wave.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(wave.radiusProperty(), 60), new KeyValue(wave.opacityProperty(), 0))
        );
        timeline.setCycleCount(1);
        timeline.setOnFinished(e -> root.getChildren().remove(wave));
        timeline.play();
    }
}
