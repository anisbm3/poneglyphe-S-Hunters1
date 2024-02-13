package test;

import entities.Debat;
import services.ServiceDebat;
import utils.MyDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       /* MyDB db1 = MyDB.getInstance();
        MyDB db2 = MyDB.getInstance();

        System.out.println(db1.hashCode());
        System.out.println(db2.hashCode());*/

        Debat d1 = new Debat("One Piece","bhim","hhh");
        Debat d2 = new Debat("HxH","aaaaaa","Gon");
        Debat d3 = new Debat("Death Note","aaaaaa","L");
        Debat d4 = new Debat("AOT","aaaaaa","Eren");




        ServiceDebat services = new ServiceDebat();
        // ajouter les debats


        //services.ajouter(d1);


        //Affichage

       /* try {
            System.out.println(services.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/

        // modification
        /*try {
            services.modifier(d1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        //supprimer

        /*try {
            services.supprimer(d1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/
    }
}
