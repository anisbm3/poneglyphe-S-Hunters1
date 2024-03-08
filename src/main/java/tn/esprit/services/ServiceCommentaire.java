package tn.esprit.services;


import tn.esprit.interfaces.IService;

import tn.esprit.models.Commentaire;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceCommentaire implements IService<Commentaire> {
    private Connection connection;

    public ServiceCommentaire() {
        connection = MyDB.getInstance().getConnection();
    }
    final ServiceDebat SD =new ServiceDebat();

    @Override
    public void ajouter(Commentaire commentaire) throws SQLException {
        String req = "INSERT INTO commentaire (Sujet_Debat, Message,ID_User) VALUES (?, ?,?)";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, commentaire.getSujet_Debat());
            pre.setString(2, commentaire.getMessage());
            pre.setString(3, commentaire.getID_User());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }
    public String getSujet_debatByID_Commentaire(int ID_Commentaire) throws SQLException {
        String nomCommentaire = null;
        String req = "SELECT Sujet_Debat FROM Commentaire WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, ID_Commentaire);
            try (ResultSet result = pre.executeQuery()) {
                if (result.next()) {
                    nomCommentaire = result.getString("NOM_Event");
                }
            }
        }
        return nomCommentaire;
    }


    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String req = "UPDATE commentaire SET Sujet_Debat=?, Message=?, ID_USER=?, BLOCK=? WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, commentaire.getSujet_Debat());
            pre.setString(2, commentaire.getMessage());
            pre.setString(3, commentaire.getID_User());
            pre.setString(4, commentaire.getBLOCK());
            pre.setInt(5, commentaire.getID_Commentaire());

            pre.executeUpdate();
        }
    }
    @Override
    public void supprimer(Commentaire commentaire) throws SQLException {
        String req = "DELETE FROM commentaire WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, commentaire.getID_Commentaire());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimerParId(int idCommentaire) throws SQLException {
        String req = "DELETE FROM commentaire WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idCommentaire);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du commentaire : " + e.getMessage());
        }
    }

    @Override
    public List<Commentaire> afficher() throws SQLException {
        String req = "SELECT * FROM commentaire ";
        try (Statement ste = connection.createStatement(); ResultSet res = ste.executeQuery(req)) {
            List<Commentaire> list = new ArrayList<>();
            while (res.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setID_Commentaire(res.getInt("ID_Commentaire"));
                commentaire.setSujet_Debat(res.getString("Sujet_Debat"));
                commentaire.setMessage(res.getString("Message"));
               // commentaire.setID_User(res.getInt("ID_User"));
                commentaire.setBLOCK(res.getString("BLOCK"));



                list.add(commentaire);
            }
            return list;
        }
    }



    public List<Commentaire> afficherByuser(String pseudo) throws SQLException {
        List<Commentaire> list = new ArrayList<>();
        String req = "SELECT * FROM commentaire where ID_User = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setString(1, pseudo);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setID_Commentaire(res.getInt("ID_Commentaire"));
                commentaire.setSujet_Debat(res.getString("Sujet_Debat"));
                commentaire.setMessage(res.getString("Message"));
                //commentaire.setID_User(res.getString("ID_User"));
                commentaire.setBLOCK(res.getString("BLOCK"));



                list.add(commentaire);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Commentaire> afficherbyNOM(String tri) {
        String req = "SELECT * FROM commentaire";
        List<Commentaire> list = new ArrayList<>();
        try (Statement ste = connection.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setID_Commentaire(res.getInt(1));
                commentaire.setSujet_Debat(res.getString(2));
                commentaire.setMessage(res.getString(3));
                //commentaire.setID_User(res.getInt(4));
                commentaire.setBLOCK(res.getString(5));


                list.add(commentaire);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        if (tri.equals("ASC"))
        {
            Collections.sort(list, Comparator.comparing(Commentaire::getSujet_Debat));}
        else {
            Collections.sort(list, Comparator.comparing(Commentaire::getSujet_Debat).reversed());
        }
        return list;
    }




}