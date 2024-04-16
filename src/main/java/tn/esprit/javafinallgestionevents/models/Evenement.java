package tn.esprit.javafinallgestionevents.models;

import java.time.LocalDateTime;

public class Evenement {
    private int id;
    private String nom_event;
    private String description_event;
    private String lieu_event;
    private LocalDateTime date_event;
    private String image;

    public Evenement() {
    }


  public  Evenement(int id,String nom_event, String description_event, String lieu_event, LocalDateTime date_event,String image){
        this.id=id;
        this.nom_event=nom_event;
        this.description_event=description_event;
        this.lieu_event=lieu_event;
        this.date_event=date_event;
        this.image=image;

    }

  public  Evenement(String nom_event, String description_event, String lieu_event, LocalDateTime date_event,String image){
        this.nom_event=nom_event;
        this.description_event=description_event;
        this.lieu_event=lieu_event;
        this.date_event=date_event;
        this.image=image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public LocalDateTime getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDateTime date_event) {
        this.date_event = date_event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom_event='" + nom_event + '\'' +
                ", description_event='" + description_event + '\'' +
                ", lieu_event='" + lieu_event + '\'' +
                ", date_event=" + date_event +
                ", image='" + image + '\'' +
                '}';
    }
}
