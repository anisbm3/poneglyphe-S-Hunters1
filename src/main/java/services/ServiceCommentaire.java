package services;
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
    public void ajouter(Commentaire commentaire)  {
        String req ="INSERT INTO commentaire (Message,ID_User,ID_Debat,BLOCK) VALUES (?,?,?,?)";
        try{
            PreparedStatement stm =connection.prepareStatement(req);
            stm.setString(1,commentaire.getMessage());
            stm.setInt(2,commentaire.getID_User());
            stm.setInt(3,commentaire.getID_Debat());
            stm.setInt(4,commentaire.getBLOCK());
            stm.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String req = "update commentaire set Message=? ,ID_User=?, ID_Debat=?, BLOCK=? where ID_Commentaire=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1,commentaire.getMessage());
        pre.setInt(2,commentaire.getID_User());
        pre.setInt(3,commentaire.getID_Debat());
        pre.setInt(4,commentaire.getBLOCK());
        pre.setInt(5, commentaire.getID_Commentaire());

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

       /* String req = "SELECT debat.*, debat.ID_Debat" +
                "FROM debat " + "INNER JOIN commentaire ON commentaire.ID_Debat = debat.ID_Debat";*/
        String req = "SELECT commentaire.*, debat.ID_Debat " +
                "FROM commentaire " +
                "INNER JOIN debat ON commentaire.ID_Debat = debat.ID_Debat";
        //String req = "select * from commentaire";
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
