package pj2.pj2projektni;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.vozila.Vozilo;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class OstatakController {
    @FXML
    public GridPane stazaGrid;
    private StackPane[] staza;
    private static final int VELICINA = 45;

    public OstatakController() {
        Consumer<Integer> ukloniVoziloSaPolja = index -> Platform.runLater(() -> ukloniSaPolja(staza[index]));
        BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje = (vozilo, index) -> Platform.runLater(() -> postaviNaPolje(vozilo, staza[index]));

        Simulacija.setPomjeriVoziloNaPoljeOstatak(pomjeriVoziloNaPolje);
        Simulacija.setUkloniVoziloSaPoljaOstatak(ukloniVoziloSaPolja);
    }

    private void ukloniSaPolja(StackPane polje) {
        polje.getChildren().remove(0);
    }

    private void postaviNaPolje(Vozilo vozilo, StackPane polje) {
        Label labela;
        if (polje.getChildren().size() > 0)
            labela = (Label) polje.getChildren().get(0);
        else {
            labela = new Label();
            polje.getChildren().add(labela);
        }
        labela.setText(vozilo.getOznaka() + " " + vozilo.getID());
        labela.setTextFill(Color.WHITE);
        labela.setAlignment(Pos.CENTER);
        labela.prefWidthProperty().bind(polje.widthProperty());
        labela.prefHeightProperty().bind(polje.heightProperty());
        labela.setStyle("-fx-background-color: " + vozilo.getBoja());

        labela.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, vozilo.opisVozila());
            alert.setTitle("Detalji vozila");
            alert.show();
        });
    }

    @FXML
    public void initialize() {
        staza = new StackPane[VELICINA];

        for (int i = 0; i < VELICINA; i++) {
            staza[i] = new StackPane();
            staza[i].setStyle("-fx-pref-width: 90; -fx-max-width: 90; -fx-pref-height: 50; -fx-max-height: 50");
            stazaGrid.add(staza[i], 0, i);
        }
    }
}
