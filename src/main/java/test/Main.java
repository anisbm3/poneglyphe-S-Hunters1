package test;
import Entities.Panier;
import Services.ServicePanier;

import Entities.Produit;
import Services.ServiceProduit;
import Utils.MyDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
  /* MyDB db1 = MyDB.getInstance();
        MyDB db2 = MyDB.getInstance();

        System.out.println(db1.hashCode());
        System.out.println(db2.hashCode());*/

        Produit p2 = new Produit(3, "Trabelsi", "21", 70, "Syrine");
        Panier pa2 = new Panier(3, 70, 70, 70);

        ServiceProduit services = new ServiceProduit();
        ServicePanier services2 = new ServicePanier();
      //ajouter Panier
         /* try {
            services2.ajouter(pa2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       //afficher panier
        /*try {
            System.out.println(services2.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());*/
//Ajouter Produit
      /* try {
            services.ajouter(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

            //Afficher Produit
      /*  try {
            System.out.println(services.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/


            //Modifier
          /*try {
            services.modifier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
            //Supprimer Produit
        /*try {
            services.supprimer(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        //Supprimer panier
        /*try {
            services2.supprimer(pa2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        try {
            services2.modifier(pa2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}