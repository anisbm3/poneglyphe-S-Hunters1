package tn.esprit.javafinallgestionevents.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.esprit.javafinallgestionevents.models.Reservation;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;
import tn.esprit.javafinallgestionevents.services.ServiceReservation;
import tn.esprit.javafinallgestionevents.services.ServiceUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AjouterReservationController {

    @FXML
    private TextField Etat;

    @FXML
    private Button ReservationBtn;

    @FXML
    private ChoiceBox<String> evenementId; // Change to String to hold event names

    @FXML
    private TextField nbPlaces;

    @FXML
    private TextField nomReservation;

    @FXML
    private ChoiceBox<String> userId;

    private final ServiceReservation SR = new ServiceReservation();
    private final ServiceEvenement SE = new ServiceEvenement();

    private final ServiceUser SU = new ServiceUser(); // Ajoutez l'instance de ServiceUser


    @FXML
    void OnClickedAjouter(ActionEvent event) throws SQLException {
        if (nomReservation.getText().isEmpty() || nbPlaces.getText().isEmpty() || Etat.getText().isEmpty() || evenementId.getValue() == null || userId.getValue() == null) {
            afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        int nombrePlaces;
        try {
            nombrePlaces = Integer.parseInt(nbPlaces.getText());
        } catch (NumberFormatException e) {
            afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
            return;
        }

        if (nombrePlaces <= 0) {
            afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
            return;
        }
       // int eventId = SE.getEventIdByName(evenementId.getValue());
        int eventId = SE.getEventIdByName(evenementId.getValue());
        Reservation reservation = new Reservation(nomReservation.getText(), nombrePlaces, Etat.getText(), LocalDateTime.now(), String.valueOf(eventId), userId.getValue());

       // Reservation reservation = new Reservation(nomReservation.getText(), nombrePlaces, Etat.getText(), eventId, userId.getValue());

        SR.ajouter(reservation);
        String eventName = evenementId.getValue(); // No need to call the method here as the name is already in the choice box
        //String RolesUser = userId.getValue();
        int selectedUserId = Integer.parseInt(userId.getValue());
        List<String> userRoles = SU.getRoleByUserId(selectedUserId);
        afficherNotification("Ajout avec succès", "La réservation pour l'événement '" + eventName + "' a été ajoutée avec succès. Rôles de l'utilisateur : " + String.join(", ", userRoles));

        //afficherNotification("Ajout avec succès", "La réservation pour l'événement '" + eventName + "' a été ajoutée avec succès." + RolesUser + "user Roles");
    }


    @FXML
    public void initialize() throws SQLException {
        // Populate event names ChoiceBox
        List<String> eventNames = SE.getAllEventNames(); // Assuming you have a method in ServiceEvenement to retrieve all event names
        evenementId.setItems(FXCollections.observableArrayList(eventNames));

        // You may also need to populate userIds ChoiceBox here if needed
        /*List<String> roles = SU.getRoleByUserId();
        userId.setItems(FXCollections.observableArrayList(roles));*/

        List<Integer> userIds = SU.getAllUserIds(); // Assuming you have a method in ServiceUser to retrieve all user IDs
        userId.setItems(FXCollections.observableArrayList(userIds.stream().map(String::valueOf).collect(Collectors.toList())));
       /* List<String> roles = getUserRoles(); // Call the method to get user roles
        userId.setItems(FXCollections.observableArrayList(roles));*/



    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
