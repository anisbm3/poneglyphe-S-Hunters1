package test;

import entities.Commentaire;
import entities.Debat;
import services.ServiceCommentaire;
import services.ServiceDebat;
import utils.MyDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       /* MyDB db1 = MyDB.getInstance();
        MyDB db2 = MyDB.getInstance();

        System.out.println(db1.hashCode());
        System.out.println(db2.hashCode());*/

        Debat d1 = new Debat("One m","bhim","hhh");
        Debat d2 = new Debat("HxH","aaaaaa","Gon");
        Debat d3 = new Debat("Death Note","achref","light");
        Debat d4 = new Debat("AOT","aaaaaa","Eren");


        //Commentaire c1 = new Commentaire("salem",16,18,4);


        ServiceDebat servicesD = new ServiceDebat();
        ServiceCommentaire servicesC = new ServiceCommentaire();

        // ajouter les debats


       //servicesD.ajouter(d3);

        //servicesC.ajouter(c1);


        //Affichage Debat

      /*  try {
            System.out.println(servicesD.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

       */

        //Affichage Commentaire

       /* try {
            System.out.println(servicesC.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/




        // modification
        /*try {
          d3.setID_Debat(3);
            servicesD.modifier(d3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        //supprimer

       /* try {
            d3.setID_Debat(4);
            servicesD.supprimer(d3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

    }
}
