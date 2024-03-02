package tn.poneglyphe.Test;


import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Models.entities.Materiaux;
import tn.poneglyphe.Services.CrudCosplay;
import tn.poneglyphe.Services.CrudMateriaux;
import tn.poneglyphe.Utils.MyConnection;

import java.sql.Date;



public class Main {

    public static void main(String[] args) {
       // MyConnection cn = MyConnection.getInstance();
       /* Cosplay c=new Cosplay(12,"naruto","de couleur orange ..","naruto","naruto.jpg",new Date(System.currentTimeMillis()),3);
        Cosplay c1=new Cosplay(12,"one piece luffy hat","round hat of color ..","Monkey d.luffy","luffy.jpg",new Date(System.currentTimeMillis()),1);
        Cosplay c2=new Cosplay(12,"one piece ","yellow ..","Monkey d.luffy","luffy.jpg",new Date(System.currentTimeMillis()),1);*/

        // Materiaux materiaux=new Materiaux();
         //long millis=System.currentTimeMillis();

        //c.setDateCreation(new Date(millis));

       CrudCosplay ccs =new CrudCosplay();
       // ajout cosplay;
      // ccs.add(c);
      // ccs.add(c2);
        //display



        // update
        /*c2.setIdCp(4);
        c2.setPersonnage("sasusaku");
        c2.setIdmateriaux(1);
        c2.setDateCreation(new Date(System.currentTimeMillis()));
        c2.setNomCp("one piece ");
        c2.setDescriptionCp("round");
        c2.setImageCp("hat.jpg");
        ccs.update(c2);*/
        System.out.println(ccs.getAll());
       // boolean isUpdated = ccs.update(c2);

        /*if (isUpdated) {
            System.out.println("Data is updated");
        } else {
            System.out.println("Failed to update data");
        }*/
        //c1.setIdCp(3);
        //ccs.delete(c1);

// remplir record avec des données
       // Materiaux m = new Materiaux(12, "tissu de coton", "textile", "dispo a sfax");
        //Materiaux m1 = new Materiaux(60, "MousseEva", "plastique", "indisponible");
       // Materiaux m2 = new Materiaux(2, "Papier mâché", "papier", "disponible");

 //instance des services
      //  CrudMateriaux crud = new CrudMateriaux();
// ajouter les materiaux

         /*   crud.add(m);
        crud.add(m1);
        crud.add(m2);*/
        // modifier
       /* m2.setIdMa(3);
        m2.setNomMa("hikuji");
       m2.setTypeMa("coton");
       m2.setDisponibilite("dispo");
           crud.update(m2);*/
            //afficher
      // System.out.println(crud.getAll());
        //crud.delete(m1);

    }
}