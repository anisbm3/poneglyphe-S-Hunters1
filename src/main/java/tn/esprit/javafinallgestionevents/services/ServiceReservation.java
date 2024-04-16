package tn.esprit.javafinallgestionevents.services;

import javafx.scene.control.TextField;
import tn.esprit.javafinallgestionevents.interfaces.IService;
import tn.esprit.javafinallgestionevents.models.Reservation;
import tn.esprit.javafinallgestionevents.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReservation implements IService<Reservation> {
    private Connection cnx;

    public ServiceReservation() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    final ServiceEvenement SE = new ServiceEvenement();




    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (nom_reservation, nb_place, etat, user_id,evenement_id) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_reservation());
            pre.setInt(2, reservation.getNb_place());
            pre.setString(3, reservation.getEtat());
            //pre.setString(4, String.valueOf(reservation.getResdate()));
            pre.setString(4, reservation.getUser_id());
            pre.setString(5, reservation.getEvenement_id());


            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding Reservation: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {

        String req = "UPDATE reservation SET nom_reservation=?, nb_place=?, etat=?, user_id=?, evenement_id=?  WHERE id=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, reservation.getNom_reservation());
            pre.setInt(2, reservation.getNb_place());
            pre.setString(3, reservation.getEtat());
            pre.setString(4, reservation.getUser_id());
            pre.setString(5, reservation.getEvenement_id());
            pre.setInt(6,reservation.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifying reservation: " + e.getMessage());
        }

    }

    @Override
    public void supprimer(Reservation reservation) throws SQLException {

        String req = "DELETE FROM reservation WHERE id=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, reservation.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
        }

    }

    @Override
    public List<Reservation> afficher() throws SQLException {
        //String req = "SELECT * FROM reservation";
        /*String req = "SELECT reservation.*, evenement.id AS evenement_id " +
                "FROM reservation " +
                "INNER JOIN evenement ON reservation.evenement_id = evenement.id";*/

      /*  String req = "SELECT reservation.*, evenement.id " +
                "FROM reservation " +
                " INNER JOIN evenement  ON reservation.evenement_id = evenement.id";*/

       String req = "SELECT reservation.*, evenement.id " +
        "FROM reservation " +
                " INNER JOIN evenement  ON reservation.evenement_id = evenement.id";
        List<Reservation> list = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(res.getInt("id"));
                reservation.setNom_reservation(res.getString("nom_reservation"));
                reservation.setNb_place(res.getInt("nb_place"));
                reservation.setEtat(res.getString("etat"));
                reservation.setUser_id(res.getString("user_id"));
                reservation.setEvenement_id(res.getString("evenement_id"));
                reservation.setResdate(res.getTimestamp("resdate").toLocalDateTime());

                //evenement.setUser_id(res.getTimestamp(5).toLocalDateTime());

                list.add(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Reservations: " + e.getMessage());
        }
        return list;
    }
}