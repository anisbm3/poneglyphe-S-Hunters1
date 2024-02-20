package EH.services;
import EH.interfaces.IService;
import EH.models.Livraison;
import EH.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ServiceLivraison implements IService<Livraison> {
    private Connection cnx;

    public ServiceLivraison() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison livraison) {
        String query = "INSERT INTO `livraison`(`ID_Produit`, `ID_Client`, `quantity`, `montant`, `date`) VALUES (?,?,?,?,?)";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, livraison.getID_Produit());
            stm.setInt(2, livraison.getID_Client());
            stm.setInt(3, livraison.getQuantity());
            stm.setFloat(4, livraison.getMontant());
            stm.setTimestamp(5, Timestamp.valueOf(livraison.getDate()));
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout d'une livraison : " + ex.getMessage());
        }
    }

    @Override
    public List<Livraison> afficher() throws SQLException {
        List<Livraison> l = new ArrayList<>();
        String query = "SELECT * FROM livraison";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setID_Livraison(rs.getInt("ID_Livraison"));
                livraison.setID_Produit(rs.getInt("ID_Produit"));
                livraison.setID_Client(rs.getInt("ID_Client"));
                livraison.setQuantity(rs.getInt("quantity"));
                livraison.setMontant(rs.getFloat("montant"));
                livraison.setDate(rs.getTimestamp("date").toLocalDateTime());

                l.add(livraison);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage des livraisons : " + ex.getMessage());
        }
        return l;
    }




    @Override
    public void supprimer(int ID_Livraison) throws SQLException {
        String query = "DELETE FROM livraison WHERE ID_Livraison = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, ID_Livraison);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livraison supprimée avec succès.");
            } else {
                System.out.println("Aucune livraison trouvée avec l'ID : " + ID_Livraison);
            }
        }
    }


    public Livraison SearchById(long ID_Livraison) throws SQLException{
        Statement stm = cnx.createStatement();
        Livraison livraison = new Livraison();
        String query = "select * from livraison where ID_Livraison=?";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setID_Produit(rs.getInt("ID_Produit"));
            livraison.setID_Client(rs.getInt("ID_Client"));
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setDate(rs.getTimestamp(6).toLocalDateTime());}
        return livraison;
    }
    @Override
    public void modifier(int ID_LivraisonModifier, Livraison livraison) throws SQLException {
        String query = "UPDATE `livraison` SET `ID_Produit`=?, `ID_Client`=?, `quantity`=?, `montant`=?, `date`=? WHERE ID_Livraison=?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, livraison.getID_Produit());
            stm.setInt(2, livraison.getID_Client());
            stm.setInt(3, livraison.getQuantity());
            stm.setFloat(4, livraison.getMontant());
            stm.setTimestamp(5, Timestamp.valueOf(livraison.getDate())); // Utilisez Timestamp.valueOf()
            stm.setInt(6, ID_LivraisonModifier);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification d'une livraison : " + ex.getMessage());
        }
    }

}
