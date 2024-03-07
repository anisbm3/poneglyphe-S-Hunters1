package tn.poneglyphe.Controllers;
import javafx.scene.control.Button;
import tn.poneglyphe.Services.CrudMateriaux;
import tn.poneglyphe.Test.Main;
import tn.poneglyphe.Utils.MyConnection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
    private Button ann;
    @FXML
    private ResourceBundle resources;
    @FXML
    private ComboBox<String> cb_mat;
    ObservableList<String> optionsMateriaux = FXCollections.observableArrayList();
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
    private AjouterCosplayController showController =new AjouterCosplayController();;
    private final CrudCosplay cs = new CrudCosplay();
    private final CrudMateriaux cm = new CrudMateriaux();
    private ArrayList<Cosplay> cosplays;



    @FXML
    void initialize() {
        fillcomboMateriaux();

    }

    private void fillcomboMateriaux() {
        ArrayList<String> materiauxNoms = cm.getAllMateriauxNoms();
        System.out.println(materiauxNoms);
        optionsMateriaux.addAll(materiauxNoms);
        cb_mat.setItems(optionsMateriaux);
    }

    /*  private void fillcomboMateriaux() {
          try {
              Connection cnx = MyConnection.getInstance().getCnx();
              String req = " select nomMa from materiaux";
              PreparedStatement cs = cnx.prepareStatement(req);
              ResultSet rs = cs.executeQuery(req);
              while(rs.next()){
                  optionsMateriaux.add(rs.getString("nomMa"));
              }
              cb_mat.setItems(optionsMateriaux);
          } catch (SQLException ex) {
              Logger.getLogger(AjouterCosplayController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }*/
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
    private void postbtn(ActionEvent event) {
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

        // Récupérer l'identifiant du matériau a partir  mn nom selection fi wost combo
        String nomMa = cb_mat.getValue();
        if (nomMa == null || nomMa.isEmpty()) {

            System.out.println("Aucun matériau sélectionné !");
        } else {
            int idMateriaux = cm.getIdMateriauxFromName(nomMa);
            System.out.println("Identifiant du matériau: " + idMateriaux); // Vérifiez si l'identifiant est correct

            if (idMateriaux == -1) {
                // Handle the case where the material name was not found
                showAlert("Matériau introuvable", "Le matériau sélectionné n'existe pas.");
                return;
            }

            String materialName = cm.getNomMateriauxById(idMateriaux);
            LocalDate selectedDate = datepick.getValue();
            // Convert LocalDate to Date
            Date date = java.sql.Date.valueOf(selectedDate);
            System.out.println("Before adding: " + cs.getAll().size());
            cs.add(new Cosplay(tfnom.getText(), tfdesc.getText(), tfpers.getText(), imagepath.getText(), date,idMateriaux ,materialName));
            System.out.println("After adding: " + cs.getAll().size());
            System.out.println("Material Name: " + materialName);

            ArrayList<Cosplay> cosplays = cs.getAll();
            if (cosplays == null) {
                showAlert("Erreur de récupération", "Impossible de récupérer la liste des cosplays.");
                return;
            }

        }

            // Load the FXML file for the cosplay card



            // Pass the cosplay card controller to the ShowController
            showController.addCosplayCard(cosplays);

            // Close the AddController window
            Stage stage = (Stage) tfnom.getScene().getWindow();
            stage.close();

        }
        /*
        if (cosplays != null) {

            // Create a new stage
            Stage stage = new Stage();

// Create the root node (the parent of your scene graph)
            Parent root = null;
            try {
                // Load the FXML file manually
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCosplay.fxml"));
                root = loader.load();

                // Get the controller
                AjouterCosplayController ajouterCosplayController = loader.getController();
                if (ajouterCosplayController != null) {
                    ajouterCosplayController.displayCosplays(cosplays);
                } else {
                    showAlert("Erreur de chargement", "Impossible de charger le contrôleur AjouterCosplay.");
                    return;
                }
            } catch (IOException e) {
                // Handle the IOException
                e.printStackTrace(); // Print the exception stack trace or handle it appropriately
                showAlert("Erreur de chargement", "Impossible de charger la fenêtre AjouterCosplay.");
                return;
            }

// Create a new scene with the root node
            Scene scene = new Scene(root);

// Set the scene to the stage
            stage.setScene(scene);

// Show the stage
            stage.show();

        }*/



    private boolean isValidImagePath(String imagePath) {
        return !imagePath.isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
   private void retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/AjouterCosplay.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage primaryStage = (Stage) ann.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("go");
            primaryStage.centerOnScreen();



    }
}

