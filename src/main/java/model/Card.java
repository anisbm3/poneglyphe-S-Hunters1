package model;

public class Card {
    private String Name;
    private String Category;
    private String Description;
    private int prix;

    public Card() {
    }

    public Card(String name, String category, String description, int prix) {
        Name = name;
        Category = category;
        Description = description;
        this.prix = prix;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
