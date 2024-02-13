package entities;

public class Commentaire {

    int ID_Commentaire  ;
    int ID_User ;
    String Message;
    int ID_Debat ;
    int BLOCK ;


    public int getID_Debat() {
        return ID_Debat;
    }

    public void setID_Debat(int ID_Debat) {
        this.ID_Debat = ID_Debat;
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

    public int getID_Commentaire() {
        return ID_Commentaire;
    }

    public void setID_Commentaire(int ID_Commentaire) {
        this.ID_Commentaire = ID_Commentaire;
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
                "ID_Debat=" + ID_Debat +
                ", ID_Commentaire=" + ID_Commentaire +
                ", ID_User='" + ID_User + '\'' +
                ", Message='" + Message + '\'' +
                ", BLOCK='" + BLOCK + '\'' +
                '}';
    }

    public Commentaire() {
    }

    public Commentaire(int ID_Debat, int ID_Commentaire, int ID_User, String Message ,int BLOCK) {
        this.ID_Debat = ID_Debat;
        this.ID_Commentaire = ID_Commentaire;
        this.ID_User = ID_User;
        this.Message = Message;
        this.BLOCK=BLOCK;
    }

    public Commentaire(String Message,int BLOCK) {
        this.Message = Message;
        this.BLOCK = BLOCK;
    }
}


