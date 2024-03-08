package tn.esprit.controlls;

import javafx.collections.FXCollections;
import tn.esprit.models.Debat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.services.ServiceDebat;
import tn.esprit.services.userService;

import java.io.IOException;
import java.util.List;

import static tn.esprit.controlls.clientFrontController.loggedInUser;


public class AjouterDebatsController {



    @FXML
    private Button revenirEnArriereBtn;
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
    userService serviceUtilisateurs = new userService();


  /*  @FXML
    public void initialize() {
        List<String> list = SD.listdebat();
        System.out.println(list);
       // .setItems(FXCollections.observableArrayList(list));
    }*/
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
            SD.ajouter(new Debat(AnimeDebat.getText(), descDebat.getText(), nomDEBAT.getText(),loggedInUser.getPseudo()));
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
        return !AnimeDebat.getText().isEmpty() && !descDebat.getText().isEmpty() && !nomDEBAT.getText().isEmpty() && ! loggedInUser.getPseudo().isEmpty()  ;
    }

    private void clearFields() {
        AnimeDebat.clear();
        descDebat.clear();
        nomDEBAT.clear();

    }

    public void OnClickFront(ActionEvent actionEvent) {

        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherDebats.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void OnClickFrontCommentaire(ActionEvent actionEvent) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommentaires.fxml"));
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


    @FXML
    void OnclickedRevenir(ActionEvent event) {
        serviceUtilisateurs.changeScreen(event, "/tn/esprit/clientFront.fxml", "clientFRONT");


    }

}
