package Entities;

import jdk.jfr.Category;

public class Panier {

    int IDP ,IDU,IDPRO,Numcmd;


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

    public int getIDPRO() {
        return IDPRO;
    }

    public void setIDPRO(int IDPRO) {
        this.IDPRO = IDPRO;
    }

    public int getNumcmd() {
        return Numcmd;
    }

    public void setNumcmd(int Numcmd) {
        this.Numcmd = Numcmd;
    }



    @Override
    public String toString() {
        return "Panier{" +
                "IDP=" + IDP +
                ", IDU=" + IDU +
                ", IDPRO='" + IDPRO + '\'' +
                ", Numcmd='" + Numcmd +

                '}';
    }

    public Panier() {
    }

    public Panier(int IDP, int IDU, int IDPRO, int Numcmd) {
        this.IDP = IDP;
        this.IDU = IDU;
        this.IDPRO = IDPRO;
        this.Numcmd = Numcmd;

    }

    public Panier(int IDU, int IDPRO, int Numcmd) {

        this.IDU = IDU;
        this.IDPRO = IDPRO;
        this.Numcmd = Numcmd;
    }
}
