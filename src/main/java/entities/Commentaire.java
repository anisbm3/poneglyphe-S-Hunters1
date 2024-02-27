package entities;

public class Commentaire {

    int ID_Commentaire  ;
    String Message;
    int ID_User ;

    int ID_Debat ;
    int BLOCK ;

    public int getID_Commentaire() {
        return ID_Commentaire;
    }

    public void setID_Commentaire(int ID_Commentaire) {
        this.ID_Commentaire = ID_Commentaire;
    }



    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public int getID_Debat() {
        return ID_Debat;
    }

    public void setID_Debat(int ID_Debat) {
        this.ID_Debat = ID_Debat;
    }

    public int getBLOCK() {
        return BLOCK;
    }

    public void setBLOCK(int BLOCK) {
        this.BLOCK = BLOCK;
    }

    @Override
    public String toString() {
        return "Debat{" +
                ", ID_Commentaire=" + ID_Commentaire +
                ", Message='" + Message + '\'' +
                ", ID_User='" + ID_User + '\'' +
                "ID_Debat=" + ID_Debat +
                ", BLOCK='" + BLOCK + '\'' +
                '}';
    }

    public Commentaire() {
    }

    public Commentaire(int ID_Commentaire,String Message, int ID_User,int ID_Debat,int BLOCK) {
        this.ID_Commentaire = ID_Commentaire;
        this.Message = Message;
        this.ID_User = ID_User;
        this.ID_Debat = ID_Debat;
        this.BLOCK=BLOCK;
    }

    public Commentaire(String Message, int ID_User,int ID_Debat,int BLOCK) {
        this.Message = Message;
        this.ID_User = ID_User;
        this.ID_Debat = ID_Debat;
        this.BLOCK=BLOCK;
    }
}


