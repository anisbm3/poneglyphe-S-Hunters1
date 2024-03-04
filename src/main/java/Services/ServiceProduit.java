package Services;
import Entities.Produit;
import Utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceProduit implements IService<Produit> {
    private Connection connection;

    public ServiceProduit() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Produit Produit) throws SQLException {
        String req = "INSERT INTO Produits (Stock, Prix, Nom, Category, Description) " +
                "VALUES ('" + Produit.getStock() + "','" + Produit.getPrix() + "','" + Produit.getNom() + "','" + Produit.getCategory() + "','" + Produit.getDescription() + "')";


        Statement ste = connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Produit Produit) throws SQLException {
        String req = "update Produits set Stock=? ,Prix=?, Nom=?, Category=?, Description=? where ID_Produit=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Produit.getStock());
        pre.setInt(2, Produit.getPrix());
        pre.setString(3, Produit.getNom());
        pre.setString(4, Produit.getCategory());
        pre.setString(5, Produit.getDescription());
        pre.setInt(6, Produit.getID());
        pre.executeUpdate();
    }


    @Override
    public void supprimer(Produit Produit) throws SQLException {


        String req = " delete from Produits where   ID_Produit=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Produit.getID());
        pre.executeUpdate();

    }

    @Override
    public List<Produit> afficher() throws SQLException {

        String req = "select * from Produits";
        Statement ste = connection.
                createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Produit> list = new ArrayList<>();
        while (res.next()) {
            Produit p = new Produit(res.getInt("ID_Produit"), res.getInt("Stock"), res.getString("Category"), res.getString("Nom"), res.getInt("Prix"), res.getString("Description"));
            p.setID(res.getInt(1));
            p.setStock(res.getInt(2));
            p.setCategory(res.getString("category"));
            p.setNom(res.getString("nom"));
            p.setPrix(res.getInt(5));
            p.setDescription(res.getString("Description"));


            list.add(p);
        }
        return list;
    }

    public List<Produit> Tri() throws SQLException {
        String req = "SELECT * FROM Produits ORDER BY PRIX";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);

        List<Produit> list = new ArrayList<>();

        while (res.next()) {
            Produit p = new Produit(
                    res.getInt("ID_Produit"),
                    res.getInt("Stock"),
                    res.getString("Category"),
                    res.getString("Nom"),
                    res.getInt("Prix"),
                    res.getString("Description")
            );

            list.add(p);
        }

        return list;
    }
    public List<Produit> rechercheDinamyque(String recherche) throws SQLException {
        String sql = "SELECT * FROM PRODUITS WHERE ID_Produit LIKE ? OR Nom LIKE ? OR Category LIKE ? OR Prix LIKE ? OR Stock LIKE ? OR Description LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 6; i++) {
                String searchValue = "%" + recherche + "%";
                if (i == 4 || i == 1 || i == 5) { // Si c'est la colonne est un nombre (Prix ou Stock)
                    try {
                        int number = Integer.parseInt(recherche);
                        statement.setInt(i, number);
                    } catch (NumberFormatException e) {
                        // La recherche n'est pas un nombre, donc ignorez cette colonne pour la recherche
                        statement.setString(i, searchValue);
                    }
                } else {
                    statement.setString(i, searchValue);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            ObservableList<Produit> filteredList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setID(resultSet.getInt("ID_Produit")); // Assurez-vous que la colonne s'appelle "ID_Produit" dans votre base de données
                produit.setStock(resultSet.getInt("Stock"));
                produit.setPrix(resultSet.getInt("Prix"));
                produit.setNom(resultSet.getString("Nom"));
                produit.setCategory(resultSet.getString("Category"));
                produit.setDescription(resultSet.getString("Description"));

                filteredList.add(produit);
            }
            return filteredList;
        }
    }

}
