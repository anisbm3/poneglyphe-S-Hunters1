package tn.esprit.controlls;

import tn.esprit.models.Panier;
import tn.esprit.services.ServicePanier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Card2Controller {
    @FXML
    private Label dateLabel;

    @FXML
    private Button modifierPanier;

    @FXML
    private Label panierIdLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label prodNameLabel;

    @FXML
    private Label quantityLabel;
    private ServicePanier SP = new ServicePanier();
    private Panier paniers;
    public void displayPanierDetails(Panier panier) {
        paniers = panier;
        panierIdLabel.setText("panier_id: " + panier.getPanier_id());
        prodNameLabel.setText("Nom:" + panier.getProd_name());
        quantityLabel.setText("quantity:" + panier.getQuantity());
        priceLabel.setText("prix: " + panier.getPrice());
        dateLabel.setText("description:" + panier.getDate());
    }
}
