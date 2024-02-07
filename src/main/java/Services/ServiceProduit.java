package Services;
import Entities.Produit;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceProduit implements IService<Produit> {
    private Connection connection;

    public ServiceProduit(){
        connection= MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(Produit Produit) throws SQLException {
        String req = "insert into Produits (Prix, Nom, Category, Description) " +
                "values ('" + Produit.getPrix() + "','" + Produit.getNom() + "','" + Produit.getCategory() + "','" + Produit.getDescription() + "')";

        Statement ste= connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Produit Produit) throws SQLException {
        String req = "update Produits set Prix=?, Nom=?, Category=?, Description=? where ID_Produit=?";
        PreparedStatement pre = connection.prepareStatement(req);

        pre.setInt(1, Produit.getPrix());
        pre.setString(2, Produit.getNom());
        pre.setString(3, Produit.getCategory());
        pre.setString(4, Produit.getDescription());
        pre.setInt(5, Produit.getID());
        pre.executeUpdate();
    }


    @Override
    public void supprimer(Produit Produit) throws SQLException {


        String req = " delete from Produits where   ID_Produit=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,Produit.getID());
        pre.executeUpdate();

    }

    @Override
    public List<Produit> afficher() throws SQLException {

        String req = "select * from Produits";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Produit> list =new ArrayList<>();
        while (res.next()){
            Produit p = new Produit();
            p.setID(res.getInt(1));
            p.setNom(res.getString("nom"));
            p.setPrix(res.getInt(3));
            p.setCategory(res.getString("category"));
            p.setDescription(res.getString("Description"));


            list.add(p);
        }
        return list;
    }
}
