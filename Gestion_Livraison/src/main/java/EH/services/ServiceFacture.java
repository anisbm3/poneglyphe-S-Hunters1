package EH.services;

import EH.interfaces.IService;
import EH.models.Facture;
import EH.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ServiceFacture implements IService<Facture> {
    private Connection cnx;

    public ServiceFacture() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Facture facture) {
        String qry = "INSERT INTO `facture`(`NomPrenom`, `ville`, `montant_a_paye`, `date_versement`, `etat`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, facture.getNomPrenom());
            stm.setString(2, facture.getVille());
            stm.setString(3, facture.getMontant_a_paye());
            stm.setObject(4, facture.getDate_versement());
            stm.setString(5, facture.getEtat());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public ArrayList<Facture> getAll() {
        ArrayList<Facture> factures = new ArrayList<>();
        String qry = "SELECT * FROM `facture` ";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Facture f = new Facture();
                f.setID_Facture(rs.getInt(1));
                f.setNomPrenom(rs.getString(2));
                f.setVille(rs.getString(3));
                f.setMontant_a_paye(rs.getString(4));
                f.setDate_versement(rs.getObject(5, LocalDateTime.class));
                f.setEtat(rs.getString(6));
                factures.add(f);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des factures", e);
        }
        return factures;
    }

    @Override
    public void update(Facture facture) {
        String qry = "UPDATE facture SET NomPrenom=?, ville=?, montant_a_paye=?, date_versement=?, etat=? WHERE ID_Facture=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, facture.getNomPrenom());
            stm.setString(2, facture.getVille());
            stm.setString(3, facture.getMontant_a_paye());
            stm.setObject(4, facture.getDate_versement());
            stm.setString(5, facture.getEtat());
            stm.setInt(6, facture.getID_Facture());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la facture", e);
        }
    }

    @Override
    public boolean delete(Facture facture) {
        String qry = "DELETE FROM facture WHERE ID_Facture=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, facture.getID_Facture());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
