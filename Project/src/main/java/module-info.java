module pj2.pj2projektni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens pj2.pj2projektni to javafx.fxml;
    exports pj2.pj2projektni;
}