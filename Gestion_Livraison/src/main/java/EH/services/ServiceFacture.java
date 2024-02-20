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
            facture.setIdFacture(rs.getInt("idFacture"));
            facture.setID_Livraison(rs.getInt("ID_Livraison"));
            facture.setDatefacture(rs.getTimestamp("datefacture").toLocalDateTime());
            facture.setRemise(rs.getInt("remise"));
            facture.setMontant(rs.getFloat("montant"));
            lf.add(facture);
        }
        return lf;
    }

    @Override
    public void supprimer(int idFacture) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM facture WHERE idFacture = " + idFacture;
        stm.executeUpdate(query);
    }

    @Override
    public void modifier(int id, Facture facture) throws SQLException {

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

    public void modifier(int idFactureModifier, int ID_Livraison, Facture facture) throws SQLException {
        Statement stm = cnx.createStatement();
        Facture f = SearchById(idFactureModifier);
        Livraison l = SearchLivraisonById(f.getID_Livraison());
        String query = "UPDATE facture SET ID_Livraison = '" + facture.getID_Livraison() + "', datefacture = '" + facture.getDatefacture() + "', remise = '" + facture.getRemise() + "', montant = '" + (l.getMontant() * facture.getRemise()) + "' WHERE idFacture =" + f.getIdFacture();
        stm.executeUpdate(query);
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
            livraison.setID_Produit(rs.getInt("ID_Pannier"));
        }
        return livraison;
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
