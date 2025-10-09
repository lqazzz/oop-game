package com.Arkanoid.game.Utils;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;
public class ButtonEffect {
    public static void applyCuteIdle(Button button) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.8), button);
        st.setFromX(1.0);    // scale ban đầu ngang
        st.setFromY(1.0);    // scale ban đầu dọc
        st.setToX(1.1);      // kéo dài ngang 10%
        st.setToY(1.05);     // kéo dài dọc 5%
        st.setCycleCount(ScaleTransition.INDEFINITE); // lặp vô hạn
        st.setAutoReverse(true); // thu nhỏ lại
        st.play();
    }
}