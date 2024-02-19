package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Evenement;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvenement implements IService<Evenement> {
    private Connection cnx;

    public ServiceEvenement() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement evenement) {
        String req = "INSERT INTO evenement (Nom_Event, Description_Event, Lieu_Event, Date_Event) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, evenement.getNom_Event());
            pre.setString(2, evenement.getDescription_Event());
            pre.setString(3, evenement.getLieu_Event());
            pre.setString(4, String.valueOf(evenement.getDate_Event()));

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Evenement evenement) {
        String req = "UPDATE evenement SET Nom_Event=?, Description_Event=?, Lieu_Event=?, Date_Event=? WHERE ID_Event=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, evenement.getNom_Event());
            pre.setString(2, evenement.getDescription_Event());
            pre.setString(3, evenement.getLieu_Event());
            pre.setString(4, String.valueOf(evenement.getDate_Event()));
            pre.setInt(5, evenement.getID_Event());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifying event: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement evenement) {
        String req = "DELETE FROM evenement WHERE ID_Event=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, evenement.getID_Event());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
        }
    }

    @Override
    public List<Evenement> afficher() {
        String req = "SELECT * FROM evenement";
        List<Evenement> list = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Evenement evenement = new Evenement();
                evenement.setID_Event(res.getInt(1));
                evenement.setNom_Event(res.getString(2));
                evenement.setDescription_Event(res.getString(3));
                evenement.setLieu_Event(res.getString(4));
                evenement.setDate_Event(res.getTimestamp(5).toLocalDateTime());

                list.add(evenement);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        return list;
    }
}
