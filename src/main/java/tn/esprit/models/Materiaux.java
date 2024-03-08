package tn.esprit.models;

public class Materiaux {
private int idMa;
private String nomMa;
private String typeMa;
private String disponibilite;

    public Materiaux() {
    }

    public Materiaux(int idMa, String nomMa, String typeMa, String disponibilite) {
        this.idMa = idMa;
        this.nomMa = nomMa;
        this.typeMa = typeMa;
        this.disponibilite = disponibilite;
    }

    public Materiaux(String nomMa, String typeMa, String disponibilite) {
        this.nomMa = nomMa;
        this.typeMa = typeMa;
        this.disponibilite = disponibilite;
    }

    public int getIdMa() {
        return idMa;
    }

    public void setIdMa(int idMa) {
        this.idMa = idMa;
    }

    public String getNomMa() {
        return nomMa;
    }

    public void setNomMa(String nomMa) {
        this.nomMa = nomMa;
    }

    public String getTypeMa() {
        return typeMa;
    }

    public void setTypeMa(String typeMa) {
        this.typeMa = typeMa;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Materiaux{" +
                "idMa=" + idMa +
                ", nomMa='" + nomMa + '\'' +
                ", typeMa='" + typeMa + '\'' +
                ", Disponibilite='" + disponibilite + '\'' +
                '}';
    }
}
