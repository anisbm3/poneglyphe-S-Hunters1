package tn.esprit.models;


import java.sql.Date;

public class Cosplay {
    private  int idCp;
    private String nomCp;
     private String descriptionCp;
    private String personnage;
    private String imageCp;
    private Date dateCreation;
    private int idmateriaux;
    private String nomMa;


    public Cosplay() {
    }

    public Cosplay(int idCp, String nomCp, String descriptionCp, String personnage, String imageCp, Date dateCreation, int idmateriaux, String nomMa) {
        this.idCp = idCp;
        this.nomCp = nomCp;
        this.descriptionCp = descriptionCp;
        this.personnage = personnage;
        this.imageCp = imageCp;
        this.dateCreation = dateCreation;
        this.idmateriaux = idmateriaux;
        this.nomMa= nomMa;
    }

    public Cosplay(String nomCp, String descriptionCp, String personnage, String imageCp, Date dateCreation, int idmateriaux, String nomMa) {
        this.nomCp = nomCp;
        this.descriptionCp = descriptionCp;
        this.personnage = personnage;
        this.imageCp = imageCp;
        this.dateCreation = dateCreation;
        this.idmateriaux = idmateriaux;
        this.nomMa = nomMa;
    }

    public Cosplay(String nomCp, String descriptionCp, String personnage, String imageCp, Date dateCreation, String nomMa) {
        this.nomCp = nomCp;
        this.descriptionCp = descriptionCp;
        this.personnage = personnage;
        this.imageCp = imageCp;
        this.dateCreation = dateCreation;
        this.nomMa= nomMa;

    }

    public Cosplay(String nomCp, String descriptionCp, String personnage, String imageCp, Date dateCreation, int idmateriaux) {
        this.nomCp = nomCp;
        this.descriptionCp = descriptionCp;
        this.personnage = personnage;
        this.imageCp = imageCp;
        this.dateCreation = dateCreation;
        this.idmateriaux = idmateriaux;

    }

    public Cosplay(String nomCp, String descriptionCp, String personnage, String nomMa) {
        this.nomCp = nomCp;
        this.descriptionCp = descriptionCp;
        this.personnage = personnage;
        this.nomMa = nomMa;
    }

    public int getIdCp() {
        return idCp;
    }

    public void setIdCp(int idCp) {
        this.idCp = idCp;
    }

    public String getNomCp() {
        return nomCp;
    }

    public void setNomCp(String nomCp) {
        this.nomCp = nomCp;
    }

    public String getDescriptionCp() {
        return descriptionCp;
    }

    public void setDescriptionCp(String descriptionCp) {
        this.descriptionCp = descriptionCp;
    }

    public String getPersonnage() {
        return personnage;
    }

    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    public String getImageCp() {
        return imageCp;
    }

    public void setImageCp(String imageCp) {
        this.imageCp = imageCp;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdmateriaux() {
        return idmateriaux;
    }

    public void setIdmateriaux(int idmateriaux) {
        this.idmateriaux = idmateriaux;
    }

    public String getNomMa() {
        return nomMa;
    }

    public void setNomMa(String nomMa) {
        this.nomMa = nomMa;
    }

    @Override
    public String toString() {
        return "Cosplay{" +
                "idCp=" + idCp +
                ", nomCp='" + nomCp + '\'' +
                ", descriptionCp='" + descriptionCp + '\'' +
                ", personnage='" + personnage + '\'' +
                ", imageCp='" + imageCp + '\'' +
                ", dateCreation=" + dateCreation +
                ", idmateriaux=" + idmateriaux +
                ", nomMa='" + nomMa + '\'' +
                '}';
    }
}
