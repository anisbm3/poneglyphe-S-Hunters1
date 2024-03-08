package tn.esprit.services;

/*import eh.gestionlivraison.interfaces.IService1;
import eh.gestionlivraison.models.Panier;
import eh.gestionlivraison.utils.MyDB;*/
import tn.esprit.interfaces.IService1;
import tn.esprit.models.Panier;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ServicePanier implements IService1<Panier> {
    private Connection connection;

    public ServicePanier() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Panier Panier) throws SQLException {
        String req = "INSERT INTO Panier (prod_name, quantity, price, date, panier_id,IDU) " +
                "VALUES ('" + Panier.getProd_name() + "','" + Panier.getQuantity() + "','" + Panier.getPrice() + "','" + Panier.getDate() + "','" + Panier.getPanier_id() + "','" + Panier.getIDU() + "')";


        Statement ste = connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Panier Panier) throws SQLException {
        String req = "update Panier set prod_name=? ,quantity=?, price=?, date=?, panier_id=?, IDU=? where IDP=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, Panier.getProd_name());
        pre.setInt(2, Panier.getQuantity());
        pre.setInt(3, Panier.getPrice());
        pre.setDate(4, new Date(Panier.getDate().getTime()));
        pre.setInt(5, Panier.getPanier_id());
        pre.setInt(7, Panier.getIDU());
        pre.setInt(7, Panier.getIDP());
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
            Panier p = new Panier(res.getInt("IDP"), res.getString("prod_name"), res.getInt("quantity"), res.getInt("price"), res.getDate("date"), res.getInt("panier_id"),res.getInt("IDU"));
            p.setIDP(res.getInt("IDP"));
            p.setProd_name(res.getString("prod_name"));
            p.setQuantity(res.getInt("quantity"));
            p.setPrice(res.getInt("price"));
            p.setDate(res.getDate("date"));
            p.setPanier_id(res.getInt("panier_id"));
            p.setIDP(res.getInt("IDU"));


            list.add(p);
        }
        return list;
    }
    public List<Panier> afficherbyprix(String tri) {
        String req = "SELECT * FROM Panier";
        List<Panier> list = new ArrayList<>();
        try (Statement ste = connection.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Panier Panier = new Panier();
                Panier.setIDP(res.getInt(1));
                Panier.setProd_name(res.getString(2));
                Panier.setQuantity(res.getInt(3));
                Panier.setPrice(res.getInt(4));
                Panier.setDate(res.getDate(5));
                Panier.setPanier_id(res.getInt(6));
                Panier.setIDU(1);

                list.add(Panier);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving paniers: " + e.getMessage());
        }
        if (tri.equals("ASC")) {
            Collections.sort(list, Comparator.comparing(Panier::getPrice));
        } else {
            Collections.sort(list, Comparator.comparing(Panier::getPrice).reversed());
        }
        return list; // Return the sorted list
    }



}