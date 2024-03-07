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
    private TextField TfQuantity;

    @FXML
    private ComboBox<String> cb_Produits;

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
        fillcomboProduit();
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

    public void fillcomboProduit() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT Produits FROM panier"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsProduits = FXCollections.observableArrayList();
            while (rs.next()) {
                String Produit = rs.getString("Produits");
                optionsProduits.add(Produit);
                System.out.println("Produit récupéré depuis la base de données : " + Produit);
            }
            cb_Produits.setItems(optionsProduits);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregister(ActionEvent event) {
        System.out.println("NomPrenom: " + NomPrenom.getText());
        System.out.println("Adresse: " + Adresse.getText());
        System.out.println("Produit sélectionné: " + cb_Produits.getValue());
        System.out.println("Quantité: " + TfQuantity.getText());
        System.out.println("Montant: " + TfMontant.getText());
        System.out.println("Date: " + date.getValue());
        String erreurs = "";
        if (TfQuantity.getText().trim().isEmpty()) {
            erreurs += "Quantity vide\n";
        }
        if (TfMontant.getText().trim().isEmpty()) {
            erreurs += "Montant vide\n";
        }
        if (date.getValue() != null
                && date.getValue().isBefore(LocalDate.now())
        ) {
            erreurs += "date must be after\n";
        }
        if (date.getValue() == null) {
            erreurs += "date vide\n";
        }

        if (erreurs.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else {
            // Vérifier l'ID_Pannier
            System.out.println("ID_Pannier: " + Livraison.getID_Pannier());

            // Vérifier le produit sélectionné
            System.out.println("Produit sélectionné: " + cb_Produits.getValue());

            Livraison livraison = new Livraison(NomPrenom.getText(), Adresse.getText(), cb_Produits.getValue(),
                    Integer.parseInt(TfQuantity.getText()),
                    Float.parseFloat(TfMontant.getText()),
                    Date.valueOf(date.getValue()));
            sl.add(livraison);
            showAlert(Alert.AlertType.INFORMATION, "Livraison ajoutée", "La livraison a été ajoutée avec succès.");
        }
    }




    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }}




