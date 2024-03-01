package Controllers;

import Entities.Produit;
import Utils.MyDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class CatalogueCardController implements Initializable {

    @FXML
    private ImageView prod_Image;

    @FXML
    private Label prod_Name;

    @FXML
    private Label prod_Price;

    @FXML
    private Spinner<Integer> prod_Spinner;

    @FXML
    private Button prod_add;

    @FXML
    private AnchorPane prod_cardForm;

    private String prodID;
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    private Alert alert;
    private int totalP;
    private Produit produit;
    private int pr;
    private String prod_update;
    private int stock;
    private SpinnerValueFactory<Integer> spin;

    private int qty;

    public void setData(Produit produit) {
        this.produit = produit;
        prodID = String.valueOf(produit.getID());
        prod_Name.setText(produit.getNom());
        prod_Price.setText(String.valueOf(produit.getPrix()));
        pr = produit.getPrix();
    }

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_Spinner.setValueFactory(spin);
    }

    public void addBtn() {
        panierID(); // Appel à la méthode panierID pour récupérer cID
        LocalDateTime currentDateTime = LocalDateTime.now();

        qty = prod_Spinner.getValue();
        String checkAvailable = "SELECT stock FROM PRODUITS WHERE Nom='" + prod_Name.getText() + "'";
        connect = MyDB.getConnection();

        try {
            prepare = connect.prepareStatement(checkAvailable);
            result = prepare.executeQuery();

            if (result.next() && result.getInt("stock") >= qty && qty > 0) {
                // Stock disponible, procéder à l'ajout au panier
                String insertData = "INSERT INTO PANIER (panier_id, prod_name, quantity, price, date) VALUES (?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertData);
                prepare.setInt(1, cID);
                prepare.setString(2, prod_Name.getText());
                prepare.setInt(3, qty);
                totalP = (qty * pr);
                prepare.setInt(4, totalP);
                Date date = new Date();
                java.sql.Date sqlDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());
                prepare.setDate(5, sqlDate);
                prepare.executeUpdate();

                // Afficher les valeurs ajoutées à la table PANIER
                System.out.println("Values added to PANIER:");
                System.out.println("panier_id: " + cID);
                System.out.println("prod_name: " + prod_Name.getText());
                System.out.println("quantity: " + qty);
                System.out.println("price: " + totalP);
                System.out.println("date: " + cID);
                System.out.println("Insert query: " + insertData);
                // System.out.println("Values: panier_id=" + panier_id + ", prod_name=" + prod_name + ", quantity=" + quantity + ", price=" + price + ", date=" + date);

                // ...
                // Ajoutez cette déclaration pour afficher la valeur de prodID
                System.out.println("prodID before updateStock: " + prodID);

                // Mise à jour du stock dans la table PRODUITS
                int checkStck = result.getInt("stock");
                int upStock = checkStck - qty;

                // Ajoutez cette déclaration pour afficher la requête SQL
                System.out.println("updateStock query: " + "UPDATE PRODUITS SET stock=" + upStock + " WHERE ID_Produit='" + prodID + "'");

                String updateStock = "UPDATE PRODUITS SET stock=" + upStock + " WHERE ID_Produit='" + prodID + "'";
                prepare = connect.prepareStatement(updateStock);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added");
                alert.showAndWait();
            } else {
                // Stock insuffisant ou quantité invalide
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid quantity or out of stock");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int cID; // Utiliser une variable statique pour conserver la valeur entre les instances

    private void panierID() {
        if (cID == 0) { // Si cID n'a pas encore été initialisé
            String sql = "SELECT MAX(panier_id) FROM PANIER";
            connect = MyDB.getConnection();
            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}