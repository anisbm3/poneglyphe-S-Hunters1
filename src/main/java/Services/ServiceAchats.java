package Services;
import Entities.Achats;
import Entities.Produit;
import Entities.Panier;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class ServiceAchats implements IService<Achats> {
    private Connection connection;

    public ServiceAchats() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Achats Achats) throws SQLException {
        String req = "insert into ACHATS (IDP,IDPRO,Quantite) " +
                "values ('" + Achats.getIDP() + "','" + Achats.getIDPRO() + "','" + Achats.getQuantite() + "')";


        Statement ste = connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override

    public void modifier(Achats Achats) throws SQLException {
        String req = "update Achats set IDP=?,IDPRO=?,Quantite=? where IDA=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Achats.getIDP());

        pre.setInt(2, Achats.getIDPRO());
        pre.setInt(3, Achats.getQuantite());
        pre.setInt(4, Achats.getIDA());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Achats Achats) throws SQLException {


        String req = " delete from Achats where   IDA=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Achats.getIDA());
        pre.executeUpdate();

    }

    public List<Achats> afficher() throws SQLException {
        String req = "SELECT Achats.*, Produits.ID_Produit " +
                "FROM Achats " +
                "INNER JOIN Produits ON Achats.IDPRO = Produits.ID_Produit";

        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Achats> list = new ArrayList<>();

        while (res.next()) {
            Achats a = new Achats();
            a.setIDA(res.getInt("IDA")); // Assurez-vous que "IDA" est la bonne colonne
            a.setIDP(res.getInt("IDP")); // Assurez-vous que "IDP" est la bonne colonne
            a.setIDPRO(res.getInt("IDPRO")); // Assurez-vous que "IDPRO" est la bonne colonne
            a.setQuantite(res.getInt("Quantite")); // Assurez-vous que "Quantite" est la bonne colonne

            list.add(a);
        }

        return list;
    }

}