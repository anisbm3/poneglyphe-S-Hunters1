package tn.esprit.controlls;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.ServiceReservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationAfficherController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField nomField;

    @FXML
    private TextField placesField;

    @FXML
    private TextField etatField;

    @FXML
    private ChoiceBox<String> nomRES;

    @FXML
    private VBox reservationsContainer;

    @FXML
    private VBox  EventVBox;

    @FXML
    private Button SuppBtn;

    @FXML
    private TextField searchBar;




    private final ServiceReservation serviceReservation = new ServiceReservation();

    private final ServiceEvenement serviceEvent = new ServiceEvenement();

    private Reservation selectedReservation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshReservations();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshReservations() throws SQLException {
        List<Reservation> reservations = serviceReservation.afficher();
        EventVBox.getChildren().clear();

        for (Reservation reservation : reservations) {
            Label reservationLabel = new Label("Nom: " + reservation.getNom_Reseervation() +
                    ", Places: " + reservation.getNB_Places() +
                    ", État: " + reservation.getEtat() +
                    ", Événement: " + reservation.getNOM_Event() +
                    ", res: " + reservation.getResDate());

            reservationLabel.setOnMouseClicked(event -> {
                selectedReservation = reservation;
                nomField.setText(selectedReservation.getNom_Reseervation());
                placesField.setText(String.valueOf(selectedReservation.getNB_Places()));
                etatField.setText(selectedReservation.getEtat());
                nomRES.setValue(selectedReservation.getNOM_Event());
            }) ;

            EventVBox.getChildren().addAll(reservationLabel);
        }

        nomRES.getItems().clear();
        for (Reservation reservation : reservations) {
            nomRES.getItems().add(reservation.getNOM_Event());
        }

        nomRES.setOnAction(event -> {
            if (selectedReservation != null) {
                selectedReservation.setNOM_Event(nomRES.getValue());
            }
        });
    }

    @FXML
    void onSupprimerClicked(ActionEvent event) {
        if (selectedReservation != null) {
            try {
                int res = selectedReservation.getID_Reservation();
                serviceReservation.supprimer(new Reservation(res, nomField.getText(), Integer.parseInt(placesField.getText()), etatField.getText(), nomRES.getValue()));
                refreshReservations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onModifierClicked() {
        if (selectedReservation != null) {
            try {
                selectedReservation.setNom_Reseervation(nomField.getText());
                selectedReservation.setNB_Places(Integer.parseInt(placesField.getText()));
                selectedReservation.setEtat(etatField.getText());

                String selectedEvent = nomRES.getValue();
                if (selectedEvent != null) {
                    selectedReservation.setNOM_Event(selectedEvent);
                } else {
                    System.out.println("erreur");
                }

                serviceReservation.modifier(selectedReservation);
                refreshReservations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateEventHBox() {
        if (selectedReservation != null) {
            // Clear previous content
            EventVBox.getChildren().clear();


            Label nomLabel = new Label("Nom: " + selectedReservation.getNom_Reseervation());
            Label placesLabel = new Label("Places: " + selectedReservation.getNB_Places());
            Label etatLabel = new Label("État: " + selectedReservation.getEtat());
            Label eventLabel = new Label("Événement: " + selectedReservation.getNOM_Event());


            EventVBox.getChildren().addAll(nomLabel, placesLabel, etatLabel, eventLabel);
        }
    }
}
