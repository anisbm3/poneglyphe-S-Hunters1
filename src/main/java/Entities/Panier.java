package Entities;

import java.util.Date;

public class Panier {

    int IDP, quantity, price, panier_id;
    String prod_name;
    Date date;

    public Panier() {

    }


    public int getIDP() {
        return IDP;
    }

    public void setIDP(int IDP) {
        this.IDP = IDP;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Panier(int IDP, int quantity, int price, int panier_id, String prod_name, Date date) {
        this.IDP = IDP;
        this.quantity = quantity;
        this.price = price;
        this.panier_id = panier_id;
        this.prod_name = prod_name;
        this.date = date;
    }

    public Panier(String prod_name, int quantity, int price, int IDP) {
        this.prod_name = prod_name;
        this.quantity = quantity;
        this.price = price;
        this.IDP = IDP;
    }

    public Panier(int IDP, String prod_name, int quantity, int price, Date date, int panier_id) {


    }
}

