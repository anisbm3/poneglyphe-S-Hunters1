package eh.gestionlivraison.models;
import java.time.LocalDateTime;
public class Facture {
    private int idFacture;

    private int ID_Livraison;
    private String NomProduit;
    private Integer remise;
    private float montant;
    private LocalDateTime datefacture;


    public Facture() {
    }

    public Facture(int idFacture, int ID_Livraison, LocalDateTime datefacture, int remise, float montant) {
        this.idFacture = idFacture;
        this.ID_Livraison = ID_Livraison;
        this.datefacture = datefacture;
        this.remise = remise;
        this.montant = montant;
    }

    public Facture(int id, LocalDateTime localDateTime, int i) {
    }

    public Facture(String text, int i, float v, LocalDateTime localDateTime) {
    }

    public int getIdFacture() {
        return idFacture;
    }

    public int getID_Livraison() {
        return ID_Livraison;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public LocalDateTime getDatefacture() {
        return datefacture;
    }

    public Integer getRemise() {
        return remise;
    }

    public float getMontant() {
        return montant;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setID_Livraison(int ID_Livraison) {
        this.ID_Livraison = ID_Livraison;
    }

    public void setNomProduit(String nomProduit) {
        NomProduit = nomProduit;
    }

    public void setDatefacture(LocalDateTime datefacture) {
        this.datefacture = datefacture;
    }

    public void setRemise(Integer remise) {
        this.remise = remise;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", ID_Livraison=" + ID_Livraison +
                ", NomProduit='" + NomProduit + '\'' +
                ", remise=" + remise +
                ", montant=" + montant +
                ", datefacture=" + datefacture +
                '}';
    }
}
