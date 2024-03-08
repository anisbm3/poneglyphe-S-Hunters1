package entities;

public class Debat {

    int ID_Debat  ;
    String Nom_Anime;
    String Description_Debat;
    String Sujet_Debat;

    /*public int getNote_Anime() {
        return Note_Anime;
    }

    public void setNote_Anime(int Note_Anime) {
        this.Note_Anime = Note_Anime;
    }*/

    public int getID_Debat() {
        return ID_Debat;
    }

    public void setID_Debat(int ID_Debat) {
        this.ID_Debat = ID_Debat;
    }

    public String getNom_Anime() {
        return Nom_Anime;
    }

    public void setNom_Anime(String Nom_Anime) {
        this.Nom_Anime = Nom_Anime;
    }

    public String getDescription_Debat() {
        return Description_Debat;
    }

    public void setDescription_Debat(String Description_Debat) {
        this.Description_Debat = Description_Debat;
    }

    public String getSujet_Debat() {
        return Sujet_Debat;
    }

    public void setSujet_Debat(String Sujet_Debat) {
        this.Sujet_Debat = Sujet_Debat;
    }


    @Override
    public String toString() {
        return "Debat{" +
                "ID_Debat=" + ID_Debat +
                ", Nom_Anime=" + Nom_Anime +
                ", Description_Debat='" + Description_Debat + '\'' +
                ", Sujet_Debat='" + Sujet_Debat + '\'' +
                '}';
    }

    public Debat(int ID_Debat, String Description_Debat, String Sujet_Debat) {
        this.ID_Debat = ID_Debat;
        //this.Nom_Anime = Nom_Anime;
        this.Description_Debat = Description_Debat;
        this.Sujet_Debat = Sujet_Debat;
    }

    public Debat() {
        this.ID_Debat = ID_Debat;
        this.Nom_Anime = Nom_Anime;
        this.Description_Debat = Description_Debat;
        this.Sujet_Debat = Sujet_Debat;
    }

    public Debat(String Nom_Anime, String Description_Debat, String Sujet_Debat) {
        this.Nom_Anime = Nom_Anime;
        this.Description_Debat = Description_Debat;
        this.Sujet_Debat = Sujet_Debat;
    }


}
