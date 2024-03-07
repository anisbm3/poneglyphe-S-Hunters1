package eh.gestionlivraison;

import eh.gestionlivraison.Services.ServiceFacture;
import eh.gestionlivraison.Services.ServiceLivraison;
import eh.gestionlivraison.models.Facture;
import eh.gestionlivraison.models.Livraison;
import eh.gestionlivraison.utils.MyDataBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class AjouterFactureController {

    @FXML
    private Button Acceuil;

    @FXML
    private TextField MontantAvecRemise;

    @FXML
    private ComboBox<String> cb_Adresse;

    @FXML
    private ComboBox<Date> cb_Date;

    @FXML
    private ComboBox<String> cb_NomPrenom;

    @FXML
    private ComboBox<Float> cb_montant;

    @FXML
    private ComboBox<Integer> cb_quantity;

    @FXML
    private HBox chosenhotelCard;

    @FXML
    private ComboBox<String> cb_Produits;
    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane left_main;

    @FXML
    private Rectangle montant;

    @FXML
    private TextField remise;

    @FXML
    private ScrollPane scroll;

    private ServiceFacture sf = new ServiceFacture();
    private int id;
    private Facture f;
    private FXMLLoader loader;

    public void initialize(URL url, ResourceBundle rb) {
        loader = new FXMLLoader(getClass().getResource("AjouterFacture.fxml"));

    }

    @FXML
    private void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GestionFacture.fxml"));
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
        loader = new FXMLLoader(getClass().getResource("AjouterFacture.fxml"));

        fillcomboNomPrenom();
        fillcomboAdresse();
        fillcomboLMantantAvantRemise();
        fillcomboDateLivraison();
    }


    @FXML
    private GestionLivraisonController gestionLivraisonController;

    public void setGestionLivraisonController(GestionLivraisonController gestionLivraisonController) {
        this.gestionLivraisonController = gestionLivraisonController;
    }
    @FXML
    private ObservableList<String> optionsNomPrenom = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> optionsAdresse = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> optionsMantantAvantRemise = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> optionsDateLivraison = FXCollections.observableArrayList();
    private void fillcomboNomPrenom() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT NomPrenomClient FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsNomPrenom = FXCollections.observableArrayList();
            while (rs.next()) {
                String NomPrenomClient = rs.getString("NomPrenomClient");
                optionsNomPrenom.add(NomPrenomClient);
                System.out.println("NomPrenom récupéré depuis la base de données : " + NomPrenomClient);
            }
            cb_NomPrenom.setItems(optionsNomPrenom);
        } catch (SQLException ex) {
            /* Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

    private void fillcomboAdresse() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT Adresse FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsAdresse = FXCollections.observableArrayList();
            while (rs.next()) {
                String Adresse = rs.getString("Adresse");
                optionsAdresse.add(Adresse);
                System.out.println("Adresse récupéré depuis la base de données : " + Adresse);
            }
            cb_Adresse.setItems(optionsAdresse);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    private void fillcomboLMantantAvantRemise() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT montant FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<Float> optionsMantantAvantRemise = FXCollections.observableArrayList();
            while (rs.next()) {
                String montant = rs.getString("montant");
                optionsMantantAvantRemise.add(Float.parseFloat(montant));
                System.out.println("MantantAvantRemiserécupéré depuis la base de données : " + montant);
            }
            cb_montant.setItems(optionsMantantAvantRemise);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcomboDateLivraison() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT date FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<Date> optionsDateLivraison = FXCollections.observableArrayList();
            while (rs.next()) {
                Date date = rs.getDate("date");
                optionsDateLivraison.add(date);
                System.out.println("Date récupéré depuis la base de données : " + date);
            }
            cb_Date.setItems(optionsDateLivraison);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregister(ActionEvent event) throws SQLException {
        System.out.println("Remise: " + remise.getText());
        System.out.println("MontantAvecRemise: " + MontantAvecRemise.getText());
        System.out.println("Date sélectionné: " + date.getValue());
        System.out.println("NomPreno sélectionné: " + cb_NomPrenom.getValue());
        System.out.println("Adresse sélectionné: " + cb_Adresse.getValue());
        System.out.println("MontantAvantRemise: " + cb_montant.getValue());
        System.out.println("DateLivraison: " + cb_Date.getValue());

        String erreurs = "";
        if (date.getValue() != null
                && date.getValue().isBefore(LocalDate.now())
        ) {
            erreurs += "date must be after\n";
        }
        if (date.getValue() == null) {
            erreurs += "date vide\n";
        }
        System.out.println("Erreurs: " + erreurs);
        if (erreurs.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else {
            Facture facture = new Facture(
                    Integer.parseInt(remise.getText()),
                    Float.parseFloat(MontantAvecRemise.getText()),
                    java.sql.Date.valueOf(date.getValue()),
                    cb_NomPrenom.getValue(),
                    cb_Adresse.getValue(),
                    cb_montant.getValue(),
                    cb_Date.getValue());

            sf.add(facture);
            showAlert(Alert.AlertType.INFORMATION, "Facture ajoutée", "La facture a été ajoutée avec succès.");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

