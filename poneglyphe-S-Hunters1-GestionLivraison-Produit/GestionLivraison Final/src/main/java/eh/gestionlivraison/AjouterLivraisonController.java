package eh.gestionlivraison;
import eh.gestionlivraison.Services.ServiceLivraison;
import eh.gestionlivraison.models.Livraison;
import eh.gestionlivraison.utils.MyDataBase;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;

public class AjouterLivraisonController  implements Initializable {

    @FXML
    private Button Acceuil;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField NomPrenom;

    @FXML
    private TextField TfMontant;



    @FXML
    private HBox chosenhotelCard;

    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane left_main;

    @FXML
    private ScrollPane scroll;
    private FXMLLoader loader;

    ServiceLivraison sl =new ServiceLivraison();
    int id;
    Livraison l;
    public void initialize(URL url, ResourceBundle rb) {
        loader = new FXMLLoader(getClass().getResource("AjouterLivraison.fxml"));

    }

    @FXML
    private void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GestionLivraison.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void initialize() {
        loader = new FXMLLoader(getClass().getResource("AjouterLivraison.fxml"));
    }
    @FXML
    private GestionLivraisonController gestionLivraisonController;

    public void setGestionLivraisonController(GestionLivraisonController gestionLivraisonController) {
        this.gestionLivraisonController = gestionLivraisonController;
    }
    @FXML
    private ObservableList<String> optionsProduit = FXCollections.observableArrayList();



    public void Enregister(ActionEvent event) throws IOException {
        // Vérifiez si tous les champs sont remplis
        if (NomPrenom.getText().isEmpty() || Adresse.getText().isEmpty() || date.getValue() == null) {
            // Affichez un message d'erreur et quittez la méthode si des champs sont manquants
            System.out.println("Veuillez remplir tous les champs.");
            return;
        }

        // Créez une nouvelle livraison avec les données saisies par l'utilisateur
        Livraison livraison = new Livraison();
        livraison.setNomPrenomClient(NomPrenom.getText());
        livraison.setAdresse(Adresse.getText());
        livraison.setDate(Date.valueOf(date.getValue()));

        // Appelez la méthode add de ServiceLivraison pour ajouter la livraison à la base de données
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        serviceLivraison.add(livraison);

        // Affichez un message de succès
        System.out.println("Livraison ajoutée avec succès.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }}




