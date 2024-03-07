package eh.gestionlivraison;


import eh.gestionlivraison.models.Produit;
import eh.gestionlivraison.Services.ServiceProduit;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.List;

public class AfficherController implements Initializable {

    private ServiceProduit SP= new ServiceProduit();
    private List<VBox> displayedCards = new ArrayList<>();
    @FXML
    private TextField searchField;

    @FXML
    private FlowPane cardLayout2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Produit> produits = null;
        try {
            produits =SP.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        display(produits);

    }
    @FXML
    void searchproduit() throws SQLException {
        // Récupérer le mot-clé de recherche saisi par l'utilisateur
        String keyword = searchField.getText().toLowerCase().trim();

        // Récupérer la liste complète des produits depuis le service ServiceProduit
        List<Produit> produits = SP.afficher();

        // Appliquer le filtre en fonction du mot-clé de recherche
        FilteredList<Produit> filteredProduits = new FilteredList<>(FXCollections.observableArrayList(produits));
        filteredProduits.setPredicate(produit -> produitContainsKeyword(produit, keyword));

        // Mettre à jour l'affichage avec les cartes filtrées
        updateDisplay(filteredProduits);
    }

    private void updateDisplay(List<Produit> produits) {
        // Effacer les cartes existantes
        cardLayout2.getChildren().clear();

        // Afficher les nouvelles cartes
        for (Produit produit : produits) {
            VBox card = createProduit(produit);
            cardLayout2.getChildren().add(card);
            displayedCards.add(card);
        }
    }

    private boolean produitContainsKeyword(Produit produit, String keyword) {
        String id = String.valueOf(produit.getID());
        String stock= String.valueOf(produit.getStock());
        String price = String.valueOf(produit.getPrix());
        return id.contains(keyword) ||
                stock.contains(keyword)||
                price.contains(keyword)||
                produit.getNom().toLowerCase().contains(keyword) ||
                produit.getDescription().toLowerCase().contains(keyword) ||
                produit.getCategory().toLowerCase().contains(keyword);
    }


    @FXML
    void AjouterProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddProduit.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void AffPanier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Paniers.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void RefreshPage(ActionEvent event) {
        List<Produit> produits = null;
        try {
            produits = SP.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cardLayout2.getChildren().clear();
        display(produits);

    }

   /* @FXML
    void goToReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReservation.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }*/


    private void display(List<Produit> produits) {
        for (Produit produit : produits) {
            VBox card = createProduit(produit);
            cardLayout2.getChildren().add(card);
        }
    }


    private void openDetailsPage(Produit produit) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Produit.fxml"));
            Parent root = fxmlLoader.load();
            CardController controller = fxmlLoader.getController();
            if (controller != null) {
                controller.displayProduitDetails(produit);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("ProductDetails");
                stage.show();
            } else {
                System.out.println("Erreur : Contrôleur null pour la page de détails ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VBox createProduit(Produit produit) {
        VBox card = new VBox();
        card.getStyleClass().add("commande-card");
        Label id = new Label("id:" + produit.getID());
        Label Category = new Label("Category:" + produit.getCategory());
        Label nom = new Label("Nom:" + produit.getNom());
        Label prix = new Label("Prix:" + produit.getPrix());
        Label Description = new Label("description:" + produit.getDescription());

        card.getChildren().addAll(id,Category,nom,prix,Description);
        card.setOnMouseClicked(event -> openDetailsPage(produit));
        card.setCursor(Cursor.HAND);
        return card;
    }




}
