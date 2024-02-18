package EH.models;

import java.time.LocalDateTime;
public class Facture {
    private int idFacture;
    private int ID_Livraison;
    private LocalDateTime datefacture;
    private int remise;
    private float montant;

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getID_Livraison() {
        return ID_Livraison;
    }

    public void setID_Pannier(int ID_Pannier) {
        this.ID_Livraison = ID_Livraison;
    }

    public LocalDateTime getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(LocalDateTime datefacture) {
        this.datefacture = datefacture;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Facture(int ID_Pannier, LocalDateTime datefacture, int remise, float montant) {
        this.ID_Livraison = ID_Livraison;
        this.datefacture = datefacture;
        this.remise = remise;
        this.montant = montant;
    }

    public Facture(int ID_Pannier, LocalDateTime datefacture, int remise) {
        this.ID_Livraison = ID_Livraison;
        this.datefacture = datefacture;
        this.remise = remise;
    }

    public Facture() {
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", ID_Livraison=" + ID_Livraison +
                ", datefacture=" + datefacture +
                ", remise=" + remise +
                ", montant=" + montant +
                '}';
    }

    public void setID_Livraison(int idLivraison) {
    }
}