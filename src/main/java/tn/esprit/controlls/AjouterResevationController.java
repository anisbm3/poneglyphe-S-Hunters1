package tn.esprit.controlls;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.ServiceReservation;
import tn.esprit.services.userService;

//import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjouterResevationController {

    @FXML
    private Button ajouterbtn;

    @FXML
    private Button RetourBtn;

    @FXML
    private Button afficherBtn;

    @FXML
    private TextField etatRes;

    @FXML
    private TextField nbPlace;

    @FXML
    private ChoiceBox<String> nomEvent;

    @FXML
    private TextField nomRES;

    @FXML
    private Button quitBtn;

    private final ServiceReservation SR = new ServiceReservation();
    private final ServiceEvenement SE = new ServiceEvenement();

    userService serviceUtilisateurs = new userService();
    @FXML
    public void initialize() {
        List<String> list = SE.listevenement();
        System.out.println(list);
        nomEvent.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void onClickedAjouter(ActionEvent event) {
        try {
            // Vérifier si les champs sont vides
            if (nomRES.getText().isEmpty() || nbPlace.getText().isEmpty() || etatRes.getText().isEmpty() || nomEvent.getValue() == null || clientFrontController.loggedInUser.getPseudo() == null) {
                afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
                return;
            }

            int nombrePlaces;
            try {
                nombrePlaces = Integer.parseInt(nbPlace.getText());
            } catch (NumberFormatException e) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
                return;
            }


            if (nombrePlaces <= 0) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
                return;
            }

            Reservation reservation = new Reservation(nomRES.getText(), nombrePlaces, etatRes.getText(), nomEvent.getValue(),clientFrontController.loggedInUser.getPseudo());
            System.out.println(reservation);
            SR.ajouter(reservation);

            afficherNotification("Ajout avec succès", "La réservation a été ajoutée avec succès.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*@FXML
    void onClickedAjouter(ActionEvent event) {
        try {
            if (nomRES.getText().isEmpty() || nbPlace.getText().isEmpty() || etatRes.getText().isEmpty() || nomEvent.getValue() == null) {
                afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
                return;
            }

            int nombrePlaces;
            try {
                nombrePlaces = Integer.parseInt(nbPlace.getText());
            } catch (NumberFormatException e) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
                return;
            }

            if (nombrePlaces <= 0) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
                return;
            }

            Reservation reservation = new Reservation(nomRES.getText(), nombrePlaces, etatRes.getText(), nomEvent.getValue());
            SR.ajouter(reservation);

            // Création de l'événement dans Google Calendar
            ZonedDateTime startDateTime = ZonedDateTime.now();
            ZonedDateTime endDateTime = startDateTime.plusHours(1);
            String summary = "Réservation pour " + nomRES.getText();
            try {
                GoogleCalendarIntegration.createEvent(summary, startDateTime, endDateTime);
            } catch (IOException | GeneralSecurityException e) {
                afficherNotification("Erreur", "Impossible de créer l'événement dans Google Calendar.");
                e.printStackTrace();
                return;
            }

            afficherNotification("Ajout avec succès", "La réservation a été ajoutée avec succès et l'événement a été créé dans Google Calendar.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     */
    /*@FXML
    void onClickedAjouter(ActionEvent event) throws SQLException {
        try {
            if (nomRES.getText().isEmpty() || nbPlace.getText().isEmpty() || etatRes.getText().isEmpty() || nomEvent.getValue() == null) {
                afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
                return;
            }

            int nombrePlaces;
            try {
                nombrePlaces = Integer.parseInt(nbPlace.getText());
            } catch (NumberFormatException e) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
                return;
            }

            if (nombrePlaces <= 0) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
                return;
            }

            Reservation reservation = new Reservation(nomRES.getText(), nombrePlaces, etatRes.getText(), nomEvent.getValue());
            SR.ajouter(reservation);

            // Création de l'événement dans Google Calendar
            ZonedDateTime startDateTime = ZonedDateTime.now();
            ZonedDateTime endDateTime = startDateTime.plusHours(1);
            String summary = "Réservation pour " + nomRES.getText();


            //GoogleCalendarIntegration.createEvent(nomRES.getText(), nbPlace.getText(), etatRes.getText(),nomEvent.getValue());

            //GoogleCalendarIntegration.createEvent(summary, descEv.getText(), lieuEv.getText(), startDateTime, endDateTime);

            afficherNotification("Erreur", "Impossible de créer l'événement dans Google Calendar.");

            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/


   /* @FXML
    void onClickedAjouter(ActionEvent event) throws SQLException {
        try {
            if (nomRES.getText().isEmpty() || nbPlace.getText().isEmpty() || etatRes.getText().isEmpty() || nomEvent.getValue() == null) {
                afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
                return;
            }

            int nombrePlaces;
            try {
                nombrePlaces = Integer.parseInt(nbPlace.getText());
            } catch (NumberFormatException e) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
                return;
            }

            if (nombrePlaces <= 0) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
                return;
            }

            Reservation reservation = new Reservation(nomRES.getText(), nombrePlaces, etatRes.getText(), nomEvent.getValue());
            SR.ajouter(reservation);

            // Création de l'événement dans Google Calendar
            ZonedDateTime startDateTime = ZonedDateTime.now();
            ZonedDateTime endDateTime = startDateTime.plusHours(1);
            String summary = "Réservation pour " + nomRES.getText();
            GoogleCalendarIntegration.createEvent(summary, "Description de l'événement", "Lieu de l'événement", startDateTime, endDateTime);

            afficherNotification("Succès", "La réservation a été ajoutée avec succès.");

            return;
        } catch (SQLException | IOException | GeneralSecurityException e) {
            afficherNotification("Erreur", "Impossible de créer l'événement dans Google Calendar.");
            throw new RuntimeException(e);
        }
    }

    */

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void onAfficherClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ReservationAfficher.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            refreshEvents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickedRetour(ActionEvent event) {
        serviceUtilisateurs.changeScreen(event, "/tn/esprit/clientFront.fxml", "client front");
    }

    private void refreshEvents() {
    }

    @FXML
    void OnClickedEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ajouterEvent.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            refreshEvents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onQuitClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/Menu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            refreshEvents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
