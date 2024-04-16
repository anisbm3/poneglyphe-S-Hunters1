package tn.esprit.javafinallgestionevents.controllers;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BackEvenementController implements Initializable {

    @FXML
    private DatePicker DateEvent;

    @FXML
    private TextField DescriptionEvent;

    @FXML
    private VBox EventVbox;

    @FXML
    private TextField LieuEvent;



    @FXML
    private Button imageEventBtn;
    @FXML
    private Button modifierBtn;

    @FXML
    private TextField nomEvent;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button suppBtn;

    @FXML
    private Button retourBtn;


    @FXML
    private Button triBtn;
    @FXML
    private TextField searchBar;
    private Evenement selectedEvenement;
    private final ServiceEvenement SE = new ServiceEvenement();

    private String tri="ASC";
    private int i = 0;
    private final String imageDirectory = "C:/Users/Lenovo/Desktop/test/gestionevent/public/uploads/";

    private String imagePath;
    private void setupSearchBarListener() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                filterEvenement(newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void filterEvenement(String searchText) throws SQLException {

        List<Evenement> evenements = SE.afficher();
        EventVbox.getChildren().clear();

        for (Evenement evenement : evenements) {
            // Vérifiez si le nom de la réservation contient le texte de recherche
            if (evenement.getNom_event().toLowerCase().contains(searchText.toLowerCase())) {
                Label eventLabel = new Label("Nom: " + evenement.getNom_event() +
                        ", Description: " + evenement.getDescription_event() +
                        ", Lieu: " + evenement.getLieu_event() +
                        ", Date: " + evenement.getDate_event().toString());
                        //",Image: " + evenement.getImage());
                ImageView imageView = new ImageView();
                try {
                    File imageFile = new File(imageDirectory + evenement.getImage());
                    Image image = new Image(imageFile.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Définir la largeur souhaitée de l'image
                    imageView.setPreserveRatio(true); // Préserver le ratio de l'image
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                eventLabel.setOnMouseClicked(event -> {
                    selectedEvenement = evenement;
                    nomEvent.setText(selectedEvenement.getNom_event());
                    DescriptionEvent.setText(selectedEvenement.getDescription_event());
                    LieuEvent.setText(selectedEvenement.getLieu_event());
                    DateEvent.setValue(selectedEvenement.getDate_event().toLocalDate());
                    imageEventBtn.setText(selectedEvenement.getImage());
                });
                EventVbox.getChildren().addAll(imageView, eventLabel);
                //EventVbox.getChildren().add(reservationLabel);
            }
        }
    }
    @FXML
    void OnClickedModifier(ActionEvent event) throws SQLException {
        if (selectedEvenement != null) {
            selectedEvenement.setNom_event(nomEvent.getText());
            selectedEvenement.setDescription_event(DescriptionEvent.getText());
            selectedEvenement.setLieu_event(LieuEvent.getText());
            selectedEvenement.setDate_event(DateEvent.getValue().atStartOfDay());
            selectedEvenement.setImage(imagePath); // Utilisez imagePath pour définir le chemin de l'image

            SE.modifier(selectedEvenement);
            refreshEvents();

            updateButtonImage(imagePath); // Appelez updateButtonImage après la modification de l'image
        }
    }

    private void updateButtonImage(String imagePath) {
        try {
            // Chargez l'image à partir du chemin
            File imageFile = new File(imageDirectory + imagePath);
            Image image = new Image(imageFile.toURI().toString());

            // Mettez à jour l'image du bouton
            imageEventBtn.setGraphic(new ImageView(image));
        } catch (Exception e) {
            // Gérez les exceptions si une erreur se produit lors du chargement de l'image
            e.printStackTrace();
        }
    }



   /* @FXML
    void OnClickedModifier(ActionEvent event) throws SQLException {
        if (selectedEvenement != null) {
            selectedEvenement.setNom_event(nomEvent.getText());

            selectedEvenement.setDescription_event(DescriptionEvent.getText());
            selectedEvenement.setLieu_event(LieuEvent.getText());

            selectedEvenement.setDate_event(DateEvent.getValue().atStartOfDay());
            //selectedEvenement.setImage(imageEventBtn.getValue());
            selectedEvenement.setImage(imagePath); // Utilisez imagePath pour définir le chemin de l'image


            SE.modifier(selectedEvenement);
            refreshEvents();


            updateButtonImage(selectedEvenement.getImage());
        }

    }

    private void updateButtonImage(String imagePath) {
        try {
            // Chargez l'image à partir du chemin
            File imageFile = new File(imageDirectory + imagePath);
            Image image = new Image(imageFile.toURI().toString());

            // Mettez à jour l'image du bouton
            imageEventBtn.setGraphic(new ImageView(image));
        } catch (Exception e) {
            // Gérez les exceptions si une erreur se produit lors du chargement de l'image
            e.printStackTrace();
        }
    }*/

    @FXML
    void OnClickedSupp(ActionEvent event) throws SQLException {

        if (selectedEvenement != null) {
            int res = selectedEvenement.getId();
            SE.supprimer(new Evenement(res, nomEvent.getText(),DescriptionEvent.getText(), LieuEvent.getText() , DateEvent.getValue().atStartOfDay(),imagePath ));
            refreshEvents();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            updateButtonImage(imagePath);
            refreshEvents();
            setupSearchBarListener();
            //OnClickedUpdateEvent(eve);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnClickedUpdateEvent(ActionEvent event) {
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
            // Afficher le chemin de l'image sélectionnée dans un TextField ou un Label
           imageEventBtn.setText(imagePath); // Supposons que vous avez un TextField nommé imageEvent
        }
    }

    private void refreshEvents() throws SQLException {
        List<Evenement> events = SE.afficherbyTri(tri);
        EventVbox.getChildren().clear();
        for (Evenement evenement : events) {

            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_event() +
                            ", Description: " + evenement.getDescription_event() +
                            ", Lieu: " + evenement.getLieu_event() +
                            ", Date: " + evenement.getDate_event().toString()
                    // ",Image: " + evenement.getImage()
            );
            ImageView imageView = new ImageView();
            try {
                File imageFile = new File(imageDirectory + evenement.getImage());
                Image image = new Image(imageFile.toURI().toString());
                imageView.setImage(image);
                imageView.setFitWidth(100); // Définir la largeur souhaitée de l'image
                imageView.setPreserveRatio(true); // Préserver le ratio de l'image
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            eventLabel.setOnMouseClicked(event -> {
                selectedEvenement = evenement;
                nomEvent.setText(selectedEvenement.getNom_event());
                DescriptionEvent.setText(selectedEvenement.getDescription_event());
                LieuEvent.setText(selectedEvenement.getLieu_event());
                DateEvent.setValue(selectedEvenement.getDate_event().toLocalDate());
                // imageEvent.setText(selectedEvenement.getImage());
            });
            EventVbox.getChildren().addAll(imageView, eventLabel);
        }
    }

    @FXML
    void OnClickedRetour(ActionEvent event) {
        SE.changeScreen(event, "/tn/esprit/javafinallgestionevents/AjouterEvenement.fxml", "Ajouter  Evenement");

    }


    @FXML
    void OnClickedTri(ActionEvent event) throws SQLException {
        if(i % 2 == 0){
            tri = "ASC";
        }else{
            tri = "DESC";

        }
        i++;
        refreshEvents();
    }

}
