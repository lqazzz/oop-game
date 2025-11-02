package com.Arkanoid.game.Utils;

import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class BallTrailEffect {

    private Group gameRoot;
    private LinkedList<TrailParticle> activeTrails;
    private Image ballImage;
    private static final int MAX_TRAIL_LENGTH = 25;

    private static class TrailParticle {
        ImageView ballView;
        Rectangle colorOverlay;
        int age;
        boolean isFireMode;

        TrailParticle(ImageView ballView, Rectangle colorOverlay, boolean isFireMode) {
            this.ballView = ballView;
            this.colorOverlay = colorOverlay;
            this.age = 0;
            this.isFireMode = isFireMode;
        }
    }

    public BallTrailEffect(Group gameRoot, Image ballImage) {
        this.gameRoot = gameRoot;
        this.ballImage = ballImage;
        this.activeTrails = new LinkedList<>();
    }

    public void addTrail(double x, double y, double width, double height, boolean isFireMode) {
        if (activeTrails.size() >= MAX_TRAIL_LENGTH) {
            removeOldestTrail();
        }

        for (TrailParticle particle : activeTrails) {
            particle.age++;
        }

        ImageView trailView = new ImageView(ballImage);
        trailView.setFitWidth(width);
        trailView.setFitHeight(height);
        trailView.setPreserveRatio(true);
        trailView.setSmooth(true);
        trailView.setCache(true);
        trailView.setLayoutX(x);
        trailView.setLayoutY(y);

        GaussianBlur blur = new GaussianBlur(isFireMode ? 1.2 : 0.8);
        trailView.setEffect(blur);

        if (!gameRoot.getChildren().isEmpty()) {
            gameRoot.getChildren().add(0, trailView);
        } else {
            gameRoot.getChildren().add(trailView);
        }

        Rectangle fireOverlay = null;
        if (isFireMode) {
            fireOverlay = new Rectangle(width, height);

            fireOverlay.setFill(Color.rgb(255, 40, 0, 0.7));
            fireOverlay.setLayoutX(x);
            fireOverlay.setLayoutY(y);
            fireOverlay.setBlendMode(BlendMode.ADD);

            javafx.scene.effect.DropShadow fireGlow = new javafx.scene.effect.DropShadow();
            fireGlow.setColor(Color.rgb(255, 80, 0, 0.9));
            fireGlow.setRadius(12);
            fireGlow.setSpread(0.7);
            fireOverlay.setEffect(fireGlow);

            if (gameRoot.getChildren().size() > 1) {
                gameRoot.getChildren().add(1, fireOverlay);
            } else {
                gameRoot.getChildren().add(fireOverlay);
            }
        }

        updateAllTrailOpacity();

        activeTrails.addLast(new TrailParticle(trailView, fireOverlay, isFireMode));
    }

    private void updateAllTrailOpacity() {
        int total = activeTrails.size();
        int index = 0;

        for (TrailParticle particle : activeTrails) {
            double progress = (double) index / (double) total;

            double maxOpacity = particle.isFireMode ? 0.9 : 0.75;
            double opacity = Math.pow(progress, 2.5) * maxOpacity;

            particle.ballView.setOpacity(opacity);

            double scale = 0.7 + (progress * 0.3);
            particle.ballView.setScaleX(scale);
            particle.ballView.setScaleY(scale);

            if (particle.colorOverlay != null) {
                particle.colorOverlay.setOpacity(opacity * 0.85);
                particle.colorOverlay.setScaleX(scale);
                particle.colorOverlay.setScaleY(scale);
            }

            index++;
        }
    }

    private void removeOldestTrail() {
        if (!activeTrails.isEmpty()) {
            TrailParticle oldest = activeTrails.removeFirst();
            gameRoot.getChildren().remove(oldest.ballView);
            if (oldest.colorOverlay != null) {
                gameRoot.getChildren().remove(oldest.colorOverlay);
            }
        }
    }

    public void cleanupOldTrails() {
        while (!activeTrails.isEmpty()) {
            TrailParticle oldest = activeTrails.getFirst();
            if (oldest.age > MAX_TRAIL_LENGTH) {
                activeTrails.removeFirst();

                gameRoot.getChildren().remove(oldest.ballView);
                if (oldest.colorOverlay != null) {
                    gameRoot.getChildren().remove(oldest.colorOverlay);
                }
            } else {
                break;
            }
        }
    }

    public void clearAll() {
        for (TrailParticle particle : activeTrails) {
            gameRoot.getChildren().remove(particle.ballView);
            if (particle.colorOverlay != null) {
                gameRoot.getChildren().remove(particle.colorOverlay);
            }
        }
        activeTrails.clear();
    }

    public void updateBallImage(Image newImage) {
        this.ballImage = newImage;
    }
}