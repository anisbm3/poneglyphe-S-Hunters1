package tn.esprit.services;


import tn.esprit.interfaces.IService;
import tn.esprit.models.Reservation;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReservation implements IService<Reservation> {

    private Connection cnx;

    public ServiceReservation() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (Nom_Reseervation, NB_Places, Etat, ID_Event) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_Reseervation());
            pre.setInt(2, reservation.getNB_Places());
            pre.setString(3, reservation.getEtat());
            pre.setInt(4, reservation.getID_Event());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }


    @Override
    public void modifier(Reservation reservation) throws SQLException {
        String req = "UPDATE reservation SET Nom_Reseervation=?, NB_Places=?, Etat=? WHERE ID_Reservation=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_Reseervation());
            pre.setInt(2, reservation.getNB_Places());
            pre.setString(3, reservation.getEtat());
            pre.setInt(4, reservation.getID_Reservation());

            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Reservation reservation) throws SQLException {
        String req = "DELETE FROM reservation WHERE ID_Reservation=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, reservation.getID_Reservation());
            pre.executeUpdate();
        }
    }

    @Override
    public List<Reservation> afficher() throws SQLException {
        String req = "SELECT reservation.*, evenement.ID_Event " +
                "FROM reservation " +
                " INNER JOIN evenement  ON reservation.ID_Event = evenement.ID_Event ";
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            List<Reservation> list = new ArrayList<>();
            while (res.next()) {
                Reservation reservation = new Reservation();
                reservation.setID_Reservation(res.getInt("ID_Reservation"));
                reservation.setNom_Reseervation(res.getString("Nom_Reseervation"));
                reservation.setNB_Places(res.getInt("NB_Places"));
                reservation.setEtat(res.getString("Etat"));
                reservation.setID_Event(res.getInt("ID_Event"));
                //reservation.setID_User(res.getInt("ID_User")); // Assuming you have a setter for Username in Reservation class
                //System.out.println("ID d'utilisateur: " + reservation.getId_USER());

                list.add(reservation);
            }
            return list;
        }
    }


}
