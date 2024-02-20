package EH.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Livraison {
  private int ID_Livraison;
  private static Integer ID_Produit;

  private static Integer ID_Client;
  private LocalDateTime Date;
  private int quantity;
  private float montant;


  public Livraison() {
  }

  public Livraison(int ID_Livraison, Integer ID_Produit, Integer ID_Client,int quantity, float montant, LocalDateTime Date) {
    this.ID_Livraison = ID_Livraison;
    this.ID_Produit = ID_Produit;
    this.ID_Client = ID_Client;
    this.montant = montant;
    this.quantity = quantity;
    this.Date = Date;

  }

  public Livraison(Integer ID_Produit, Integer ID_Client, int quantity, float montant, LocalDateTime localDateTime) {
  }

  public int getID_Livraison() {
    return ID_Livraison;
  }

  public static Integer getID_Produit() {
    return ID_Produit;
  }

  public static Integer getID_Client() {
    return ID_Client;
  }

  public LocalDateTime getDate() {
    return Date;
  }

  public int getQuantity() {
    return quantity;
  }

  public float getMontant() {
    return montant;
  }

  public void setID_Livraison(int ID_Livraison) {
    this.ID_Livraison = ID_Livraison;
  }

  public static void setID_Produit(Integer ID_Produit) {
    Livraison.ID_Produit = ID_Produit;
  }

  public void setID_Client(Integer ID_Client) {
    this.ID_Client = ID_Client;
  }

  public void setDate(LocalDateTime Date) {
    Date = Date;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setMontant(float montant) {
    this.montant = montant;
  }

  @Override
  public String toString() {
    return "Livraison{" +
            "ID_Livraison=" + ID_Livraison +
            ", ID_Client=" + ID_Client +
            ", Date=" + Date +
            ", quantity=" + quantity +
            ", montant=" + montant +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Livraison livraison = (Livraison) o;
    return ID_Client == livraison.ID_Client && quantity == livraison.quantity && Float.compare(montant, livraison.montant) == 0 && Objects.equals(ID_Livraison, livraison.ID_Livraison) && Objects.equals(Date, livraison.Date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID_Livraison, ID_Client, Date, quantity, montant);
  }
}