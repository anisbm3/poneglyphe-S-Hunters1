package Entities;

import jdk.jfr.Category;

public class Achats {

    int IDA,IDP ,IDPRO,Quantite;


    public int getIDP() {
        return IDP;
    }

    public void setIDP(int IDP) {
        this.IDP = IDP;
    }

    public int getIDPRO() {
        return IDPRO;
    }

    public void setIDPRO(int IDPRO) {
        this.IDPRO = IDPRO;
    }
    public int getIDA() {
        return IDA;
    }

    public void setIDA(int IDA) {
        this.IDA = IDA;
    }
    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }





    @Override
    public String toString() {
        return "Panier{" +
                "IDA=" + IDA+
                ", IDP=" + IDP + "IDPRO=" + IDPRO+
                ", Quantite=" + Quantite +


                '}';
    }

    public Achats() {
    }

    public Achats(int IDA, int IDP, int IDPRO, int Quantite) {
        this.IDA = IDA;
        this.IDP = IDP;
        this.IDPRO = IDPRO;
        this.Quantite = Quantite;

    }

    public Achats(int IDP, int IDPRO, int Quantite) {

        this.IDP = IDP;
        this.IDPRO = IDPRO;
        this.Quantite = Quantite;

    }
}

