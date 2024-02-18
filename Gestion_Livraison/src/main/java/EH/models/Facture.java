package EH.models;

import java.time.LocalDateTime;
public class Facture {
    private int ID_Facture;
    private String NomPrenom,ville,montant_a_paye,etat;
    private LocalDateTime date_versement;

    public Facture() {
    }

    public Facture(int ID_Facture, String NomPrenom, String ville, String montant_a_paye, LocalDateTime date_versement, String etat) {
        this.ID_Facture = ID_Facture;
        this.NomPrenom = NomPrenom ;
        this.ville = ville;
        this.montant_a_paye = montant_a_paye;
        this.date_versement = date_versement;
        this.etat = etat;

    }

    public int getID_Facture() {
        return ID_Facture;
    }

    public String getNomPrenom() {
        return NomPrenom;
    }

    public String getVille() {
        return ville;
    }

    public String getMontant_a_paye() {
        return montant_a_paye;
    }


    public LocalDateTime getDate_versement() {
        return date_versement;
    }
    public String getEtat() {
        return etat;
    }

    public void setID_Facture(int ID_Facture) {
        this.ID_Facture = ID_Facture;
    }

    public void setNomPrenom(String nomPrenom) {
        NomPrenom = nomPrenom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setMontant_a_paye(String montant_a_paye) {
        this.montant_a_paye = montant_a_paye;
    }
    public void setDate_versement(LocalDateTime date_versement) {
        this.date_versement = date_versement;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "ID_Facture=" + ID_Facture +
                ", NomPrenom='" + NomPrenom + '\'' +
                ", ville='" + ville + '\'' +
                ", montant_a_paye='" + montant_a_paye + '\'' +
                ", etat='" + etat + '\'' +
                ", date_versement=" + date_versement +
                '}';
    }
}
