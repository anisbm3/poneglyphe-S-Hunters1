package tn.esprit.controlls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationAfficherController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    private final ServiceReservation serviceReservation = new ServiceReservation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshReservations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshReservations() throws SQLException {
        // Récupérer les réservations depuis le service
        List<Reservation> reservations = serviceReservation.afficher();

        // Créer un conteneur pour afficher les réservations
        VBox reservationsContainer = new VBox();
        reservationsContainer.setSpacing(10); // Ajout d'un espacement vertical entre chaque label

        // Parcourir la liste des réservations et créer des labels pour chaque réservation
        for (Reservation reservation : reservations) {
            // Créer un label pour afficher les détails de la réservation
            Label reservationLabel = new Label("Nom: " + reservation.getNom_Reseervation() +
                    ", Places: " + reservation.getNB_Places() +
                    ", État: " + reservation.getEtat() +
                    ", Événement: " + reservation.getNOM_Event());

            // Appliquer le style directement au label
            reservationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12)); // Style de la police
            reservationLabel.setTextFill(Color.web("#333333")); // Couleur du texte
            reservationLabel.setPadding(new Insets(10)); // Ajout d'un padding au label
            reservationLabel.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px;"); // Style CSS pour le fond et la bordure

            // Ajouter le label au conteneur
            reservationsContainer.getChildren().add(reservationLabel);
        }

        // Ajouter le conteneur au ScrollPane
        scrollPane.setContent(reservationsContainer);
    }
}
