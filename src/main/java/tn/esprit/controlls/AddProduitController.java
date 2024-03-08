package tn.esprit.controlls;


import tn.esprit.models.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.services.ServiceProduit;

import java.sql.SQLException;

public class AddProduitController {
    @FXML
    private TextField IDPrix;
    @FXML
    private TextField IDStock;
    @FXML
    private TextField IDNom;
    @FXML
    private TextField IDCategory;
    @FXML
    private TextField IDDescription;

    ServiceProduit sp=new ServiceProduit();

    public AddProduitController() {
    }




    @FXML
    void ajouterBtn(ActionEvent event) {
        try {
            String prixText = IDPrix.getText();
            String nomText = IDNom.getText();
            String categoryText = IDCategory.getText();
            String descriptionText = IDDescription.getText();
            String stocktext = IDStock.getText();

            // Vérifier que les champs ne sont pas vides
            if (prixText.isEmpty() || nomText.isEmpty() || categoryText.isEmpty() || descriptionText.isEmpty()) {
                System.out.println("Veuillez remplir tous les champs.");
            } else {
                // Convertir le texte du champ prix en entier
                int prix = Integer.parseInt(prixText);
                int stock = Integer.parseInt(stocktext);

                // Utiliser les valeurs récupérées comme nécessaire
                sp.ajouter(new Produit(stock,prix, nomText, categoryText, descriptionText));
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur de conversion du prix en entier : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }


}
