package controllers;

import entities.Debat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceDebat;

import java.io.IOException;

public class AjouterDebatsController {

    @FXML
    private TextField AnimeDebat;
    @FXML
    private TextField NoteAnime;

    @FXML
    private Button afficherBtn;

    @FXML
    private Button ajouterbtn;

    @FXML
    private TextField descDebat;

    @FXML
    private TextField nomDEBAT;

    private final ServiceDebat SD = new ServiceDebat();
    @FXML
    void OnClickAfficherBtn(ActionEvent event) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherDebats.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

} catch (IOException e) {
        e.printStackTrace();
        }

        }

    @FXML
    void OnClickAjouterDebat(ActionEvent event) {

        if (champsSontValides()) {
            SD.ajouter(new Debat(AnimeDebat.getText(), descDebat.getText(), nomDEBAT.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Debat ajouté");
            alert.setHeaderText(null);
            alert.setContentText("Le debat a été ajouté avec succès!");
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

    private boolean champsSontValides() {
        return !AnimeDebat.getText().isEmpty() && !descDebat.getText().isEmpty() && !nomDEBAT.getText().isEmpty()  ;
    }

    private void clearFields() {
        AnimeDebat.clear();
        descDebat.clear();
        nomDEBAT.clear();

    }

    public void OnClickFront(ActionEvent actionEvent) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontDebat.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void OnClickFrontCommentaire(ActionEvent actionEvent) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontCommentaire.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnClickTakemetocomment(ActionEvent actionEvent) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommentaires.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
