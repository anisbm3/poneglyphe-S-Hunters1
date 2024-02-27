//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controllers;

import entities.Debat;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceDebat;

public class AjouterDebatController {
    @FXML
    private TextField DESCDEBAT;
    @FXML
    private TextField IDDEBAT;
    @FXML
    private TextField NOMANIME;
    @FXML
    private TextField SUJETDEBAT;
    @FXML
    private Button ajouterbtn;
    private final ServiceDebat SD = new ServiceDebat();



    @FXML
    void onClickedAjouter(ActionEvent event) throws SQLIntegrityConstraintViolationException {
        //String idDebat = this.IDDEBAT.getText();
        String sujetDebat = this.SUJETDEBAT.getText();
        String descriptionDebat = this.DESCDEBAT.getText();
        String nomAnime = this.NOMANIME.getText();
        if (descriptionDebat.isEmpty() || sujetDebat.isEmpty()  || nomAnime.isEmpty() )
        afficherAlerte("Error","veuillez remplir tous les champs");
        else if (isNumeric(sujetDebat) || isNumeric(descriptionDebat) || isNumeric(nomAnime))
            afficherAlerte("Error","veuillez saisir des chaines de characteres");

            else afficherAlerte("Sucees","Ajout reussi");
    }

    @FXML
    void onClickedAfficher(ActionEvent event) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherDebat.fxml"));
            Parent root = loader.load();

            // Créer la scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la scène dans la fenêtre
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement du fichier FXML
        }


    }
    private void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

