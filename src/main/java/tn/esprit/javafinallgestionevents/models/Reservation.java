package tn.esprit.javafinallgestionevents.models;

import java.time.LocalDateTime;

public class Reservation {
private  int id;
private  String nom_reservation;
private int nb_place;
private String etat;
private LocalDateTime resdate;

private String user_id;
private String evenement_id;

public Reservation(){

}


public Reservation(int id, String nom_reservation, int nb_place, String etat, LocalDateTime resdate,String evenement_id,String user_id){
    this.id = id;
    this.nom_reservation=nom_reservation;
    this.nb_place=nb_place;
    this.etat=etat;
    this.resdate=resdate;
    this.user_id=user_id;
    this.evenement_id=evenement_id;

}

    public Reservation(String nom_reservation, int nb_place, String etat, LocalDateTime resdate,String evenement_id,String user_id){
        this.nom_reservation=nom_reservation;
        this.nb_place=nb_place;
        this.etat=etat;
        this.resdate=resdate;
        this.user_id=user_id;
        this.evenement_id=evenement_id;

    }
    public Reservation(int id,String nom_reservation, int nb_place, String etat,String evenement_id,String user_id){
    this.id=id;
        this.nom_reservation=nom_reservation;
        this.nb_place=nb_place;
        this.etat=etat;
        this.user_id=user_id;
        this.evenement_id=evenement_id;

    }
    public Reservation(String nom_reservation, int nb_place, String etat,String evenement_id,String user_id){
        this.nom_reservation=nom_reservation;
        this.nb_place=nb_place;
        this.etat=etat;
        this.user_id=user_id;
        this.evenement_id=evenement_id;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_reservation() {
        return nom_reservation;
    }

    public void setNom_reservation(String nom_reservation) {
        this.nom_reservation = nom_reservation;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getResdate() {
        return resdate;
    }

    public void setResdate(LocalDateTime resdate) {
        this.resdate = resdate;
    }

    public String getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(String evenement_id) {
        this.evenement_id = evenement_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", nom_reservation='" + nom_reservation + '\'' +
                ", nb_place=" + nb_place +
                ", etat='" + etat + '\'' +
                ", resdate=" + resdate +
                ", user_id=" + user_id +
                ", evenement_id='" + evenement_id + '\'' +
                '}';
    }
}
