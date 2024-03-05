package pj2.pj2projektni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pj2.pj2projektni.terminali.Kontrola;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ostatak.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Simulacija");
        stage2.setScene(scene2);
        stage2.show();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Simulacija");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            stage2.close();
            Kontrola.KRAJ = true;
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}