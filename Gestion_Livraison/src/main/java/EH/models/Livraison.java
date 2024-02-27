package EH.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Livraison {
  private int ID_Livraison;
  private static Integer ID_Pannier;
  private static Integer ID_Client;
  private LocalDateTime Date;
  private int quantity;
  private float montant;

  public Livraison() {
  }

  public Livraison(int ID_Livraison, Integer ID_Pannier, Integer ID_Client, int quantity, float montant, LocalDateTime Date) {
    this.ID_Livraison = ID_Livraison;
    Livraison.ID_Pannier = ID_Pannier;
    Livraison.ID_Client = ID_Client;
    this.montant = montant;
    this.quantity = quantity;
    this.Date = Date;
  }

  public int getID_Livraison() {
    return ID_Livraison;
  }

  public static Integer getID_Pannier() {
    return ID_Pannier;
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

  public static void setID_Pannier(Integer ID_Pannier) {
    Livraison.ID_Pannier = ID_Pannier;
  }

  public void setID_Client(Integer ID_Client) {
    this.ID_Client = ID_Client;
  }

  public void setDate(LocalDateTime Date) {
    this.Date = Date;
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
            ", ID_Pannier=" + ID_Pannier +
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
    return ID_Livraison == livraison.ID_Livraison &&
            quantity == livraison.quantity &&
            Float.compare(livraison.montant, montant) == 0 &&
            Objects.equals(ID_Pannier, livraison.ID_Pannier) &&
            Objects.equals(ID_Client, livraison.ID_Client) &&
            Objects.equals(Date, livraison.Date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID_Livraison, ID_Pannier, ID_Client, Date, quantity, montant);
  }
}

