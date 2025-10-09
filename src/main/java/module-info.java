module com.Arkanoid.game {
    // Yêu cầu các thư viện (module) của JavaFX mà project cần dùng
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    opens com.Arkanoid.game.Controller to javafx.fxml;
    opens com.Arkanoid.game to javafx.fxml;
    opens sounds to javafx.media;
    opens fxml to javafx.fxml;

    exports com.Arkanoid.game;
    opens com.Arkanoid.game.Utils to javafx.fxml;
}