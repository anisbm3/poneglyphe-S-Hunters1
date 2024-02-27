package tn.poneglyphe.Controllers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Services.CrudCosplay;



public class PostCosplayContr {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datepick;

    @FXML
    private TextArea tfdesc;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfpers;
    @FXML
    private ImageView imageView;
    @FXML
    private Label imagepath;
    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Save the image path
            String imagePath = selectedFile.getAbsolutePath();
            imagepath.setText(imagePath);

            // Load the image into the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);


        }
    }
    @FXML
    void postbtn(ActionEvent event) {
        // Vérification des champs obligatoires
        if (tfnom.getText().isEmpty() || tfdesc.getText().isEmpty() || tfpers.getText().isEmpty() || datepick.getValue() == null) {
            showAlert("Champs obligatoires", "Veuillez remplir tous les champs.");
            return;
        }
// Vérification du format de l'image
        String imagePath = imagepath.getText();
        if (!isValidImagePath(imagePath)) {
            showAlert("Format d'image invalide", "Le chemin de l'image doit être un lien valide.");
            return;
        }


        CrudCosplay cs =new CrudCosplay();
        LocalDate selectedDate = datepick.getValue();
        // Convert LocalDate to Date
        Date date = java.sql.Date.valueOf(selectedDate);
        cs.add(new Cosplay(tfnom.getText(),tfdesc.getText(), tfpers.getText(),imagepath.getText(),date));
    }

    private boolean isValidImagePath(String imagePath) {
        return !imagePath.isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}
    @FXML
    void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCosplay.fxml"));
        try {
            Parent root =loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCosplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

