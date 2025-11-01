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
import java.util.List;
import java.util.Scanner;

public class RankingController extends Scene {
    @FXML Text top1;
    @FXML Text top2;
    @FXML Text top3;
    @FXML Text top4;
    @FXML Text top5;
    @FXML AnchorPane rootPane;
    @FXML ImageView background;
    public static String currentTheme;
    @FXML
    public void initialize() throws IOException {
        System.out.println("Gay");
        updateTheme(rootPane);
        File file = new File("/home/khoa/Desktop/idea-IC-252.25557.131/oop-game/src/main/resources/ranking/ranking.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            top1.setText(br.readLine());
            top2.setText(br.readLine());
            top3.setText(br.readLine());
            top4.setText(br.readLine());
            top5.setText(br.readLine());
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
        super.switchToMainPage(event);
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
            if(tempScore >= score.get(i)) {
                break;
            }
            index += 1;
        }
        tops.add(index, insertScore);
        File file = new File("/home/khoa/Desktop/idea-IC-252.25557.131/oop-game/src/main/resources/ranking/ranking.txt");
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
