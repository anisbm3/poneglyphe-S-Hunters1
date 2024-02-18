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
        Statement st;
        try {
            st = cnx.createStatement();
            String query = "INSERT INTO `livraison`(`Date`, `Adresse`, `Nom_Client`, `NumTel`, `quantity`, `montant`, `ID_Produit`) VALUES ('" + livraison.getDate() + "','" + livraison.getAdresse() + "','" + livraison.getNom_Client() + "','" + livraison.getNumTel() + "','" + livraison.getQuantity() + "','" + livraison.getMontant() + "','" + livraison.getID_Produit() + "')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Livraison> afficher() throws SQLException {
        List<Livraison> LL = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM livraison";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            Livraison livraison = new Livraison();
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setDate(rs.getTimestamp("Date").toLocalDateTime());
            livraison.setAdresse(rs.getString("Adresse"));
            livraison.setNom_Client(rs.getString("Nom_Client"));
            livraison.setNumTel(rs.getInt("NumTel"));
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setID_Produit(rs.getInt("ID_Produit"));
            LL.add(livraison);
        }

        return LL;
    }

    @Override
    public void supprimer(int ID_Livraison) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from livraison where ID_Livraison =" + ID_Livraison;
        stm.executeUpdate(query);
    }

    public Livraison SearchById(long ID_Livraison) throws SQLException {
        Statement stm = cnx.createStatement();
        Livraison livraison = new Livraison();
        String query = "select * from livraison where ID_Livraison=" + ID_Livraison;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setDate(rs.getTimestamp("Date").toLocalDateTime());
            livraison.setAdresse(rs.getString("Adresse"));
            livraison.setNom_Client(rs.getString("Nom_Client"));
            livraison.setNumTel(rs.getInt("NumTel"));
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setID_Produit(rs.getInt("ID_Produit"));
        }
        return livraison;
    }

    @Override
    public void modifier(int ID_Livraison, Livraison livraison) throws SQLException {
        Statement stm = cnx.createStatement();
        Livraison r = SearchById(ID_Livraison);
        String query = "UPDATE `livraison` SET `Date`='" + LocalDateTime.now() + "',`Adresse`='" + livraison.getAdresse() + "',`Nom_Client`='" + livraison.getNom_Client() + "',`NumTel`='" + livraison.getNumTel() + "',`quantity`='" + livraison.getQuantity() + "',`montant`='" + livraison.getMontant() + "',`ID_Produit`='" + livraison.getID_Produit() + "' where ID_Livraison =" + r.getID_Livraison();
        stm.executeUpdate(query);
    }
}
