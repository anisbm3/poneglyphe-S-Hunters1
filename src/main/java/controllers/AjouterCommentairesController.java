package controllers;

import entities.Commentaire;
import entities.Debat;
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
import services.ServiceCommentaire;
import services.ServiceDebat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AjouterCommentairesController {
    @FXML
private Button ajouterbtn;


    @FXML
    private Button afficherBtn;

    @FXML
    private ChoiceBox<String> nomDebat;

    @FXML
    private TextField nomMES;

    @FXML
    private Button quitBtn;


        private Connection connection;

    private final ServiceCommentaire SC = new ServiceCommentaire() ;
    private final ServiceDebat SD = new ServiceDebat();

    @FXML
    public void initialize() {
        List<String> list = SD.listdebat();
        System.out.println(list);
        nomDebat.setItems(FXCollections.observableArrayList(list));
    }
    @FXML
    void onClickedAjouter(ActionEvent event) throws SQLException {
        // Vérifier si les champs sont vides
        if (nomMES.getText().isEmpty() ||  nomDebat.getValue() == null) {
            afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
            return; // Sortir de la méthode si les champs ne sont pas remplis
        }

        // Créer la réservation si toutes les conditions sont remplies
        Commentaire commentaire = new Commentaire(nomMES.getText(),nomDebat.getValue());
        System.out.println(commentaire);
        SC.ajouter(commentaire);

        // Afficher une notification de succès
        afficherNotification("Ajout avec succès", "La commentaire a été ajoutée avec succès.");

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

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommentaires.fxml"));
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

    @FXML
    void OnClickedEvent(ActionEvent event) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterDebats.fxml"));
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

    public void supprimerParId(int idCommentaire) throws SQLException {
        String req = "DELETE FROM commentaire WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idCommentaire);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du Commentaire : " + e.getMessage());
        }
    }
    @FXML
    void onQuitClicked(ActionEvent event) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/Menu.fxml"));
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

}
