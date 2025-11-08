package com.Arkanoid.game.Controller;

import com.Arkanoid.game.Model.Scene;
import com.Arkanoid.game.Utils.GlobalState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankingController extends Scene {
    @FXML Text top1;
    @FXML Text top2;
    @FXML Text top3;
    @FXML Text top4;
    @FXML Text top5;
    @FXML Text top1Score;
    @FXML Text top2Score;
    @FXML Text top3Score;
    @FXML Text top4Score;
    @FXML Text top5Score;
    List<Text> topArr;
    List<Text> topScoreArr;
    @FXML AnchorPane rootPane;
    @FXML ImageView background;
    public static String currentTheme;
    @FXML
    public void initialize() throws IOException {
        topArr = new ArrayList<>(Arrays.asList(top1, top2, top3, top4, top5));
        topScoreArr = new ArrayList<>(Arrays.asList(top1Score, top2Score, top3Score, top4Score, top5Score));
        updateTheme(rootPane);
        System.out.println(GlobalState.getRankingPath());
        File file = new File(GlobalState.getRankingPath());
        int idx = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                StringBuilder name = new StringBuilder();
                for(int i = 0; i < parts.length - 1; ++i) {
                    name.append(parts[i]).append(" ");
                }
                topArr.get(idx).setText(name.toString());
                topScoreArr.get(idx).setText(parts[parts.length-1]);
                idx += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = background.getImage().getUrl();
        if (url != null && !url.contains("/images/default")) {
            for (Node node : rootPane.getChildrenUnmodifiable()) {
                if (node instanceof Text text) {
                    System.out.println(text.getText());
                    text.setStyle("-fx-fill: white;");
                }
            }
        }
    }
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        SoundController.getInstance().playBtnClick();
        super.switchToMainPage(event);
    }

    public void updateTheme(Parent parent) {
        if (parent == null) return;
        currentTheme = GlobalState.newTheme;

        for(Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof ImageView imageView && imageView.getImage() != null) {
                updateImage(imageView);
            }
            if (node instanceof javafx.scene.control.Button button) {
                if (button.getGraphic() instanceof ImageView imageView && imageView.getImage() != null) {
                    updateImage(imageView);
                }
            }
            if (node instanceof Parent childParent) {
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

    public static void updateRanking(String top) throws IOException {
        List<String> tops = new ArrayList<>();
        List<Integer> score = new ArrayList<>();
        InputStream input = RankingController.class.getResourceAsStream("/ranking/ranking.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            for(int i = 0; i < 5; ++i) {
                String line = br.readLine();
                String[] parts = line.trim().split("\\s+");
                tops.add(line);
                score.add(Integer.parseInt(parts[parts.length - 1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String insertScore = top;
        String[] parts = insertScore.trim().split("\\s+");
        int tempScore = Integer.parseInt(parts[parts.length - 1]);
        int index = 0;
        for(int i = 0; i < 5; ++i) {
            if (tempScore >= score.get(i)) {
                break;
            }
            index += 1;
        }
        tops.add(index, insertScore);
        File file = new File(GlobalState.getRankingPath());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write("");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for(int i = 0; i < 5; ++i) {
                bw.write(tops.get(i) + "\n");
            }
        }

    }
}
