module com.Arkanoid.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires javafx.base;
//    requires com.Arkanoid.game;

    opens com.Arkanoid.game.Controller to javafx.fxml;
    opens com.Arkanoid.game.Model to javafx.fxml;
    opens sounds to javafx.media;
    opens fxml to javafx.fxml;
    opens com.Arkanoid.game.Utils to javafx.fxml;
    exports com.Arkanoid.game.application;
    opens com.Arkanoid.game.application to javafx.fxml;
    opens com.Arkanoid.game.View to javafx.fxml;
}