package service;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.datasource;
import controller.login_controller;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.*;

public class personneService implements IService<user>{
    private Connection cnx;
    private Statement statement;
    private PreparedStatement ste;

    public personneService() {
        this.cnx = datasource.getInstance().getCnx();
    }


    @Override
    public void add(user user) throws SQLException {
        String req = "INSERT INTO `user`(`pseudo`, `cin`, `nom`, `prenom`, `age`, `numTel`, `email`, `mdp`, `role`) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, user.getPseudo());
        ps.setInt(2, user.getCin());
        ps.setString(3, user.getNom());
        ps.setString(4, user.getPrenom());
        ps.setInt(5, user.getAge());
        ps.setInt(6, user.getNumTel());
        ps.setString(7, user.getEmail());
        ps.setString(8, user.getMdp());
        ps.setString(9, user.getRole());
        ps.executeUpdate();
        System.out.println("Personne Ajoutée");
    }
    @Override
    public void delete(user user) throws SQLException {
        String req = "DELETE FROM `user` WHERE `pseudo`=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, user.getPseudo());
        ps.executeUpdate();
        System.out.println("Personne Supprimée");
    }




    @Override
    public void update(user user) throws SQLException {
        String req="UPDATE `user` SET `cin`=?,`nom`=?,`prenom`=?,`age`=?,`numTel`=?,`email`=?,`mdp`=? WHERE `pseudo`=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setInt(1, user.getCin());
        ps.setString(2, user.getNom());
        ps.setString(3, user.getPrenom());
        ps.setInt(4, user.getAge());
        ps.setInt(5, user.getNumTel());
        ps.setString(6, user.getEmail());
        ps.setString(7, user.getMdp());
        ps.setString(8, user.getPseudo());
        ps.executeUpdate();
        System.out.println("Personne modifie");

    }

    @Override
    public List<user> Readall()  throws SQLException {
        List<user> utilisateurs= new ArrayList<>();
        String req="SELECT * FROM `user`";
        Statement st  = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            user u = new user();
            u.setPseudo(rs.getString("PSEUDO"));
            u.setCin(rs.getInt("CIN"));
            u.setNom(rs.getString("NOM"));
            u.setPrenom(rs.getString("PRENOM"));
            u.setAge(rs.getInt("AGE"));
            u.setNumTel(rs.getInt("NUMTEL"));
            u.setEmail(rs.getString("EMAIL"));
            u.setMdp(rs.getString("MDP"));
            u.setRole(rs.getString("ROLE"));
            utilisateurs.add(u);
        }
        return utilisateurs;
    }

    public boolean utilisateurLoggedIn(String pseudo, String mdp) throws SQLException {
        String req = "SELECT * FROM `user` WHERE pseudo=? AND mdp=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ps.setString(2, mdp);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public user afficherParPseudo(String pseudo) throws SQLException {
        user u = null; // Initialisez à null en dehors de la boucle
        String req = "SELECT * FROM `user` WHERE pseudo=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ResultSet rs = ps.executeQuery(); // Remove req from executeQuery
        while (rs.next()){
            u = new user(rs.getString("PSEUDO"), rs.getInt("CIN"), rs.getString("NOM"), rs.getString("PRENOM"), rs.getInt("AGE"), rs.getInt("NUMTEL"), rs.getString("EMAIL"), rs.getString("MDP"), rs.getString("ROLE"));
        }
        return u;
    }
    public boolean pseudoExiste(String pseudo) throws SQLException {
        String req = "SELECT * FROM `user` WHERE pseudo=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void changeScreen(ActionEvent event, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(login_controller.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
