package EH.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Livraison {
  private int ID_Livraison,NumTel;
  private String Adresse,Nom_Client;
  private LocalDateTime Date;
    public Livraison() {
    }

    public Livraison(int ID_Livraison, LocalDateTime Date, String Adresse,String Nom_Client, int NumTel ) {
      this.ID_Livraison = ID_Livraison;
      this. Date = Date;
      this.Adresse = Adresse;
      this.Nom_Client = Nom_Client;
      this.NumTel = NumTel;


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


  @Override
  public String toString() {
    return "Livraison{" +
            "ID_Livraison=" + ID_Livraison +
            ", Date=" + Date +
            ", NumTel=" + NumTel +
            ", Adresse='" + Adresse + '\'' +
            ", Nom_Client='" + Nom_Client + '\'' +
            '}';
  }
}

