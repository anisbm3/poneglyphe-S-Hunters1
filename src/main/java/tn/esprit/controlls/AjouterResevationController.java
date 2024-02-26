package tn.esprit.controlls;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Evenement;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterResevationController {

    @FXML
    private Button ajouterbtn;


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

    private final ServiceReservation SR = new ServiceReservation();
    private final ServiceEvenement SE = new ServiceEvenement();

    @FXML
    public void initialize() {
        List<String> list=SE.listevenement();
        System.out.println(list);
        nomEvent.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void onClickedAjouter(ActionEvent event) {
        try {
            // Vérifier si les champs sont vides
            if (nomRES.getText().isEmpty() || nbPlace.getText().isEmpty() || etatRes.getText().isEmpty() || nomEvent.getValue() == null) {
                afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
                return; // Sortir de la méthode si les champs ne sont pas remplis
            }

            // Vérifier si le champ nbPlace contient un entier valide
            int nombrePlaces;
            try {
                nombrePlaces = Integer.parseInt(nbPlace.getText());
            } catch (NumberFormatException e) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être un entier.");
                return; // Sortir de la méthode si le nombre de places n'est pas un entier
            }

            // Vérifier si le nombre de places est positif
            if (nombrePlaces <= 0) {
                afficherNotification("Erreur de saisie", "Le nombre de places doit être supérieur à zéro.");
                return; // Sortir de la méthode si le nombre de places est invalide
            }

            // Créer la réservation si toutes les conditions sont remplies
            Reservation reservation = new Reservation(nomRES.getText(), nombrePlaces, etatRes.getText(), nomEvent.getValue());
            System.out.println(reservation);
            SR.ajouter(reservation);

            // Afficher une notification de succès
            afficherNotification("Ajout avec succès", "La réservation a été ajoutée avec succès.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    void onAfficherClicked(ActionEvent event) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ReservationAfficher.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

            // Refresh the events after ajouter
            refreshEvents();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void refreshEvents() {
    }

}

