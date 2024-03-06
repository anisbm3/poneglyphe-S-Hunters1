package eh.gestionlivraison.models;

import java.sql.Date;
import java.util.Objects;

public class Livraison {
    private int ID_Livraison;
    private String NomPrenomClient;
    private String Adresse;

    private float montant;
    private int panier_id;

    private Date date;
    private boolean selected;

    public Livraison(String text, String text1, String value, int montant, float v, Date date) {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Livraison() {
    }

    public Livraison(String nomPrenomClient, String adresse, float montant, Date date, int panier_id) {
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.panier_id = panier_id;
        this.montant = montant;
        this.date = date;

    }

    public Livraison(int ID_Livraison, String nomPrenomClient, String adresse, float montant, Date date, int panier_id) {
        this.ID_Livraison = ID_Livraison;
        NomPrenomClient = nomPrenomClient;
        Adresse = adresse;
        this.panier_id = panier_id;
        // this.quantity = quantity;
        this.montant = montant;
        this.date = date;
        //this.prod_name = prod_name;
    }

    /*  public Livraison(String nomPrenomClient, String adresse, int panier_id, int quantity, float montant, Date date, String prod_name) {
          NomPrenomClient = nomPrenomClient;
          Adresse = adresse;
          this.panier_id = panier_id;
          this.quantity = quantity;
          this.montant = montant;
          this.date = date;
          this.prod_name = prod_name;
      }
  */
    public int getID_Livraison() {
        return ID_Livraison;
    }

    public String getNomPrenomClient() {
        return NomPrenomClient;
    }

    public String getAdresse() {
        return Adresse;
    }


    public  int getPanier_id() {
        return panier_id;
    }


    public float getMontant() {
        return montant;
    }


    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
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
                ", montant=" + montant +
                ", panier_id=" + panier_id +
                ", date=" + date +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return ID_Livraison == livraison.ID_Livraison && Float.compare(montant, livraison.montant) == 0 && panier_id == livraison.panier_id && selected == livraison.selected && Objects.equals(NomPrenomClient, livraison.NomPrenomClient) && Objects.equals(Adresse, livraison.Adresse) && Objects.equals(date, livraison.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_Livraison, NomPrenomClient, Adresse, montant, panier_id, date, selected);
    }
}

