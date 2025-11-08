package com.Arkanoid.game.View;

import com.Arkanoid.game.Utils.GameConfig;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ranking {
    protected static Group rankingGroup;
    protected static Rectangle overlay = new Rectangle(
            GameConfig.DEFAULT_SCREEN_WIDTH,
            GameConfig.DEFAULT_SCREEN_HEIGHT,
            Color.BLACK
    );
    protected static Text top1;
    protected static Text top2;
    protected static Text top3;
    protected static Text top4;
    protected static Text top5;
    protected static List<Text> tops = new ArrayList<>(Arrays.asList(top1, top2, top3, top4, top5));
    protected static final int startX = 200;
    protected static final int startY = 200;
    public static void initRanking() {
        GlobalState.getScene().getStylesheets().add(Ranking.class.getResource(
                "/fxml/styles.css").toExternalForm()
        );
        overlay.setOpacity(0.5);
        Scanner sc = new Scanner("/ranking/ranking.txt");
        List<String> ranking = new ArrayList<>();
        int space = 40;
        rankingGroup.getChildren().clear();
        for (int i = 0; i < tops.size(); ++i) {
            tops.get(i).setText(sc.nextLine());
            tops.get(i).setLayoutX(startX + space * i);
            tops.get(i).setLayoutY(startY);
            rankingGroup.getChildren().add(tops.get(i));
        }
    }
    
    public Group getRankingGroup() {
        return rankingGroup;
    }
}
