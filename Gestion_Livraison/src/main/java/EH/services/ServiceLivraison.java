package EH.services;
import EH.interfaces.IService;
import EH.models.Livraison;
import EH.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ServiceLivraison implements IService<Livraison> {
    private Connection cnx;

    public ServiceLivraison() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Livraison livraison) {
        String qry = "INSERT INTO `livraison`(`Date`, `Adresse`, `Nom_Client`, `NumTel`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setObject(1, livraison.getDate() != null ? livraison.getDate() : LocalDateTime.now());
            stm.setString(2, livraison.getAdresse());
            stm.setString(3, livraison.getNom_Client());
            stm.setInt(4, livraison.getNumTel());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public ArrayList<Livraison> getAll() {
        ArrayList<Livraison> livraisons = new ArrayList<>();
        String qry = "SELECT * FROM `livraison` ";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setID_Livraison(rs.getInt(1));
                LocalDateTime dateLivraison = rs.getObject(2, LocalDateTime.class);
                // Vérifier si la date est nulle
                if (dateLivraison != null) {
                    l.setDate(dateLivraison);
                    l.setAdresse(rs.getString(3));
                    l.setNom_Client(rs.getString(4));
                    l.setNumTel(rs.getInt(5));
                    livraisons.add(l);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des livraisons", e);
        }
        return livraisons;
    }


    @Override
    public void update(Livraison livraison) {
        String qry = "UPDATE livraison SET Date=?, Adresse=?, Nom_Client=?, NumTel=? WHERE ID_Livraison=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            if (livraison.getDate() == null) {
                stm.setNull(1, Types.NULL);
            } else {
                stm.setObject(1, livraison.getDate());
            }
            stm.setString(2, livraison.getAdresse());
            stm.setString(3, livraison.getNom_Client());
            stm.setInt(4, livraison.getNumTel());
            stm.setInt(5, livraison.getID_Livraison());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la livraison", e);
        }
    }

    @Override
    public boolean delete(Livraison livraison) {
        String qry = "DELETE FROM livraison WHERE ID_Livraison=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, livraison.getID_Livraison());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
