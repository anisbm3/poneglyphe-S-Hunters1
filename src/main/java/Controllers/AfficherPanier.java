package Controllers;

import Entities.Panier;
import Entities.Produit;
import Services.ServicePanier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherPanier implements  Initializable  {



    @FXML
    private Button triBtn;
    private ServicePanier SP= new ServicePanier();
    private List<VBox> displayedCards1 = new ArrayList<>();


    @FXML
    private FlowPane cardLayout2;



    private String tri="ASC";
    private int i = 0;
    @FXML
    void OnClickedTri(ActionEvent event) {
        if (i % 2 == 0) {
            tri = "ASC";
        } else {
            tri = "DESC";
        }
        i++;
        RefreshPage(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Panier> panier = null;
        try {
            panier =SP.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        display(panier);
        triBtn.setOnAction(this::OnClickedTri);


    }



    @FXML
    void RefreshPage(ActionEvent event) {
        List<Panier> Panier = null;
        Panier = SP.afficherbyprix(tri); // Utilize the sorted list
        cardLayout2.getChildren().clear();
        display(Panier);
    }




    private void display(List<Panier> Panier) {
        for (Panier panier : Panier) {
            VBox card = createProduit(panier);
            cardLayout2.getChildren().add(card);
        }
    }


    private void openDetailsPage(Panier Panier) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Commande.fxml"));
            Parent root = fxmlLoader.load();
            Card2Controller controller = fxmlLoader.getController();
            if (controller != null) {
                controller.displayPanierDetails(Panier);
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

    private VBox createProduit(Panier Panier) {
        VBox card = new VBox();
        card.getStyleClass().add("commande-card");
        Label panier_id = new Label("id:" + Panier.getPanier_id());
        Label prod_name = new Label("name:" + Panier.getProd_name());
        Label quantity = new Label("Nom:" + Panier.getQuantity());
        Label price = new Label("Prix:" + Panier.getPrice());
        Label date = new Label("description:" + Panier.getDate());

        card.getChildren().addAll(panier_id,prod_name,quantity,price,date);
        card.setOnMouseClicked(event -> openDetailsPage(Panier));
        card.setCursor(Cursor.HAND);
        return card;
    }




}


