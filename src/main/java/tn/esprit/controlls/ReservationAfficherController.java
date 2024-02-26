package tn.esprit.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.models.Evenement;
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
    private HBox EventHBox;


    @FXML
    private Button SuppBtn;

    @FXML
    private TextField searchBar;

    // Service for managing reservations
    private final ServiceReservation serviceReservation = new ServiceReservation();

    // Service for managing events
    private final ServiceEvenement serviceEvent = new ServiceEvenement();

    // To store the selected reservation
    private Reservation selectedReservation;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshReservations();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., show error message
        }

    }

    // Method to refresh the list of reservations displayed
    private void refreshReservations() throws SQLException {
        List<Reservation> reservations = serviceReservation.afficher();
        VBox reservationsContainer = new VBox();
        reservationsContainer.setSpacing(10);

        // Populate the list of reservations
        for (Reservation reservation : reservations) {
            Label reservationLabel = new Label("Nom: " + reservation.getNom_Reseervation() +
                    ", Places: " + reservation.getNB_Places() +
                    ", État: " + reservation.getEtat() +
                    ", Événement: " + reservation.getNOM_Event()+
                      ", res: " + reservation.getResDate());

            // Handle mouse click on a reservation label
            reservationLabel.setOnMouseClicked(event -> {
                selectedReservation = reservation; // Update the selected reservation
                // Display the details of the selected reservation in the text fields
                nomField.setText(selectedReservation.getNom_Reseervation());
                placesField.setText(String.valueOf(selectedReservation.getNB_Places()));
                etatField.setText(selectedReservation.getEtat());
                nomRES.setValue(selectedReservation.getNOM_Event());
            });

            reservationsContainer.getChildren().add(reservationLabel);
        }

        scrollPane.setContent(reservationsContainer);

        // Populate the ChoiceBox with event options
        nomRES.getItems().clear();
        for (Reservation reservation : reservations) {
            nomRES.getItems().add(reservation.getNOM_Event());
        }

        // Define a listener for the ChoiceBox selection
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
                // Call the service method to delete the selected reservation
                int res = selectedReservation.getID_Reservation();
                serviceReservation.supprimer(new Reservation(res,nomField.getText(), Integer.parseInt(placesField.getText()),etatField.getText(),nomRES.getValue()));

                // Refresh the list of reservations after deletion
                refreshReservations();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately, e.g., show error message
            }
        }

    }
    // Method invoked when the "Modifier" button is clicked
    @FXML
    void onModifierClicked() {
        if (selectedReservation != null) {
            try {
                // Update reservation details with values from text fields and ChoiceBox
                selectedReservation.setNom_Reseervation(nomField.getText());
                selectedReservation.setNB_Places(Integer.parseInt(placesField.getText()));
                selectedReservation.setEtat(etatField.getText());

                // Check if an event is selected in the ChoiceBox
                String selectedEvent = nomRES.getValue();
                if (selectedEvent != null) {
                    // Set the name of the event to the reservation
                    selectedReservation.setNOM_Event(selectedEvent);
                } else {
                    // Handle the case when no event is selected
                    // You may display an error message or take appropriate action
                }

                // Modify the reservation in the database
                serviceReservation.modifier(selectedReservation);

                // Refresh the list of reservations after modification
                refreshReservations();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately, e.g., show error message
            }
        }
    }
}
