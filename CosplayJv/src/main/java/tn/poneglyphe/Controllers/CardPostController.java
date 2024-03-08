package tn.poneglyphe.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Services.CrudCosplay;
import tn.poneglyphe.Services.CrudMateriaux;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardPostController {
    @FXML
    private ImageView audience;

    @FXML
    private Label caption;

    @FXML
    private Label labeldate;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgPost;
    // image

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgReaction;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgVerified;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbShares;

    @FXML
    private Label reactionName;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private Label username;
    @FXML
    private Label typemat;
    @FXML
    private Label nomCosp;

    @FXML
    private Label personnage;
    @FXML
    private VBox cosplayCard;
    private Cosplay cosplay;
    private final CrudMateriaux cm = new CrudMateriaux();
    private final CrudCosplay cs = new CrudCosplay();

    private AjouterCosplayController mainController;


    //@Override
   /* public void initialize(URL location, ResourceBundle resources) {
        //setData();
    }*/
    @FXML
    void onLikeContainerMouseReleased(MouseEvent event) {

    }

    @FXML
    void onLikeContainerPressed(MouseEvent event) {

    }

    @FXML
    void onReactionImgPressed(MouseEvent event) {

    }

    public void initData(Cosplay cosplay,  AjouterCosplayController mainController) {
        this.cosplay = cosplay;
        this.mainController = mainController;
        Date date = cosplay.getDateCreation();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Specify the desired date format
        String dateString = dateFormat.format(date); // Convert the date to a string in the desired format
        labeldate.setText(dateString);
        if (cosplay.getDescriptionCp() != null && !cosplay.getDescriptionCp().isEmpty()) {
            caption.setText(cosplay.getDescriptionCp());
        } else {
            caption.setManaged(false);
        }
        //image
        if (cosplay.getImageCp() != null && !cosplay.getImageCp().isEmpty()) {

            Image img = new Image(new File(cosplay.getImageCp()).toURI().toString());
            imgPost.setImage(img);
            double maxWidth = 100; // Set the maximum width for the image
            double maxHeight = 100; // Set the maximum height for the image
            imgPost.setFitWidth(maxWidth);
            imgPost.setFitHeight(maxHeight);
        } else {
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }
        personnage.setText(String.valueOf(cosplay.getPersonnage()));
        nomCosp.setText(String.valueOf(cosplay.getNomCp()));
        String materialName = cm.getNomMateriauxById(cosplay.getIdmateriaux());
        if (materialName != null && !materialName.isEmpty()) {
            typemat.setText(materialName);
        } else {
            typemat.setText("Material Name Not Found");
        }
    }


       /* nbReactions.setText(String.valueOf(cosplay.getTotalReactions()));
        currentReaction = Reactions.NON;*/

      /*  System.out.println("Date: "+dateString);
        System.out.println("Caption: "+(cosplay.getDescriptionCp()!=null?cosplay.getDescriptionCp():"No caption"));
        System.out.println("Image Path: "+(cosplay.getImageCp()!=null?cosplay.getImageCp():"No image"));
        System.out.println("Personnage: "+(cosplay.getPersonnage()!=null?cosplay.getPersonnage():"No personnage"));
        System.out.println("NomCosp: "+(cosplay.getNomCp()!=null?cosplay.getNomCp():"No nomCosp"));
        System.out.println("Material Name: "+(materialName !=null?materialName :"No material name"));*/


   public Cosplay getCosplay() {
        return cosplay;
    }

   @FXML
    public void handleEditAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditDialog.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Retrieve the controller for the dialog/form
        EditDialogController dialogController = loader.getController();

        // Populate the dialog/form fields with existing data from the CardPost
        dialogController.initData(cosplay);

        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(root));

        // Show the dialog and wait for user interaction
        dialogStage.showAndWait();

        // After the dialog is closed, retrieve the updated data from the dialog (if any)
        String updatedNom = dialogController.getUpdatedNom();
        String updatedCaption = dialogController.getUpdatedCaption();
        String updatedPersonnage = dialogController.getUpdatedPersonnage();
        String updatedTypemat = dialogController.getUpdatedTypemat();

        // Update the UI components in the CardPost with the updated data
        nomCosp.setText(updatedNom);
        caption.setText(updatedCaption);
        personnage.setText(updatedPersonnage);
        typemat.setText(updatedTypemat);

        // Call the service method to update the data

        cs.update(new Cosplay(updatedNom, updatedCaption, updatedPersonnage, updatedTypemat));

        // Optionally, you can also update the data model in your application

    }
    private Cosplay selectedCosplay;

    // Method to set the selected cosplay
    public void setSelectedCosplay(Cosplay cosplay) {
        this.selectedCosplay = cosplay;
    }
   /* @FXML
    void handleCosplaySelection(MouseEvent event) {
        // Assuming you have access to the CardPostController instance
        CardPostController cardPostController = ...; // Obtain the CardPostController instance


        Cosplay selectedCosplay = ...; // Get the selected cosplay from the UI

        // Set the selected cosplay in the AjouterCosplayController
        cardPostController.setSelectedCosplay(selectedCosplay);
    }*/

    // Method to get the selected cosplay
    public Cosplay getSelectedCosplay() {
        return selectedCosplay;
    }

    @FXML
    void handleDeleteAction(ActionEvent event) {
        if (cosplay != null) {
            try {
                // Delete the selected cosplay from the database
                mainController.deleteCosplay(cosplay);

                // Refresh the display of cosplays
                mainController.refreshCosplays();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }
}

