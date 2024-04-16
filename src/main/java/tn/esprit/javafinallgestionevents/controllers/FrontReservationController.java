


package tn.esprit.javafinallgestionevents.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import tn.esprit.javafinallgestionevents.models.Reservation;
import tn.esprit.javafinallgestionevents.services.ServiceReservation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontReservationController implements Initializable {

    @FXML
    private ChoiceBox<String> EventIdUpdateReservation;

    @FXML
    private VBox ReservationVbox;

    @FXML
    private ScrollPane ScrollPaneReservation;

    @FXML
    private Button deleteReservationBtn;

    @FXML
    private TextField etatReservationUpdate;

    @FXML
    private TextField nbPlacesUpdate;

    @FXML
    private TextField nomReservationUpdate;

    @FXML
    private Button updateReservationBtn;

    @FXML
    private ChoiceBox<String> userIdUpdate;

    private ServiceReservation serviceReservation;
    private Reservation selectedReservation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceReservation = new ServiceReservation();
        loadReservations();
    }

    @FXML
    void OnClickedDeleteReservation(ActionEvent event) {
        if (selectedReservation != null) {
            try {
                serviceReservation.supprimer(selectedReservation);
                loadReservations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void OnClickedReserverUpdate(ActionEvent event) {
        if (selectedReservation != null) {
            try {
                selectedReservation.setNom_reservation(nomReservationUpdate.getText());
                selectedReservation.setNb_place(Integer.parseInt(nbPlacesUpdate.getText()));
                selectedReservation.setEtat(etatReservationUpdate.getText());
                selectedReservation.setUser_id(userIdUpdate.getValue());
                selectedReservation.setEvenement_id(EventIdUpdateReservation.getValue());
                serviceReservation.modifier(selectedReservation);
                loadReservations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadReservations() {
        ReservationVbox.getChildren().clear();
        userIdUpdate.getItems().clear();
        EventIdUpdateReservation.getItems().clear();

        try {
            List<Reservation> reservations = serviceReservation.afficher();
            for (Reservation reservation : reservations) {
                Label reservationLabel = new Label();
                reservationLabel.setText("Reservation ID: " + reservation.getId() +
                        ", Nom: " + reservation.getNom_reservation() +
                        ", Nb Places: " + reservation.getNb_place() +
                        ", État: " + reservation.getEtat() +
                        ", User ID: " + reservation.getUser_id() +
                        ", Événement ID: " + reservation.getEvenement_id());

                reservationLabel.setOnMouseClicked(event -> {
                    selectedReservation = reservation;
                    nomReservationUpdate.setText(reservation.getNom_reservation());
                    nbPlacesUpdate.setText(String.valueOf(reservation.getNb_place()));
                    etatReservationUpdate.setText(reservation.getEtat());
                    userIdUpdate.setValue(reservation.getUser_id());
                    EventIdUpdateReservation.setValue(reservation.getEvenement_id());
                });

                ReservationVbox.getChildren().add(reservationLabel);

                userIdUpdate.getItems().add(reservation.getUser_id());
                EventIdUpdateReservation.getItems().add(reservation.getEvenement_id());
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading reservations");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
