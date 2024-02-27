package Entities;

import jdk.jfr.Category;

public class Panier {

    int IDP ,IDU;


    public int getIDP() {
        return IDP;
    }

    public void setIDP(int IDP) {
        this.IDP = IDP;
    }

    public int getIDU() {
        return IDU;
    }

    public void setIDU(int IDU) {
        this.IDU = IDU;
    }





    @Override
    public String toString() {
        return "Panier{" +
                "IDP=" + IDP +
                ", IDU=" + IDU +


                '}';
    }

    public Panier() {
    }

    public Panier(int IDP, int IDU) {
        this.IDP = IDP;
        this.IDU = IDU;

    }

    public Panier(int IDU) {

        this.IDU = IDU;

    }
}

