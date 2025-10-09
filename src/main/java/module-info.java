module org.example.oopgamefull {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.oopgamefull to javafx.fxml;
    exports org.example.oopgamefull;
}