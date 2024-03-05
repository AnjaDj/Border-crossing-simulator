package pj2.pj2projektni;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.terminali.Kontrola;
import pj2.pj2projektni.vozila.EmptyCollectionException;
import pj2.pj2projektni.vozila.Vozilo;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    @FXML
    public StackPane c1Sp;
    @FXML
    public StackPane p1SP;
    @FXML
    public StackPane p2Sp;
    @FXML
    public StackPane pkSp;
    @FXML
    public StackPane ckSp;
    @FXML
    public GridPane mainGrid;
    @FXML
    public Label timeLbl;
    @FXML
    public Button pauzaBtn;
    @FXML
    public StackPane ct1Sp;
    @FXML
    public StackPane pt1Sp;
    @FXML
    public StackPane pt2Sp;
    @FXML
    public StackPane cktSp;
    @FXML
    public StackPane pktSp;
    @FXML
    public ListView<String> dogadjajiLv;
    private StackPane[] staza;
    private static final int VRIJEME_PAUZE = 1000;
    private boolean pauza = true;

    public MainController() {
        Consumer<Integer> ukloniVoziloSaPolja = index -> Platform.runLater(() -> ukloniSaPolja(staza[index]));

        BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje = (vozilo, index) -> Platform.runLater(() -> postaviNaPolje(vozilo, staza[index]));

        BiConsumer<Vozilo, Integer> voziloNaPT = (vozilo, index) -> Platform.runLater(() -> {
            StackPane pt = index.equals(1) ? p1SP : (index.equals(2) ? p2Sp : pkSp);
            postaviNaPolje(vozilo, pt);
        });

        Consumer<Integer> ukloniVoziloSaPT = index -> Platform.runLater(() -> {
            StackPane pt = index.equals(1) ? p1SP : (index.equals(2) ? p2Sp : pkSp);
            ukloniSaPolja(pt);
        });

        BiConsumer<Vozilo, Integer> voziloNaCT = (vozilo, index) -> Platform.runLater(() -> {
            StackPane ct = index.equals(1) ? c1Sp : ckSp;
            postaviNaPolje(vozilo, ct);
        });

        Consumer<Integer> ukloniVoziloSaCT = index -> Platform.runLater(() -> {
            StackPane ct = index.equals(1) ? c1Sp : ckSp;
            ukloniSaPolja(ct);
        });

        BiConsumer<Integer, Boolean> omoguciPT = (index, omoguci) -> Platform.runLater(() -> {
            StackPane pt = index.equals(1) ? pt1Sp : (index.equals(2) ? pt2Sp : pktSp);
            pt.setStyle("-fx-background-color: " + (omoguci ? " #C1D3EB" : "gray"));
        });

        BiConsumer<Integer, Boolean> omoguciCT = (index, omoguci) -> Platform.runLater(() -> {
            StackPane ct = index.equals(1) ? ct1Sp : cktSp;
            ct.setStyle("-fx-background-color: " + (omoguci ? "#EAF1DD" : "gray"));
        });

        Consumer<String> dodajDogadjaj = tekst -> Platform.runLater(() -> dogadjajiLv.getItems().add(tekst));

        Simulacija.setPomjeriVoziloNaPolje(pomjeriVoziloNaPolje);
        Simulacija.setUkloniVoziloSaPolja(ukloniVoziloSaPolja);
        Simulacija.setVoziloNaPK(voziloNaPT);
        Simulacija.setUkloniVoziloSaPk(ukloniVoziloSaPT);
        Simulacija.setVoziloNaCT(voziloNaCT);
        Simulacija.setUkloniVoziloSaCT(ukloniVoziloSaCT);

        Simulacija.setOmoguciPT(omoguciPT);
        Simulacija.setOmoguciCT(omoguciCT);

        Simulacija.setDodajDogadjaj(dodajDogadjaj);
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
        staza = mainGrid.getChildren().toArray(new StackPane[0]);

        Thread timer = new Thread(() -> {
            int h = 0, m = 0, s = 0;
            while (true) {
                if (Kontrola.PAUZA){
                    synchronized (Kontrola.LOCK){
                        try {
                            Kontrola.LOCK.wait();
                        } catch (InterruptedException e) {
                            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                        }
                    }
                }
                s++;
                if (s == 60) {
                    m++;
                    s = 0;
                }
                if (m == 60) {
                    h++;
                    m = 0;
                }
                try {
                    Thread.sleep(VRIJEME_PAUZE);
                } catch (InterruptedException e) {
                    Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                }
                String time = String.format("%02d:%02d:%02d", h, m, s);
                Platform.runLater(() -> timeLbl.setText("Vrijeme trajanja simulacije: " + time));
            }
        });
        timer.setDaemon(true);
        timer.start();

        new Thread(() -> {
            try {
                Simulacija.main(new String[] {});
            } catch (EmptyCollectionException e) {
                Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
            }
        }).start();
    }

    public void incidentiClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("incidenti.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Incidenti");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    public void pauzaClick(ActionEvent actionEvent) {
        Kontrola.PAUZA = pauza;
        pauzaBtn.setText(pauza ? "Nastavak" : "Pauza");
        pauza = !pauza;
    }
}
