package tn.esprit.services;


import tn.esprit.controlls.Data;
import tn.esprit.interfaces.IService2;
import tn.esprit.models.Livraison;
import tn.esprit.utils.MyDataBase;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import static tn.esprit.controlls.Data.cID;
//import static eh.gestionlivraison.Data.cID;


public class ServiceLivraison implements IService2<Livraison> {
    private static Connection cnx;
int cID;

    public ServiceLivraison() {
        cnx = MyDataBase.getInstance().getCnx();
    }



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
    public void add(Livraison livraison) {
        // Vérifiez si la date de livraison est nulle
        panierID();
        if (livraison.getDate() == null) {
            throw new IllegalArgumentException("La date de livraison ne peut pas être null");
        }

        try {
            // Utilisez cID directement pour récupérer le prix total du panier

            // Insérez la livraison dans la table "livraison"
            String queryLivraison = "INSERT INTO livraison (ID_Livraison, NomPrenomClient, Adresse, panier_id, montant, Date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stm = cnx.prepareStatement(queryLivraison)) {
                stm.setInt(1, livraison.getID_Livraison());
                stm.setString(2, livraison.getNomPrenomClient());
                stm.setString(3, livraison.getAdresse());
                stm.setInt(4, cID-1);
                double totalPanierPrice = getTotalPriceForPanier(cID-1);

                stm.setDouble(5, totalPanierPrice);
                stm.setDate(6, livraison.getDate());
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public ArrayList<Livraison> getAll() {
        ArrayList<Livraison> livraisons = new ArrayList<>();
        String query = "SELECT ID_Livraison,NomPrenomClient, Adresse,panier_id, montant, date FROM livraison";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setID_Livraison(rs.getInt("ID_Livraison"));
                livraison.setNomPrenomClient(rs.getString("NomPrenomClient"));
                livraison.setAdresse(rs.getString("Adresse"));
                //  livraison.setQuantity(rs.getInt("quantity"));
                livraison.setMontant(rs.getFloat("montant"));
                livraison.setDate(rs.getDate("date"));

                int idPannier = rs.getInt("panier_id");


                // Récupérer le nom du produit depuis la table "panier"
                // Récupérer le nom du produit depuis la table "panier"
                String queryProduit = "SELECT price FROM panier WHERE panier_id = ?";
                try {
                    PreparedStatement stmProduit = cnx.prepareStatement(queryProduit);
                    stmProduit.setInt(1, idPannier);
                    ResultSet rsProduit = stmProduit.executeQuery();
                    if (rsProduit.next()) {
                        livraison.setMontant(rsProduit.getInt("price"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // livraison.setMontant("Erreur de récupération des produits");
                }

                livraisons.add(livraison);

                // Afficher les données de la livraison
                System.out.println("NomPrenomClient: " + livraison.getNomPrenomClient());
                System.out.println("Adresse: " + livraison.getAdresse());
                //   System.out.println("Quantity: " + livraison.getQuantity());
                System.out.println("Montant: " + livraison.getMontant());
                System.out.println("Date: " + livraison.getDate());
                System.out.println("panier_id: " + idPannier);
                //   System.out.println("Produits: " + livraison.getProd_name());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraisons;
    }
    private double getTotalPriceForPanier(int cID) {
        String sql = "SELECT SUM(price) AS total_price FROM PANIER WHERE panier_id = ?";
        double totalPrice = 0;

        try {
            cnx = MyDataBase.getCnx();
            PreparedStatement prepare = cnx.prepareStatement(sql);
            prepare.setInt(1, cID);
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
    public  boolean delete(Livraison livraison) {
        String query = "DELETE FROM livraison WHERE ID_Livraison = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, livraison.getID_Livraison());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public  boolean deleteAll() {
        String query = "DELETE FROM livraison";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllByPannierId(int panier_id ) {
        try {
            // Supprimer les livraisons liées à une pannier spécifié
            String query = "DELETE FROM livraison WHERE panier_id  = ?";
            int rowsDeleted;
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
                stm.setInt(1, panier_id );
                rowsDeleted = stm.executeUpdate();
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Livraison getElementByIndex(int id) {
        String query = "SELECT * FROM livraison WHERE ID_Livraison = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Livraison livraison = new Livraison();
                    livraison.setID_Livraison(rs.getInt("ID_Livraison"));
                    livraison.setNomPrenomClient(rs.getString("NomPrenomClient"));
                    livraison.setAdresse(rs.getString("Adresse"));
                    livraison.setPanier_id(rs.getInt("panier_id"));
                   // livraison.setQuantity(rs.getInt("quantity"));
                    livraison.setMontant(rs.getFloat("montant"));
                    livraison.setDate(rs.getDate("date"));
                    return livraison;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Livraison livraison) {
        String query = "UPDATE livraison SET NomPrenomClient=?,Adresse=?,panier_id=?,  montant=?, date=? WHERE ID_Livraison=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, livraison.getNomPrenomClient());
            stm.setString(2, livraison.getAdresse());
            stm.setInt(3, livraison.getPanier_id());
        //    stm.setInt(4, livraison.getQuantity());
            stm.setFloat(4, livraison.getMontant());
            stm.setDate(5, livraison.getDate());
            stm.setInt(6, livraison.getID_Livraison());


            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livraison updated: " + livraison);

            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
}

