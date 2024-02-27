package EH.services;

import EH.interfaces.IService;
import EH.models.Facture;
import EH.models.Livraison;
import EH.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFacture implements IService<Facture> {
    private Connection cnx;

    public ServiceFacture() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Facture facture) {

    }

    @Override
    public List<Facture> afficher() throws SQLException {
        List<Facture> lf = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM facture";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            Facture facture = new Facture();
          /*  facture.setIdFacture(rs.getInt("idFacture"));*/
            facture.setID_Livraison(rs.getInt("ID_Livraison"));
            facture.setDatefacture(rs.getTimestamp("datefacture").toLocalDateTime());
            facture.setRemise(rs.getInt("remise"));
            facture.setMontant(rs.getFloat("montant"));
            lf.add(facture);
        }
        return lf;
    }

    @Override
    public boolean supprimer(int idFacture) throws SQLException {
        String query = "DELETE FROM facture WHERE idFacture = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, idFacture);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Facture supprimée avec succès.");
                return true; // Retourner true si la suppression a réussi
            } else {
                System.out.println("Aucune facture trouvée avec l'ID : " + idFacture);
                return false;
            }
        }
    }

    @Override
    public void modifier(int idFacture, Facture facture) throws SQLException {
        String query = "UPDATE facture SET dateFacture=?, montant=? WHERE idFacture=?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setTimestamp(1, Timestamp.valueOf(facture.getDatefacture()));
            stm.setFloat(2, facture.getMontant());
            stm.setInt(3, idFacture);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Facture modifiée avec succès.");
            } else {
                System.out.println("Aucune facture trouvée avec l'ID : " + idFacture);
            }
        }
    }


    public Facture SearchById(long idFacture) throws SQLException {
        Statement stm = cnx.createStatement();
        Facture facture = new Facture();
        String query = "SELECT * FROM facture WHERE idFacture=" + idFacture;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            facture.setIdFacture(rs.getInt("idFacture"));
            facture.setID_Livraison(rs.getInt("ID_Livraison"));
            facture.setDatefacture(rs.getTimestamp("datefacture").toLocalDateTime());
            facture.setRemise(rs.getInt("remise"));
            facture.setMontant(rs.getFloat("montant"));
        }
        return facture;
    }



    public Livraison SearchLivraisonById(long ID_Livraison) throws SQLException {
        Statement stm = cnx.createStatement();
        Livraison livraison = new Livraison();
        String query = "SELECT * FROM livraison WHERE ID_Livraison=" + ID_Livraison;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setDate(rs.getTimestamp("Date").toLocalDateTime());
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setID_Pannier(rs.getInt("ID_Pannier"));
        }
        return livraison;
    }
    @Override
    public boolean deleteAll() {
        String qry = "DELETE FROM facture";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Toutes les facture ont été supprimées avec succès.");
                return true;
            } else {
                System.out.println("Aucune facture trouvée.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de toutes les facture : " + ex.getMessage());
            return false;
        }
    }

    public void AjouterFacture(int ID_Livraison, Facture facture) throws SQLException {
        Livraison l = SearchLivraisonById(ID_Livraison);
        Statement st;
        try {
            st = cnx.createStatement();
            String query = "INSERT INTO facture (ID_Livraison, datefacture, remise, montant) VALUES ('" + ID_Livraison + "', '" + facture.getDatefacture() + "', '" + facture.getRemise() + "', '" + (l.getMontant() * ((100 - facture.getRemise()) * 0.01)) + "')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
