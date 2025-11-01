package com.Arkanoid.game.Utils;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.LinkedList;

public class BallTrailEffect {

    private Group gameRoot;
    private LinkedList<TrailParticle> activeTrails;
    private Image ballImage;
    private static final int MAX_TRAIL_LENGTH = 25;

    private static class TrailParticle {
        ImageView view;
        int age;

        TrailParticle(ImageView view) {
            this.view = view;
            this.age = 0;
        }
    }

    public BallTrailEffect(Group gameRoot, Image ballImage) {
        this.gameRoot = gameRoot;
        this.ballImage = ballImage;
        this.activeTrails = new LinkedList<>();
    }

    public void addTrail(double x, double y, double width, double height, boolean isFireMode) {
        cleanupOldTrails();

        if (activeTrails.size() >= MAX_TRAIL_LENGTH) {
            TrailParticle oldest = activeTrails.removeFirst();
            gameRoot.getChildren().remove(oldest.view);
        }

        for (TrailParticle particle : activeTrails) {
            particle.age++;
        }

        double baseOpacity = isFireMode ? 0.85 : 0.75;

        ImageView trailView = createTrailWithOpacity(
                width,
                height,
                baseOpacity,
                isFireMode
        );

        trailView.setLayoutX(x);
        trailView.setLayoutY(y);

        GaussianBlur blur = new GaussianBlur(isFireMode ? 1.2 : 0.8);
        trailView.setEffect(blur);

        if (!gameRoot.getChildren().isEmpty()) {
            gameRoot.getChildren().addFirst(trailView);
        } else {
            gameRoot.getChildren().add(trailView);
        }

        updateAllTrailOpacity(isFireMode);

        activeTrails.addLast(new TrailParticle(trailView));
    }

    private void updateAllTrailOpacity(boolean isFireMode) {
        int total = activeTrails.size();
        int index = 0;

        for (TrailParticle particle : activeTrails) {
            double progress = (double) index / (double) total;

            double maxOpacity = isFireMode ? 0.9 : 0.75;
            double opacity = Math.pow(progress, 2.5) * maxOpacity;

            particle.view.setOpacity(opacity);

            double scale = 0.7 + (progress * 0.3);
            particle.view.setScaleX(scale);
            particle.view.setScaleY(scale);

            index++;
        }
    }

    private ImageView createTrailWithOpacity(double width, double height, double opacity, boolean isFireMode) {
        ImageView view = new ImageView(ballImage);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        view.setOpacity(opacity);

        return view;
    }

    public void cleanupOldTrails() {
        while (!activeTrails.isEmpty()) {
            TrailParticle oldest = activeTrails.getFirst();
            if (oldest.age > MAX_TRAIL_LENGTH) {
                activeTrails.removeFirst();

                FadeTransition fade = new FadeTransition(Duration.millis(100), oldest.view);
                fade.setToValue(0.0);
                fade.setOnFinished(e -> gameRoot.getChildren().remove(oldest.view));
                fade.play();
            } else {
                break;
            }
        }
    }

    public void clearAll() {
        for (TrailParticle particle : activeTrails) {
            gameRoot.getChildren().remove(particle.view);
        }
        activeTrails.clear();
    }

    public void updateBallImage(Image newImage) {
        this.ballImage = newImage;
    }
}