package eh.gestionlivraison;
import eh.gestionlivraison.models.Produit;
import eh.gestionlivraison.Services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class CardController {

    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private Label id;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    private ServiceProduit SP = new ServiceProduit();
    private Produit produits;

    @FXML
    void supprimerProduit(ActionEvent event) throws SQLException {
        SP.supprimer(produits);
        Stage stage = (Stage) id.getScene().getWindow();
        stage.close();
    }

    public void displayProduitDetails(Produit produit) {
        produits = produit;
        id.setText("Stock: " + produit.getStock());
        category.setText("category:" + produit.getCategory());
        nom.setText("nom:" + produit.getNom());
        prix.setText("prix: " + produit.getPrix());
        description.setText("description:" + produit.getDescription());
    }

    @FXML
    void modifierProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Update Produit.fxml"));
            Parent root = loader.load();
            ModifierProduitController editTicketController = loader.getController();
            editTicketController.setData(produits);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit ");
            stage.show();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la vue de modification : " + e.getMessage());
        }

    }
    // Vous pouvez ajouter d'autres méthodes ou logique selon vos besoins
}
