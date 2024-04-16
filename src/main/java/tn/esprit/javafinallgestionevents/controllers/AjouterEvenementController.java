package tn.esprit.javafinallgestionevents.controllers;

/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

import java.io.File;
import java.sql.SQLException;

public class AjouterEvenementController {

    @FXML
    private Button AfficherBtn;

    @FXML
    private DatePicker DateEvent;

    @FXML
    private TextField DescriptionEvent;

    @FXML
    private TextField Image;

    @FXML
    private TextField LieuEvent;

    @FXML
    private Button ajouterBtn;

    @FXML
    private TextField nomEvent;

    @FXML
    private Button selectImageBtn;

    private String imagePath;

    private final ServiceEvenement SE = new ServiceEvenement();

    @FXML
    void OnClickedAjouter(ActionEvent event) throws SQLException {
        if (champsSontValides()) {
            SE.ajouter(new Evenement(nomEvent.getText(), DescriptionEvent.getText(), LieuEvent.getText(), DateEvent.getValue().atStartOfDay(), imagePath));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Événement ajouté");
            alert.setHeaderText(null);
            alert.setContentText("L'événement a été ajouté avec succès!");
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

    @FXML
    void OnClickedAfficher(ActionEvent event) {
        SE.changeScreen(event, "/tn/esprit/javafinallgestionevents/BackEvenement.fxml", "afficher front Evenement");
    }

    @FXML
    void OnClickSelectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        // Filtrer uniquement les fichiers d'image
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        // Afficher la boîte de dialogue de sélection de fichier
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Récupérer le chemin de l'image sélectionnée
            imagePath = selectedFile.getAbsolutePath();
        }
    }

    private boolean champsSontValides() {
        return !nomEvent.getText().isEmpty() && !DescriptionEvent.getText().isEmpty() && !LieuEvent.getText().isEmpty() && DateEvent.getValue() != null && imagePath != null;
    }

    private void clearFields() {
        nomEvent.clear();
        DescriptionEvent.clear();
        LieuEvent.clear();
        DateEvent.setValue(null);
        imagePath = null; // Réinitialiser le chemin de l'image après l'ajout de l'événement
    }
}*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

import java.io.File;
import java.sql.SQLException;

public class AjouterEvenementController {

    @FXML
    private Button AfficherBtn;

    @FXML
    private DatePicker DateEvent;

    @FXML
    private TextField DescriptionEvent;

    @FXML
    private TextField Image;

    @FXML
    private TextField LieuEvent;

    @FXML
    private Button ajouterBtn;

    @FXML
    private TextField nomEvent;

    @FXML
    private Button selectImageBtn;

    private String imagePath;

    private final ServiceEvenement SE = new ServiceEvenement();

    @FXML
    void OnClickedAjouter(ActionEvent event) throws SQLException {
        if (champsSontValides()) {
            SE.ajouter(new Evenement(nomEvent.getText(), DescriptionEvent.getText(), LieuEvent.getText(), DateEvent.getValue().atStartOfDay(), imagePath));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Événement ajouté");
            alert.setHeaderText(null);
            alert.setContentText("L'événement a été ajouté avec succès!");
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

    @FXML
    void OnClickedAfficher(ActionEvent event) {
        SE.changeScreen(event, "/tn/esprit/javafinallgestionevents/BackEvenement.fxml", "afficher front Evenement");
    }

    @FXML
    void OnClickSelectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        // Filtrer uniquement les fichiers d'image
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        // Afficher la boîte de dialogue de sélection de fichier
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Récupérer le chemin de l'image sélectionnée
            imagePath = selectedFile.getName(); // Obtenir seulement le nom du fichier
            // Afficher le chemin de l'image sélectionnée dans le champ texte Image
            Image.setText(imagePath);
        }
    }

    private boolean champsSontValides() {
        return !nomEvent.getText().isEmpty() && !DescriptionEvent.getText().isEmpty() && !LieuEvent.getText().isEmpty() && DateEvent.getValue() != null && imagePath != null;
    }

    private void clearFields() {
        nomEvent.clear();
        DescriptionEvent.clear();
        LieuEvent.clear();
        DateEvent.setValue(null);
        imagePath = null; // Réinitialiser le chemin de l'image après l'ajout de l'événement
    }
}







/*package tn.esprit.javafinallgestionevents.controllers;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TextField;
        import javafx.stage.FileChooser;
        import tn.esprit.javafinallgestionevents.models.Evenement;
        import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

        import java.io.File;
        import java.sql.SQLException;

public class AjouterEvenementController {

    @FXML
    private Button AfficherBtn;

    @FXML
    private DatePicker DateEvent;

    @FXML
    private TextField DescriptionEvent;

    @FXML
    private TextField Image;

    @FXML
    private TextField LieuEvent;

    @FXML
    private Button ajouterBtn;

    @FXML
    private TextField nomEvent;

    @FXML
    private Button selectImageBtn;

    private String imagePath;

    private final ServiceEvenement SE = new ServiceEvenement();

    @FXML
    void OnClickedAjouter(ActionEvent event) throws SQLException {
        if (champsSontValides()) {
            SE.ajouter(new Evenement(nomEvent.getText(), DescriptionEvent.getText(), LieuEvent.getText(), DateEvent.getValue().atStartOfDay(), imagePath));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Événement ajouté");
            alert.setHeaderText(null);
            alert.setContentText("L'événement a été ajouté avec succès!");
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

    @FXML
    void OnClickedAfficher(ActionEvent event) {
        SE.changeScreen(event, "/tn/esprit/javafinallgestionevents/BackEvenement.fxml", "afficher front Evenement");
    }

    @FXML
    void OnClickSelectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        // Filtrer uniquement les fichiers d'image
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        // Afficher la boîte de dialogue de sélection de fichier
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Récupérer le chemin de l'image sélectionnée
            imagePath = selectedFile.getAbsolutePath();
            // Mettre à jour le champ texte de l'image avec le nom du fichier seulement
            Image.setText(selectedFile.getName());
        }
    }

    private boolean champsSontValides() {
        return !nomEvent.getText().isEmpty() && !DescriptionEvent.getText().isEmpty() && !LieuEvent.getText().isEmpty() && DateEvent.getValue() != null && imagePath != null;
    }

    private void clearFields() {
        nomEvent.clear();
        DescriptionEvent.clear();
        LieuEvent.clear();
        DateEvent.setValue(null);
        Image.clear(); // Effacer le champ texte de l'image
        imagePath = null; // Réinitialiser le chemin de l'image après l'ajout de l'événement
    }
}*/
