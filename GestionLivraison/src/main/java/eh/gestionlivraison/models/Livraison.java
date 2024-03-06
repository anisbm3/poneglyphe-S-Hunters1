package eh.gestionlivraison.models;

import java.sql.Date;
import java.util.Objects;

public class Livraison {
    private int ID_Livraison;
    private String NomPrenomClient;
    private String Adresse;
    private static int ID_Pannier;
    private int quantity;
    private float montant;

    private Date date;
    private String Produits ;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Livraison() {
    }


    public Livraison(String nomPrenomClient, String adresse, String produits, int quantity, float montant, Date date) {
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.quantity = quantity;
        this.montant = montant;
        this.date = date;
        Produits = produits;
    }

    public Livraison(int ID_Livraison, String nomPrenomClient, String adresse, int ID_Pannier, int quantity, float montant, Date date, String produits) {
        this.ID_Livraison = ID_Livraison;
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.ID_Pannier = ID_Pannier;
        this.quantity = quantity;
        this.montant = montant;
        this.date = date;
        Produits = produits;
    }

    public Livraison(String nomPrenomClient, String adresse, int ID_Pannier, int quantity, float montant, Date date, String produits) {
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.ID_Pannier = ID_Pannier;
        this.quantity = quantity;
        this.montant = montant;
        this.date = date;
        Produits = produits;
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

    public static int getID_Pannier() {
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

    public void setProduits(String produits) {
        Produits = produits;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "ID_Livraison=" + ID_Livraison +
                ", NomPrenomClient='" + NomPrenomClient + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", ID_Pannier=" + ID_Pannier +
                ", quantity=" + quantity +
                ", montant=" + montant +
                ", date=" + date +
                ", Produits='" + Produits + '\'' +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return ID_Livraison == livraison.ID_Livraison && ID_Pannier == livraison.ID_Pannier && quantity == livraison.quantity && Float.compare(montant, livraison.montant) == 0 && selected == livraison.selected && Objects.equals(NomPrenomClient, livraison.NomPrenomClient) && Objects.equals(Adresse, livraison.Adresse) && Objects.equals(date, livraison.date) && Objects.equals(Produits, livraison.Produits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_Livraison, NomPrenomClient, Adresse, ID_Pannier, quantity, montant, date, Produits, selected);
    }
}

