package service;
import util.datasource;

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
        ps.setString(5, user.getAge());
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
        ps.setString(4, user.getAge());
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
            u.setAge(rs.getString("AGE"));
            u.setNumTel(rs.getInt("NUMTEL"));
            u.setEmail(rs.getString("EMAIL"));
            u.setMdp(rs.getString("MDP"));
            u.setRole(rs.getString("ROLE"));
            utilisateurs.add(u);
        }
        return utilisateurs;
    }
    }

