package tn.esprit.controlls;

import tn.esprit.services.ServiceProduit;
import tn.esprit.models.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ModifierProduitController {
    @FXML
    private TextField IDStock;
    @FXML
    private TextField IDID;

    @FXML
    private TextField IDCategory;

    @FXML
    private TextField IDDescription;

    @FXML
    private TextField IDNom;

    @FXML
    private TextField IDPrix;
    ServiceProduit sp = new ServiceProduit();

    private  Produit produit;


    @FXML
    void ModifierBtn(ActionEvent event) {
        try {
            int id = Integer.parseInt(IDID.getText());
            Produit produit = new Produit(id,Integer.parseInt(IDStock.getText()),IDCategory.getText(), IDNom.getText(), Integer.parseInt(IDPrix.getText()), IDDescription.getText());
            sp.modifier(produit);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setData(Produit produit){
        this.produit= produit;
        IDID.setText(String.valueOf(produit.getID()));
        IDCategory.setText(produit.getCategory());
        IDDescription.setText(produit.getDescription());
        IDNom.setText(produit.getNom());
        IDPrix.setText(String.valueOf(produit.getPrix()));
        IDStock.setText(String.valueOf(produit.getStock()));
    }
}

