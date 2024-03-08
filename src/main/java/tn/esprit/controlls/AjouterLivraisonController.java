package tn.esprit.controlls;

import tn.esprit.services.ServiceLivraison;
import tn.esprit.models.Livraison;
import tn.esprit.utils.MyDB;
import tn.esprit.utils.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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
    private Button Facture;


    @FXML
   private Label label_Prix;

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
    int cID;
    private void panierID() {
        if (cID == 0) { // Si cID n'a pas encore été initialisé
            String sql = "SELECT MAX(panier_id) FROM PANIER";
            Connection connect;
            connect = MyDB.getConnection();
            try {
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery();
                if (result.next()) {
                    cID = result.getInt(1) + 1;
                } else {
                    cID = 1;
                }
                Data.cID = cID;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private double getTotalPriceForPanier(int cID) {
        String sql = "SELECT SUM(price) AS total_price FROM PANIER WHERE panier_id = ?";
        double totalPrice = 0;

        try {
            Connection cnx = MyDataBase.getCnx();
            PreparedStatement prepare = cnx.prepareStatement(sql);
            prepare.setInt(1, cID-1);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                totalPrice = result.getDouble("total_price");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return totalPrice;
    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {
       panierID();
        double totalPanierPrice = getTotalPriceForPanier(cID);

        String totalPanierPriceText = String.valueOf(totalPanierPrice);
        label_Prix.setText(totalPanierPriceText);
    //    System.out.println(totalPanierPrice);
        loader = new FXMLLoader(getClass().getResource("tn/esprit/AjouterLivraison.fxml"));

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




