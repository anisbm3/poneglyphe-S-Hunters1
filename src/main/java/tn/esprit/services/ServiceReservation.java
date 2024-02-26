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
    final ServiceEvenement SE =new ServiceEvenement();

    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (Nom_Reseervation, NB_Places, Etat, NOM_Event) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_Reseervation());
            pre.setInt(2, reservation.getNB_Places());
            pre.setString(3, reservation.getEtat());
            pre.setString(4, reservation.getNOM_Event());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }
    public String getNomByReservationId(int reservationId) throws SQLException {
        String nomEvent = null;
        String req = "SELECT NOM_Event FROM reservation WHERE ID_Reservation=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, reservationId);
            try (ResultSet result = pre.executeQuery()) {
                if (result.next()) {
                    nomEvent = result.getString("NOM_Event");
                }
            }
        }
        return nomEvent;
    }


    @Override
    public void modifier(Reservation reservation) throws SQLException {
        String req = "UPDATE reservation SET Nom_Reseervation=?, NB_Places=?, Etat=?, NOM_Event=? WHERE ID_Reservation=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_Reseervation());
            pre.setInt(2, reservation.getNB_Places());
            pre.setString(3, reservation.getEtat());
            pre.setString(4, reservation.getNOM_Event());
            pre.setInt(5, reservation.getID_Reservation());

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
        String req = "SELECT * FROM reservation ";
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            List<Reservation> list = new ArrayList<>();
            while (res.next()) {
                Reservation reservation = new Reservation();
                reservation.setID_Reservation(res.getInt("ID_Reservation"));
                reservation.setNom_Reseervation(res.getString("Nom_Reseervation"));
                reservation.setNB_Places(res.getInt("NB_Places"));
                reservation.setEtat(res.getString("Etat"));
                reservation.setNOM_Event(res.getString("NOM_Event"));


                list.add(reservation);
            }
            return list;
        }
    }


}
