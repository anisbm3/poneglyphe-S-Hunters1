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
            String query ="INSERT INTO `livraison`(`ID_Livraison`, `quantity`, `montant`, `ID_Produit`, `ID_Client`) VALUES ('"+livraison.getDate()+"','"+livraison.getQuantity()+"','"+livraison.getMontant()+"','"+livraison.getID_Produit()+"','"+livraison.getID_Client()+"')";
            st.executeUpdate(query);}
        catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }

    @Override
    public List<Livraison> afficher() throws SQLException {
        List<Livraison> l  = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM livraison";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            Livraison livraison = new Livraison();
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setDate(rs.getTimestamp(2).toLocalDateTime());
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setID_Produit(rs.getInt("ID_Produit"));
            livraison.setID_Client(rs.getInt("ID_Client"));
            l.add(livraison);
        }

        return l;
    }

    @Override
    public void supprimer(int idcmd) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from livraison where ID_Livraison=?";
        stm.executeUpdate(query);
    }

    public Livraison SearchById(long ID_Livraison) throws SQLException{
        Statement stm = cnx.createStatement();
        Livraison livraison = new Livraison();
        String query = "select * from livraison where ID_Livraison=?";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            livraison.setID_Livraison(rs.getInt("ID_Livraison"));
            livraison.setDate(rs.getTimestamp(2).toLocalDateTime());
            livraison.setQuantity(rs.getInt("quantity"));
            livraison.setMontant(rs.getFloat("montant"));
            livraison.setID_Produit(rs.getInt("ID_Produit"));
            livraison.setID_Client(rs.getInt("ID_Client"));;}
        return livraison;
    }
    @Override
    public void modifier(int ID_LivraisonModifier, Livraison livraison) throws SQLException {
        Statement stm = cnx.createStatement();
        Livraison l =SearchById(ID_LivraisonModifier);
        String query = "UPDATE `livraison` SET `date`='"+LocalDateTime.now()+"',`quantity`='"+livraison.getQuantity()+"',`montant`='"+livraison.getMontant()+"',`ID_Produit`='"+livraison.getID_Produit()+"',`ID_Client`='"+Livraison.getID_Client()+"' where ID_Livraison ="+l.getID_Livraison();
        stm.executeUpdate(query);
    }
}