package tn.esprit.services;


import tn.esprit.interfaces.IService;

import tn.esprit.models.Debat;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceDebat implements IService<Debat> {
    private Connection connection;

    public ServiceDebat() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Debat debat) {
        String req = "INSERT INTO debat (Sujet_Debat, Description_Debat,Nom_Anime,ID_User) VALUES (?, ?, ?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setString(1, debat.getSujet_Debat());
            stm.setString(2, debat.getDescription_Debat());
            stm.setString(3, debat.getNom_Anime());
            stm.setString(4, debat.getID_User());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Debat> searchByName(String name) throws SQLException {
        List<Debat> searchResults = new ArrayList<>();
        String query = "SELECT * FROM debates WHERE nom_anime LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%"); // Use wildcard to search for similar names

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Create a new Debat object and populate its fields from the ResultSet
                    Debat debat = new Debat();
                    debat.setID_Debat(resultSet.getInt("id"));
                    debat.setNom_Anime(resultSet.getString("nom_anime"));
                    debat.setDescription_Debat(resultSet.getString("description_debat"));
                    debat.setSujet_Debat(resultSet.getString("sujet_debat"));

                    // Add the Debat object to the list of search results
                    searchResults.add(debat);
                }
            }
        }

        return searchResults;
    }
    @Override
    public void modifier(Debat debat) throws SQLException {
        String req = "UPDATE debat SET Sujet_Debat=? , Description_Debat=?, Nom_Anime=?   WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, debat.getSujet_Debat());
            pre.setString(2, debat.getDescription_Debat());
            pre.setString(3, debat.getNom_Anime());
            pre.setInt(4, debat.getID_Debat());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Debat debat) throws SQLException {
        String req = "DELETE FROM debat WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, debat.getID_Debat());
            pre.executeUpdate();
        }
    }

    @Override
    public List<Debat> afficher() throws SQLException {
        String req = "SELECT * FROM debat";
        List<Debat> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Debat d = new Debat();
                d.setID_Debat(res.getInt("ID_Debat"));
                d.setNom_Anime(res.getString("Nom_Anime"));
                d.setSujet_Debat(res.getString("Sujet_Debat"));
                d.setDescription_Debat(res.getString("Description_Debat"));
                list.add(d);
            }
        }
        return list;
    }


    public List<Debat> afficherByUser(String pseudo) throws SQLException {

        List<Debat> list = new ArrayList<>();
        String req = "SELECT * FROM debat where ID_User = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setString(1, pseudo);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                Debat d = new Debat();
                d.setID_Debat(res.getInt("ID_Debat"));
                d.setNom_Anime(res.getString("Nom_Anime"));
                d.setSujet_Debat(res.getString("Sujet_Debat"));
                d.setDescription_Debat(res.getString("Description_Debat"));
                list.add(d);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void supprimerParId(int idDebat) throws SQLException {
        String req = "DELETE FROM debat WHERE ID_Debat=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idDebat);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du débat : " + e.getMessage());
        }
    }

    public List<Debat> afficherParNom(String tri) throws SQLException {
        String req = "SELECT * FROM debat";
        List<Debat> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Debat d = new Debat();
                d.setID_Debat(res.getInt("ID_Debat"));
                d.setNom_Anime(res.getString("Nom_Anime"));
                d.setSujet_Debat(res.getString("Sujet_Debat"));
                d.setDescription_Debat(res.getString("Description_Debat"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        if (tri.equals("ASC")) {
            Collections.sort(list, Comparator.comparing(Debat::getNom_Anime));
        } else {
            Collections.sort(list, Comparator.comparing(Debat::getNom_Anime).reversed());
        }
        return list;
    }
    public List<Debat> afficherbyNOM(String tri) {
        String req = "SELECT * FROM debat";
        List<Debat> list = new ArrayList<>();
        try (Statement ste = connection.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Debat debat = new Debat();
                debat.setID_Debat(res.getInt(1));
                debat.setSujet_Debat(res.getString(2));
                debat.setDescription_Debat(res.getString(3));
                debat.setNom_Anime(res.getString(4));


                list.add(debat);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        if (tri.equals("ASC"))
        {
            Collections.sort(list, Comparator.comparing(Debat::getSujet_Debat));}
        else {
            Collections.sort(list, Comparator.comparing(Debat::getSujet_Debat).reversed());
        }
        return list;
    }

    public List<String> listdebat() {
        String req = "SELECT Sujet_Debat FROM debat";
        List<String> list = new ArrayList<>();
        try (Statement ste = connection.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                String nom = res.getString(1); // Use index 1 to get the value of the first (and only) column
                list.add(nom);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        return list;
    }
}
