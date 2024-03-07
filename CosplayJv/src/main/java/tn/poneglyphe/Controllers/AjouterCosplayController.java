package tn.poneglyphe.Controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import  java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Services.CrudCosplay;

public class AjouterCosplayController  implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private VBox cosplayContainer ;
    @FXML
    private Label caption;

    @FXML
    private ImageView imgPost;

    @FXML
    private Label labeldate;

    @FXML
    private Label labeldate1;

    @FXML
    private Label nomCosp;

    @FXML
    private Label personnage;

    @FXML
    private Label typemat;
    @FXML
    private VBox cosplayCard;

    private   ArrayList<Cosplay> cosplays ;
    private final CrudCosplay cs = new CrudCosplay();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (cosplayContainer != null) {
            System.out.println("cosplayContainer is initialized");

            // Display the cosplays
            addCosplayCard(cosplays);
        } else {
            System.out.println("cosplayContainer is not initialized");

        }



        }
   /* public void displayCosplays(ArrayList<Cosplay> cosplays) {

        cosplayCard.getChildren().clear();

        for (Cosplay cosplay : cosplays) {
            VBox cosplayCardItem = createCosplayCard(cosplay);
            cosplayCard.getChildren().add(cosplayCardItem);
        }
        //cosplayContainer.getChildren().add(cosplayCard);
    }*/
   /* private VBox createCosplayCard(Cosplay cosplay) {
        // Create UI components to display the cosplay information
        Label nomCosp = new Label("Nom: " + cosplay.getNomCp());
        Label caption = new Label("description " + cosplay.getDescriptionCp());

        Label personnage = new Label("Personnage: " + cosplay.getPersonnage());
        Label typemat = new Label("Type de matériau: " + cosplay.getNomMa());
        Label dateLabel = new Label("Date: " + cosplay.getDateCreation().toString()); // Assuming getDate returns a Date object
        ImageView imgPost = new ImageView(new File(cosplay.getImageCp()).toURI().toString());
        double maxWidth = 100; // Set the maximum width for the image
        double maxHeight = 100; // Set the maximum height for the image
        imgPost.setFitWidth(maxWidth);
        imgPost.setFitHeight(maxHeight);

// Set the preserve ratio to true to maintain the aspect ratio of the image
        imgPost.setPreserveRatio(true);
        VBox cardLayout = new VBox(nomCosp, caption, personnage, typemat, dateLabel, imgPost);
        cardLayout.setSpacing(10); // Add spacing between components
        cardLayout.setStyle("-fx-border-color: white; -fx-border-width: 1px;"); // Add border to the card layout
        return cardLayout;

    }*/

   /* public void displayCosplays(ArrayList<Cosplay> cosplays) {
        if (cosplayContainer == null) {
            System.err.println("cosplayContainer is null. Unable to display cosplays.");
            return; // Exit the function if cosplayContainer is null
        }
        // Clear the existing UI components
        cosplayContainer.getChildren().clear();

        // Loop through the cosplays and add them to the container
        for () {
            if (cosplay == null) {
                System.err.println("Found null cosplay object. Skipping.");
                continue; // Skip to the next cosplay if current cosplay is null
            }
            // Check if any required data within cosplay is null
            if (cosplay.getNomCp() == null || cosplay.getDescriptionCp() == null || cosplay.getPersonnage() == null ||
                    cosplay.getNomMa() == null || cosplay.getDateCreation() == null || cosplay.getImageCp() == null) {
                System.err.println("Found null data in cosplay object. Skipping.");
                continue; // Skip to the next cosplay if any required data is null
            }
            try {
                // Create UI components to display the cosplay information
                Label nomCosp = new Label("Nom: " + cosplay.getNomCp());
                Label caption = new Label("description " + cosplay.getDescriptionCp());

                Label personnage = new Label("Personnage: " + cosplay.getPersonnage());
                Label typemat = new Label("Type de matériau: " + cosplay.getNomMa());
                Label dateLabel = new Label("Date: " + cosplay.getDateCreation().toString()); // Assuming getDate returns a Date object
            ImageView imgPost = new ImageView(new File(cosplay.getImageCp()).toURI().toString());


                // Add them to the container
            cosplayContainer.getChildren().addAll(nomCosp,caption, personnage, typemat, dateLabel, imgPost);
            } catch (Exception e) {
                System.err.println("Error while creating UI components for cosplay: " + e.getMessage());
            }
            }
        }*/


    @FXML
    private void addcosplay(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCosplay.fxml"));
        try {
            Parent root =loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PostCosplayContr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void addCosplayCard(ArrayList<Cosplay> cosplays) {
        if (cosplayContainer == null) {
            System.out.println("Error: cosplayContainer is null.");
            return;
        }

        cosplays = cs.getAll();
        if (cosplays == null) {
            System.out.println("Cosplays list is null. Cannot add cosplay cards.");
            return;
        }

        for (Cosplay cosplay : cosplays) {
            try {
                // Load the FXML file for the cosplay card
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPost.fxml"));
                Node cosplayCard = loader.load(); // Load the root node of the FXML file

                // Retrieve the controller for the cosplay card
                CardPostController cosplayCardController = loader.getController();

                // Initialize the data for the cosplay card
                cosplayCardController.initData(cosplay);

                // Add the cosplay card to the cosplayContainer
                cosplayContainer.getChildren().add(cosplayCard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

