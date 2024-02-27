package Services;
import Entities.Produit;
import Entities.Panier;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class ServicePanier implements IService<Panier> {
    private Connection connection;

    public ServicePanier() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Panier Panier) throws SQLException {
        String req = "insert into Panier (IDU) " +
                "values ('" + Panier.getIDU() + "' )";


        Statement ste = connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override

    public void modifier(Panier Panier) throws SQLException {
        String req = "update Panier set IDU=? where IDP=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Panier.getIDU());

        pre.setInt(2, Panier.getIDP());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Panier Panier) throws SQLException {


        String req = " delete from Panier where   IDP=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, Panier.getIDP());
        pre.executeUpdate();

    }

    @Override
    public List<Panier> afficher() throws SQLException {

        String req = "select * from Panier";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Panier> list = new ArrayList<>();
        while (res.next()) {
            Panier pa = new Panier();
            pa.setIDP(res.getInt(1));
            pa.setIDU(res.getInt(2));



            list.add(pa);
        }
        return list;
    }
}
