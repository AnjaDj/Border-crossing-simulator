package pj2.pj2projektni;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pj2.pj2projektni.incident.Incident;
import pj2.pj2projektni.putnici.Putnik;
import pj2.pj2projektni.simulacija.Simulacija;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncidentiController {
    @FXML
    public ListView<Incident> incidentiLv;
    @FXML
    public ListView<Putnik> putniciLv;
    @FXML
    public VBox putniciVb;

    @FXML
    public void initialize() {
        putniciVb.setVisible(false);
        HBox.setHgrow(putniciVb, Priority.NEVER);
        List<Incident> incidenti = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "incidenti" + File.separator + "incidenti_" + Simulacija.START_TIME + File.separator + "incidenti_S.txt"))){
            for(int i = 0; i < Incident.getCounter(); i++) {
                Incident incident = (Incident) ois.readObject();
                incidenti.add(incident);
                incidentiLv.getItems().add(incident);
            }
        } catch (Exception e) {
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    public void incidentClick(MouseEvent mouseEvent) {
        Incident incident = incidentiLv.getSelectionModel().getSelectedItem();
        if (incident == null)
            return;
        putniciVb.setVisible(true);
        HBox.setHgrow(putniciVb, Priority.ALWAYS);
        putniciLv.getItems().clear();
        if (incident.getIzbaceniPutnici() == null)
            return;
        for (Putnik p : incident.getIzbaceniPutnici())
            putniciLv.getItems().add(p);
    }
}
