package services;

import entities.Debat;
import utils.MyDB;
import entities.Commentaire;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommentaire implements IService<Commentaire> {
    private Connection connection;

    public ServiceCommentaire(){
        connection= MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(Commentaire commentaire) throws SQLException {
        String req ="insert into commentaire (Message,BLOCK)"+
                "values('"+commentaire.getMessage()+"','"+commentaire.getBLOCK()+")";
        Statement ste= connection.createStatement();

        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String req = "update commentaire set Message=? , BLOCK=?  where ID_Commentaire=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1,commentaire.getMessage());
        pre.setInt(2,commentaire.getBLOCK());
        pre.setInt(3,commentaire.getID_User());
        pre.setInt(4,commentaire.getID_Debat());
        pre.setInt(4,commentaire.getID_Commentaire());

        pre.executeUpdate();

    }

    @Override
    public void supprimer(Commentaire commentaire) throws SQLException {

        String req = " delete from commentaire where ID_Commentaire=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,commentaire.getID_Commentaire());
        pre.executeUpdate();

    }

    @Override
    public List<Commentaire> afficher() throws SQLException {

        String req = "select * from commentaire";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Commentaire> list =new ArrayList<>();
        while (res.next()){
            Commentaire c = new Commentaire();
            c.setID_Commentaire(res.getInt(1));
            c.setMessage(res.getString("Message"));
            c.setID_User(res.getInt(3));
            c.setID_Debat(res.getInt("ID_Debat"));
            c.setBLOCK(res.getInt("BLOCK"));

            list.add(c);
        }
        return list;
    }
}
