package tn.esprit.models;

import java.sql.Date;

public class Produit {

    int ID_Produit ,Prix,Stock;
    String Category , Nom, Description;

    public Produit() {

    }


    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getID() {
        return ID_Produit;
    }

    public void setID(int ID_Produit) {
        this.ID_Produit = ID_Produit;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    @Override
    public String toString() {
        return "Personne{" +
                "id=" + ID_Produit +
                ", Prix=" + Prix +
                ", nom='" + Nom + '\'' +
                ", Description='" + Description +
                ", Category='" + Category + '\'' +
                '}';
    }

    public Produit(int id, String Stock, int nom, int category, Date description, String prix) {
    }

    public Produit(int ID_Produit,int Stock, String Category, String Nom, int Prix,String Description) {
        this.ID_Produit = ID_Produit;
        this.Category = Category;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Description = Description;
        this.Stock=Stock;


    }

    public Produit(int Stock,int Prix, String Nom,String Category, String Description) {
        this.Stock=Stock;
        this.Prix = Prix;
        this.Nom = Nom;
        this.Category = Category;
        this.Description = Description;

    }
}


