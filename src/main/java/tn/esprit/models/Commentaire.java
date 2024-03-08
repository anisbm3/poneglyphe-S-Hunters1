package tn.esprit.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Commentaire {

    int ID_Commentaire  ;
    String Message;

    public void setID_User(String ID_User) {
        this.ID_User = ID_User;
    }

    public String getID_User() {
        return ID_User;
    }

    //int ID_User=1; ;
    String ID_User;
    int Rating;

    String Sujet_Debat ;
    String BLOCK="1" ;

    private Connection connection;


    public int getID_Commentaire() {
        return ID_Commentaire;
    }

    public void setID_Commentaire(int ID_Commentaire) {
        this.ID_Commentaire = ID_Commentaire;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }



    public String getSujet_Debat() {
        return Sujet_Debat;
    }

    public void setSujet_Debat(String Sujet_Debat) {
        this.Sujet_Debat = Sujet_Debat;
    }

    public String getBLOCK() {
        return BLOCK;
    }

    public void setBLOCK(String BLOCK) {
        this.BLOCK = BLOCK;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                ", ID_Commentaire=" + ID_Commentaire +
                "Sujet_Debat=" + Sujet_Debat +
                ", Message='" + Message + '\'' +
               ", ID_User='" + ID_User + '\'' +
                ", BLOCK='" + BLOCK + '\'' +
                ", Rating='" + Rating + '\'' +
                '}';
    }

    public Commentaire() {
    }

    public Commentaire(int ID_Commentaire,String Sujet_Debat ,String Message) {
        this.ID_Commentaire = ID_Commentaire;
        this.Sujet_Debat = Sujet_Debat;
        this.Message = Message;
    }



    public Commentaire(String Sujet_Debat ,String Message,String ID_User) {

        this.Sujet_Debat = Sujet_Debat;
        this.Message = Message;
       this.ID_User=ID_User;
    }

    public Commentaire(String Message,String Sujet_debat) {
        this.Message = Message;
        this.Sujet_Debat= Sujet_debat;
    }

    public Commentaire(String Message,String Sujet_debat,int Rating) {
        this.Message = Message;
        this.Sujet_Debat= Sujet_debat;
        this.Rating=Rating;

    }

    public void updateRating(Commentaire commentaire) throws SQLException {
        String req = "UPDATE commentaire SET Rating=? WHERE ID_Commentaire=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, commentaire.getRating());
            pre.setInt(2, commentaire.getID_Commentaire());

            pre.executeUpdate();
        }
    }
}


