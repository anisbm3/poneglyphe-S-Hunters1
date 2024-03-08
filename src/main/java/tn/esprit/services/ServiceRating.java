package tn.esprit.services;


import tn.esprit.interfaces.IService;

import tn.esprit.models.Rating;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRating implements IService<Rating> {


    private Connection connection;

    public ServiceRating() {
        connection = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Rating rating) throws SQLException {
        String req = "INSERT INTO rating (ID_User,ID_Debat,Rating) VALUES (?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setString(1, rating.getID_User());
            stm.setInt(2, rating.getID_Debat());
            stm.setInt(3, rating.getRating());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier(Rating rating) throws SQLException {
        String req = "UPDATE rating SET Rating=?   WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, rating.getRating());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Rating rating) throws SQLException {
        String req = "DELETE FROM rating WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, rating.getID_Debat());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimerParId(int idDebat) throws SQLException {
        String req = "DELETE FROM rating WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idDebat);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du débat : " + e.getMessage());
        }
    }

    @Override
    public List<Rating> afficher() throws SQLException {
        String req = "SELECT * FROM rating";
        List<Rating> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Rating r = new Rating();
                r.setID_Debat(res.getInt("ID_Debat"));
                r.setRating(res.getInt("Rating"));
                list.add(r);
            }
        }
        return list;
    }
  /*  public List<Rating> afficherbyuser() throws SQLException {

        String req = "SELECT * FROM rating WHERE ID_User=?";

        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Rating r = new Rating();
                r.setID_Debat(res.getInt("ID_Debat"));
                r.setRating(res.getInt("Rating"));
                list.add(r);
            }
        }
        return list;
    }*/
}
