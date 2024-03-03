package Services;
import Entities.Panier;
import Entities.Produit;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServicePanier implements IService<Panier> {
    private Connection connection;

    public ServicePanier() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Panier Panier) throws SQLException {
        String req = "INSERT INTO Panier (prod_name, quantity, price, date, panier_id) " +
                "VALUES ('" + Panier.getProd_name() + "','" + Panier.getQuantity() + "','" + Panier.getPrice() + "','" + Panier.getDate() + "','" + Panier.getPanier_id() + "')";


        Statement ste = connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Panier Panier) throws SQLException {
        String req = "update Panier set prod_name=? ,quantity=?, price=?, date=?, panier_id=? where IDP=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, Panier.getProd_name());
        pre.setInt(2, Panier.getQuantity());
        pre.setInt(3, Panier.getPrice());
        pre.setDate(4, new java.sql.Date(Panier.getDate().getTime()));
        pre.setInt(5, Panier.getPanier_id());
        pre.setInt(6, Panier.getIDP());
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
        Statement ste = connection.
                createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Panier> list = new ArrayList<>();
        while (res.next()) {
            Panier p = new Panier(res.getInt("IDP"), res.getString("prod_name"), res.getInt("quantity"), res.getInt("price"), res.getDate("date"), res.getInt("panier_id"));
            p.setIDP(res.getInt("IDP"));
            p.setProd_name(res.getString("prod_name"));
            p.setQuantity(res.getInt("quantity"));
            p.setPrice(res.getInt("price"));
            p.setDate(res.getDate("date"));
            p.setPanier_id(res.getInt("panier_id"));


            list.add(p);
        }
        return list;
    }



}