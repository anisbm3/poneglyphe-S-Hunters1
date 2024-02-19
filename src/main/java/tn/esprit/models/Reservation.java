package tn.esprit.models;

public class Reservation {

    public int ID_Reservation,NB_Places,ID_Event;
    public String Nom_Reseervation,Etat;

    public Reservation(){

    }

    public Reservation(int ID_Reservation, String Nom_Reseervation,int NB_Places,String Etat,int ID_Event){
        this.ID_Reservation=ID_Reservation;
        this.Nom_Reseervation=Nom_Reseervation;
        this.NB_Places=NB_Places;
        this.Etat=Etat;
        this.ID_Event=ID_Event;
        // this.ID_User=ID_User;
    }

    public int getID_Reservation() {
        return ID_Reservation;
    }

    public void setID_Reservation(int ID_Reservation) {
        this.ID_Reservation = ID_Reservation;
    }

    public String getNom_Reseervation(){
        return Nom_Reseervation;
    }
    public void setNom_Reseervation(String Nom_Reseervation){
        this.Nom_Reseervation = Nom_Reseervation;
    }

    public int getNB_Places() {
        return NB_Places;
    }

    public void setNB_Places(int NB_Places) {
        this.NB_Places = NB_Places;
    }
    public String getEtat(){return Etat;}

    public void setEtat(String Etat){
        this.Etat=Etat;
    }

    public int getID_Event() {
        return ID_Event;
    }

    public void setID_Event(int ID_Event) {
        this.ID_Event = ID_Event;
    }

    @Override
    public String toString(){
        return "Reservation{" +
                "ID_Reservation=" + ID_Reservation +
                " + Nom_Reseervation= " + Nom_Reseervation +
                " + NB_Places= " + NB_Places + '\''+
                " + Etat= " + Etat + '\'' +
                " + ID_Event= " + ID_Event + '\'' +
                "}\n";

    }


  /*  public void setID_User(int ID_User) {
    }
    public int getId_USER( ){return ID_User;}*/
}

