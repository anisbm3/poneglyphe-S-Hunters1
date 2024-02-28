package tn.esprit.models;

import java.time.LocalDateTime;

public class Reservation {

    public int ID_Reservation,NB_Places;
    public String Nom_Reseervation,Etat,NOM_Event;
    public LocalDateTime resDate;

    public Reservation(){

    }

    public Reservation(int ID_Reservation, String Nom_Reseervation,int NB_Places,String Etat,String NOM_Event){
        this.ID_Reservation=ID_Reservation;
        this.Nom_Reseervation=Nom_Reseervation;
        this.NB_Places=NB_Places;
        this.Etat=Etat;
        this.NOM_Event=NOM_Event;
        // this.ID_User=ID_User;
    }

    public Reservation(String Nom_Reseervation,int NB_Places,String Etat,String NOM_Event){
        this.Nom_Reseervation=Nom_Reseervation;
        this.NB_Places=NB_Places;
        this.Etat=Etat;
        this.NOM_Event=NOM_Event;
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

    public String getNOM_Event() {
        return NOM_Event;
    }

    public void setNOM_Event(String NOM_Event) {
        this.NOM_Event = NOM_Event;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "ID_Reservation=" + ID_Reservation +
                ", NB_Places=" + NB_Places +
                ", Nom_Reseervation='" + Nom_Reseervation + '\'' +
                ", Etat='" + Etat + '\'' +
                ", NOM_Event='" + NOM_Event + '\'' +
                ", resDate=" + resDate +
                '}';
    }

    public LocalDateTime getResDate() {
        return resDate;
    }

    public void setResDate(LocalDateTime resDate) {
        this.resDate = resDate;
    }


/*  public void setID_User(int ID_User) {
    }
    public int getId_USER( ){return ID_User;}*/
}

