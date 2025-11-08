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
    private LinkedList<TrailParticle> particlePool; // Object pool
    private Image ballImage;
    private static final int MAX_TRAIL_LENGTH = 25;
    private static final int POOL_SIZE = 30; // Kích thước pool

    private static class TrailParticle {
        ImageView ballView;
        Rectangle colorOverlay;
        int age;
        boolean isFireMode;
        boolean inUse;

        TrailParticle(Image ballImage) {
            this.ballView = new ImageView(ballImage);
            this.ballView.setPreserveRatio(true);
            this.ballView.setSmooth(true);
            this.ballView.setCache(true);

            GaussianBlur blur = new GaussianBlur(0.8);
            this.ballView.setEffect(blur);

            this.colorOverlay = new Rectangle();
            this.colorOverlay.setBlendMode(BlendMode.ADD);

            javafx.scene.effect.DropShadow fireGlow = new javafx.scene.effect.DropShadow();
            fireGlow.setColor(Color.rgb(255, 80, 0, 0.9));
            fireGlow.setRadius(12);
            fireGlow.setSpread(0.7);
            this.colorOverlay.setEffect(fireGlow);

            this.age = 0;
            this.isFireMode = false;
            this.inUse = false;
        }

        void reset() {
            this.age = 0;
            this.isFireMode = false;
            this.inUse = false;
            this.ballView.setOpacity(0);
            this.colorOverlay.setOpacity(0);
        }

        void activate(double x, double y, double width, double height, boolean fireMode, Image ballImage) {
            this.inUse = true;
            this.isFireMode = fireMode;
            this.age = 0;

            if (this.ballView.getImage() != ballImage) {
                this.ballView.setImage(ballImage);
            }

            this.ballView.setFitWidth(width);
            this.ballView.setFitHeight(height);
            this.ballView.setLayoutX(x);
            this.ballView.setLayoutY(y);

            if (fireMode) {
                GaussianBlur blur = new GaussianBlur(1.2);
                this.ballView.setEffect(blur);

                this.colorOverlay.setWidth(width);
                this.colorOverlay.setHeight(height);
                this.colorOverlay.setLayoutX(x);
                this.colorOverlay.setLayoutY(y);
                this.colorOverlay.setFill(Color.rgb(255, 40, 0, 0.7));
            } else {
                GaussianBlur blur = new GaussianBlur(0.8);
                this.ballView.setEffect(blur);
            }
        }
    }

    public BallTrailEffect(Group gameRoot, Image ballImage) {
        this.gameRoot = gameRoot;
        this.ballImage = ballImage;
        this.activeTrails = new LinkedList<>();
        this.particlePool = new LinkedList<>();

        initializePool();
    }

    private void initializePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            TrailParticle particle = new TrailParticle(ballImage);
            particlePool.add(particle);
        }
    }

    private TrailParticle getParticleFromPool() {
        if (!particlePool.isEmpty()) {
            return particlePool.removeFirst();
        }
        return new TrailParticle(ballImage);
    }

    private void returnParticleToPool(TrailParticle particle) {
        particle.reset();

        gameRoot.getChildren().remove(particle.ballView);
        if (particle.colorOverlay != null) {
            gameRoot.getChildren().remove(particle.colorOverlay);
        }

        if (particlePool.size() < POOL_SIZE) {
            particlePool.addLast(particle);
        }
    }

    public void addTrail(double x, double y, double width, double height, boolean isFireMode) {
        if (activeTrails.size() >= MAX_TRAIL_LENGTH) {
            removeOldestTrail();
        }

        for (TrailParticle particle : activeTrails) {
            particle.age++;
        }

        TrailParticle particle = getParticleFromPool();
        particle.activate(x, y, width, height, isFireMode, ballImage);

        if (!gameRoot.getChildren().isEmpty()) {
            gameRoot.getChildren().add(0, particle.ballView);
        } else {
            gameRoot.getChildren().add(particle.ballView);
        }

        if (isFireMode && particle.colorOverlay != null) {
            if (gameRoot.getChildren().size() > 1) {
                gameRoot.getChildren().add(1, particle.colorOverlay);
            } else {
                gameRoot.getChildren().add(particle.colorOverlay);
            }
        }

        updateAllTrailOpacity();
        activeTrails.addLast(particle);
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

            if (particle.colorOverlay != null && particle.isFireMode) {
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
            returnParticleToPool(oldest);
        }
    }

    public void clearAll() {
        while (!activeTrails.isEmpty()) {
            TrailParticle particle = activeTrails.removeFirst();
            returnParticleToPool(particle);
        }
    }

    public void updateBallImage(Image newImage) {
        this.ballImage = newImage;

        for (TrailParticle particle : particlePool) {
            particle.ballView.setImage(newImage);
        }
        for (TrailParticle particle : activeTrails) {
            particle.ballView.setImage(newImage);
        }
    }
}