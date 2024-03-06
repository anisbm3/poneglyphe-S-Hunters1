package eh.gestionlivraison.models;

import java.sql.Date;
import java.util.Objects;

public class Facture {
    private int IdFacture;
    private int Remise;
    private float MontantAvecRemise;
    private Date dateFacture;
    private Date date;
    private int ID_Livraison;
    private String NomPrenomClient;
    private String Adresse;
    private int ID_Pannier;
    private int quantity;
    private float montant;
    private String Produits;
    private boolean selected;

    public Facture() {}

    public Facture(int idFacture, int remise, float montantAvecRemise, Date dateFacture, int ID_Livraison, String nomPrenomClient, String adresse, int ID_Pannier, int quantity, float montant, String produits) {
        IdFacture = idFacture;
        Remise = remise;
        MontantAvecRemise = montantAvecRemise;
        this.dateFacture = dateFacture;
        this.ID_Livraison = ID_Livraison;
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.ID_Pannier = ID_Pannier;
        this.quantity = quantity;
        this.montant = montant;
        Produits = produits;
    }

    public Facture(int remise, float montantAvecRemise, Date dateFacture, String nomPrenomClient, String adresse, int quantity, float montant, String produits) {
        Remise = remise;
        MontantAvecRemise = montantAvecRemise;
        this.dateFacture = dateFacture;
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.quantity = quantity;
        this.montant = montant;
        Produits = produits;
    }

    public Facture(int remise, float montantAvecRemise, Date dateFacture, Date date, String nomPrenomClient, String adresse, int quantity, float montant, String produits) {
        Remise = remise;
        MontantAvecRemise = montantAvecRemise;
        this.dateFacture = dateFacture;
        this.date = date;
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.quantity = quantity;
        this.montant = montant;
        Produits = produits;
    }

    public Facture(int idFacture, int remise, float montantAvecRemise, Date dateFacture, Date date, int ID_Livraison, String nomPrenomClient, String adresse, int ID_Pannier, int quantity, float montant, String produits) {

       this.IdFacture = idFacture;
      this.Remise = remise;
       this.MontantAvecRemise = montantAvecRemise;
        this.dateFacture = dateFacture;
        this.date = date;
        this.ID_Livraison = ID_Livraison;
        this.NomPrenomClient = nomPrenomClient;
        this.Adresse = adresse;
        this.ID_Pannier = ID_Pannier;
        this.quantity = quantity;
        this.montant = montant;
        this.Produits = produits;
    }

    public Facture(int remise, float montantAvecRemise, Date dateFacture, String nomPrenomClient, String adresse, String produits, Integer quantity, Float montant, Date date) {
        this.Remise = remise;
        this.MontantAvecRemise = montantAvecRemise;
        this.dateFacture = dateFacture;
        this.NomPrenomClient = nomPrenomClient;
        this.Adresse = adresse;
        this.Produits = produits;
        this.quantity = quantity;
        this.montant = montant;
        this.date = date;
    }

    public int getIdFacture() {
        return IdFacture;
    }

    public int getRemise() {
        return Remise;
    }

    public float getMontantAvecRemise() {
        return MontantAvecRemise;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public Date getDate() {
        return date;
    }

    public int getID_Livraison() {
        return ID_Livraison;
    }

    public String getNomPrenomClient() {
        return NomPrenomClient;
    }

    public String getAdresse() {
        return Adresse;
    }

    public int getID_Pannier() {
        return ID_Pannier;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getMontant() {
        return montant;
    }

    public String getProduits() {
        return Produits;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setIdFacture(int idFacture) {
        IdFacture = idFacture;
    }

    public void setRemise(int remise) {
        Remise = remise;
    }

    public void setMontantAvecRemise(float montantAvecRemise) {
        MontantAvecRemise = montantAvecRemise;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setID_Livraison(int ID_Livraison) {
        this.ID_Livraison = ID_Livraison;
    }

    public void setNomPrenomClient(String nomPrenomClient) {
        NomPrenomClient = nomPrenomClient;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public void setID_Pannier(int ID_Pannier) {
        this.ID_Pannier = ID_Pannier;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setProduits(String produits) {
        Produits = produits;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "IdFacture=" + IdFacture +
                ", Remise=" + Remise +
                ", MontantAvecRemise=" + MontantAvecRemise +
                ", dateFacture=" + dateFacture +
                ", date=" + date +
                ", ID_Livraison=" + ID_Livraison +
                ", NomPrenomClient='" + NomPrenomClient + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", ID_Pannier=" + ID_Pannier +
                ", quantity=" + quantity +
                ", montant=" + montant +
                ", Produits='" + Produits + '\'' +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return IdFacture == facture.IdFacture && Remise == facture.Remise && Float.compare(MontantAvecRemise, facture.MontantAvecRemise) == 0 && ID_Livraison == facture.ID_Livraison && ID_Pannier == facture.ID_Pannier && quantity == facture.quantity && Float.compare(montant, facture.montant) == 0 && selected == facture.selected && Objects.equals(dateFacture, facture.dateFacture) && Objects.equals(date, facture.date) && Objects.equals(NomPrenomClient, facture.NomPrenomClient) && Objects.equals(Adresse, facture.Adresse) && Objects.equals(Produits, facture.Produits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdFacture, Remise, MontantAvecRemise, dateFacture, date, ID_Livraison, NomPrenomClient, Adresse, ID_Pannier, quantity, montant, Produits, selected);
    }

    public void setDate(java.util.Date value) {
    }
}

