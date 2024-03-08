package tn.esprit.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Evenement;
import tn.esprit.services.AddEventToCalendarApp;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.userService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AjouterEventController {


    @FXML
    private Button revenirEnArriere;
    @FXML
    private Button ajouterbtn;

    @FXML
    private DatePicker dateEv;

    @FXML
    private TextField descEv;

    @FXML
    private TextField idEv;

    @FXML
    private TextField lieuEv;

    @FXML
    private TextField nomEv;

    @FXML
    private Button afficherBtn;

    userService serviceUtilisateurs = new userService();
    private final ServiceEvenement SE = new ServiceEvenement();
    @FXML
    void OnClickAjouterEv(ActionEvent event) {
        AddEventToCalendarApp calendarApp=new AddEventToCalendarApp();
        if (champsSontValides()) {
            SE.ajouter(new Evenement(nomEv.getText(), descEv.getText(), lieuEv.getText(), dateEv.getValue().atStartOfDay()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Événement ajouté");
            alert.setHeaderText(null);
            alert.setContentText("L'événement a été ajouté avec succès!");
            calendarApp.addEventToCalendar(nomEv.getText(), dateEv.getValue());
            alert.showAndWait();
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }
    }

    private void afficherInfo(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void OnClickedArriere(ActionEvent event) {
        serviceUtilisateurs.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
    }



    private boolean champsSontValides() {
        return !nomEv.getText().isEmpty() && !descEv.getText().isEmpty() && !lieuEv.getText().isEmpty() && dateEv.getValue() != null;
    }


    private void clearFields() {
        nomEv.clear();
        descEv.clear();
        lieuEv.clear();
        dateEv.setValue(null);
    }


    @FXML
    void OnClickAfficherBtn(ActionEvent event) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/EventAfficher.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

