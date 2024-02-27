package services;

import entities.Debat;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceDebat implements IService<Debat> {
    private Connection connection;
    private Debat debat;

    public ServiceDebat() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Debat debat) {
        String req = "INSERT INTO debat (Sujet_Debat,Description_Debat,Nom_Anime) VALUES (?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setString(1, debat.getSujet_Debat());
            stm.setString(2, debat.getDescription_Debat());
            stm.setString(3, debat.getNom_Anime());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier(Debat debat) throws SQLException {
        String req = "update debat set Nom_Anime=? , Description_Debat=? , Sujet_Debat=? where ID_Debat=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, debat.getNom_Anime());
        pre.setString(2, debat.getSujet_Debat());
        pre.setString(3, debat.getDescription_Debat());
        pre.setInt(4, debat.getID_Debat());

        pre.executeUpdate();

    }

    @Override
    public void supprimer(Debat debat) throws SQLException {

        String req = " delete from debat where ID_Debat=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, debat.getID_Debat());
        pre.executeUpdate();

    }

    @Override
    public List<Debat> afficher() throws SQLException {

        String req = "select * from debat";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Debat> list = new ArrayList<>();
        while (res.next()) {
            Debat d = new Debat();
            //Debat d = new Debat(ID_DEBAT.getText(), SUJET_DEBAT.getText(), DESCDEBAT.getText(), NOMANIME.getText());
            d.setID_Debat(res.getInt(1));
            d.setNom_Anime(res.getString("Nom_Anime"));
            d.setSujet_Debat(res.getString(3));
            d.setDescription_Debat(res.getString("Description_Debat"));

            list.add(d);
        }
        return list;
    }
    public void supprimerParId(int Id_Debat) throws SQLException {

        try {
            // Préparer la requête de suppression
            String req = "delete from debat where ID_Debat=?";
            PreparedStatement pre = connection.prepareStatement(req);

            pre.setInt(1, Id_Debat);
            pre.executeUpdate();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }
    }

}

