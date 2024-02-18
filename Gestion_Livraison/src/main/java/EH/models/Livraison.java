package EH.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Livraison {
  private int ID_Livraison,NumTel;
  private String Adresse,Nom_Client;
  private LocalDateTime Date;
  private int quantity;
  private float montant;
  private int ID_Produit;

    public Livraison() {
    }

    public Livraison(int ID_Livraison, LocalDateTime Date, String Adresse,String Nom_Client, int NumTel ,int quantity,float montant,int ID_Produit) {
      this.ID_Livraison = ID_Livraison;
      this.Date = Date;
      this.Adresse = Adresse;
      this.Nom_Client = Nom_Client;
      this.NumTel = NumTel;
      this.quantity=quantity;
      this.montant = montant;
      this.ID_Produit = ID_Produit;


    }


  public int getID_Livraison() {
    return ID_Livraison;
  }

  public LocalDateTime getDate() {
    return Date;
  }

  public String getAdresse() {
    return Adresse;
  }
  public String getNom_Client() {
    return Nom_Client;
  }
  public int getNumTel() {
    return NumTel;
  }

  public int getQuantity() {
    return quantity;
  }

  public float getMontant() {
    return montant;
  }

  public int getID_Produit() {
    return ID_Produit;
  }

  public void setID_Livraison(int ID_Livraison) {
    this.ID_Livraison = ID_Livraison;
  }

  public void setDate(LocalDateTime Date) {
    this.Date = Date;
  }


  public void setAdresse(String adresse) {
    Adresse = adresse;
  }
  public void setNom_Client(String nom_Client) {
    Nom_Client = nom_Client;
  }
  public void setNumTel(int numTel) {
    NumTel = numTel;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setMontant(float montant) {
    this.montant = montant;
  }

  public void setID_Produit(int ID_Pannier) {
    this.ID_Produit = ID_Produit;
  }

  @Override
  public String toString() {
    return "Livraison{" +
            "ID_Livraison=" + ID_Livraison +
            ", NumTel=" + NumTel +
            ", Adresse='" + Adresse + '\'' +
            ", Nom_Client='" + Nom_Client + '\'' +
            ", Date=" + Date +
            ", quantity=" + quantity +
            ", montant=" + montant +
            ", ID_Produit=" + ID_Produit +
            '}';
  }
}

