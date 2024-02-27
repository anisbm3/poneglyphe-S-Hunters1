package eh.gestionlivraison.Services;

import eh.gestionlivraison.models.Facture;
import eh.gestionlivraison.interfaces.IService;
import eh.gestionlivraison.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceFacture implements IService<Facture> {
    private Connection cnx;

    public ServiceFacture() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Facture facture) {
        String query = "INSERT INTO facture (idFacture, ID_Livraison, NomProduit, datefacture, remise, montant) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, facture.getIdFacture());
            stm.setInt(2, facture.getID_Livraison());
            stm.setString(3, facture.getNomProduit());
            stm.setTimestamp(4, Timestamp.valueOf(facture.getDatefacture()));
            stm.setInt(5, facture.getRemise());
            stm.setFloat(6, facture.getMontant());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Facture> getAll() {
        ArrayList<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Facture facture = new Facture();
                facture.setIdFacture(rs.getInt("idFacture"));
                facture.setID_Livraison(rs.getInt("ID_Livraison"));
                facture.setNomProduit(rs.getString("NomProduit"));
                facture.setDatefacture(rs.getTimestamp("datefacture").toLocalDateTime());
                facture.setRemise(rs.getInt("remise"));
                facture.setMontant(rs.getFloat("montant"));
                factures.add(facture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    @Override
    public boolean update(Facture facture) {
        String query = "UPDATE facture SET ID_Livraison=?, NomProduit=?, datefacture=?, remise=?, montant=? WHERE idFacture=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, facture.getID_Livraison());
            stm.setString(2, facture.getNomProduit());
            stm.setTimestamp(3, Timestamp.valueOf(facture.getDatefacture()));
            stm.setInt(4, facture.getRemise());
            stm.setFloat(5, facture.getMontant());
            stm.setInt(6, facture.getIdFacture());
            stm.executeUpdate();
            System.out.println("Facture updated: " + facture);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Facture facture) {
        String query = "DELETE FROM facture WHERE idFacture = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, facture.getIdFacture());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        String query = "DELETE FROM facture";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllByLivraisonId(int ID_Livraison) {
        try {
            // Supprimer les factures liées à une livraison spécifiée
            String query = "DELETE FROM facture WHERE ID_Livraison = ?";
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, ID_Livraison);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Facture getElementByIndex(int id) {
        String query = "SELECT * FROM facture WHERE idFacture = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Facture facture = new Facture();
                facture.setIdFacture(rs.getInt("idFacture"));
                facture.setID_Livraison(rs.getInt("ID_Livraison"));
                facture.setNomProduit(rs.getString("NomProduit"));
                facture.setDatefacture(rs.getTimestamp("datefacture").toLocalDateTime());
                facture.setRemise(rs.getInt("remise"));
                facture.setMontant(rs.getFloat("montant"));
                return facture;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

