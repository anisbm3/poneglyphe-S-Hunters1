package tn.esprit.controlls;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import tn.esprit.services.ServiceEvenement;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AfficherEventController implements Initializable {


    @FXML
    private TableView<Evenement> eventtable;
    @FXML
    private TableColumn<Evenement, LocalDateTime> DATEEVENT;

    @FXML
    private TableColumn<Evenement, String> DESCEVENT;

    @FXML
    private TableColumn<Evenement, Integer> IDEVENT;

    @FXML
    private TableColumn<Evenement, String> LIEUEVENT;

    @FXML
    private TableColumn<Evenement, String> NOMEVENT;
    //ObservableList<Evenement> events;
    // ObservableList<Evenement> events = FXCollections.observableArrayList(ES1.afficher());

    @FXML
    private final ServiceEvenement ES1 = new ServiceEvenement();
    //@Override

    @FXML
    private Button suppbtn;

    ObservableList<Evenement> events = FXCollections.observableArrayList(ES1.afficher());

    @FXML
    void onsupprimerclicked(ActionEvent event) {
        Evenement selectedEvent = eventtable.getSelectionModel().getSelectedItem();
        ES1.supprimer(selectedEvent);
        // Clear the existing items in the ObservableList
        events.clear();
        // Retrieve the updated list of events from the database
        events.addAll(ES1.afficher());
        // Set the updated list of events to the TableView
        eventtable.setItems(events);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDEVENT.setCellValueFactory(new PropertyValueFactory<>("ID_Event"));
        NOMEVENT.setCellValueFactory(new PropertyValueFactory<>("Nom_Event"));
        DESCEVENT.setCellValueFactory(new PropertyValueFactory<>("Description_Event"));
        LIEUEVENT.setCellValueFactory(new PropertyValueFactory<>("Lieu_Event"));
        DATEEVENT.setCellValueFactory(new PropertyValueFactory<>("Date_Event"));

        ObservableList<Evenement> events = FXCollections.observableArrayList(ES1.afficher());
        eventtable.setItems(events);
    }

}
