package com.Arkanoid.game.Model;

import com.Arkanoid.game.Controller.PaddleController;
import com.Arkanoid.game.Factory.BrickFactory;
import com.Arkanoid.game.Factory.DefaultGameObjectFactory;
import com.Arkanoid.game.Factory.GameObjectFactory;
import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.Arkanoid.game.Utils.GameConfig.BRICK_HEIGHT;
import static com.Arkanoid.game.Utils.GameConfig.BRICK_WIDTH;

public class GameState {
    private Ball ball;
    private Paddle paddle;
    private Paddle paddle2;
    private List<Bricks> bricks = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private List<HitPoint> hps = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private Group gameRoot;
    private PaddleController padControl = new PaddleController();
    private int level = 12;
    private int[][] mapData;

    // Factory
    private GameObjectFactory factory = new DefaultGameObjectFactory();

    public GameState(Group gameRoot) {
        this.gameRoot = gameRoot;

        ball = factory.createBall(
                GameConfig.DEFAULT_BALL_LAYOUT_X,
                GameConfig.DEFAULT_BALL_LAYOUT_Y,
                gameRoot
        );
        balls.add(ball);

        paddle = new Paddle(
                GameConfig.DEFAULT_PADDLE_LAYOUT_X,
                GameConfig.DEFAULT_PADDLE_LAYOUT_Y,
                GameConfig.DEFAULT_PADDLE_WIDTH,
                GameConfig.DEFAULT_PADDLE_HEIGHT
        );
        paddle2 = new Paddle(
                1500,
                GameConfig.DEFAULT_PADDLE_LAYOUT_Y,
                1500,
                GameConfig.DEFAULT_PADDLE_HEIGHT
        );

        loadMapAndCreateBricks();
        createHitPoints();
    }

    /**
     * Load map and create bricks using BrickFactory
     */
    private void loadMapAndCreateBricks() {
        InputStream input = getClass().getResourceAsStream("/maps/" + GlobalState.getLevel() + ".txt");

        if (input == null) {
            System.err.println("Không tìm thấy file maps!");
            return;
        }
        mapData = loadMap(input);
        int startX = 15 + 6 * BRICK_WIDTH;
        int startY = 80;

        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                if (mapData[row][col] == 0) continue;

                int x = startX + col * BRICK_WIDTH;
                int y = startY + row * BRICK_HEIGHT;

                // brick factory
                Bricks brick = BrickFactory.createFromMapData(x, y, mapData[row][col]);
                bricks.add(brick);
            }
        }
    }

    private void createHitPoints() {
        for (int i = 0; i < 3; i++) {
            hps.add(new HitPoint(23 + 40 * i, 838));
        }
    }

    private int[][] loadMap(InputStream inputStream) {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] row = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.toArray(new int[0][]);
    }

    // Getters...
    public List<Ball> getBalls() { return balls; }
    public Ball getBall() { return ball; }
    public List<Bricks> getBricks() { return bricks; }
    public Paddle getPaddle() { return paddle; }
    public Paddle getPaddle2() { return paddle2; }
    public List<PowerUp> getPowerUpList() { return powerUps; }
    public List<HitPoint> getHitPoints() { return hps; }
    public List<Bullet> getBullets() { return bullets; }
    public Group getGameRoot() { return gameRoot; }
    public PaddleController getPadControl() { return padControl; }
    public GameObjectFactory getFactory() { return factory; }
}
