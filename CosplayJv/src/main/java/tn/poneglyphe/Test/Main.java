package tn.poneglyphe.Test;

import tn.poneglyphe.Interfaces.IService;
import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Models.entities.Materiaux;
import tn.poneglyphe.Services.CrudCosplay;
import tn.poneglyphe.Services.CrudMateriaux;
import tn.poneglyphe.Utils.MyConnection;

import java.sql.Date;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        /* Cosplay cosplay=new Cosplay();
       // Materiaux materiaux=new Materiaux();
         long millis=System.currentTimeMillis();
        cosplay.setNomCp("dfadazd");
        cosplay.setImageCp("daizdioadzoiaj");
        cosplay.setDateCreation(new Date(millis));
        cosplay.setDescriptionCp("helo");
        cosplay.setPersonnage("hello");
        IService<Cosplay> cosplayIService=new CrudCosplay();
        cosplayIService.add(cosplay);*/
       // Cosplay cos=new Cosplay();
        // cos.setIdCp(5);
        //cosplayIService.delete(cos);
       // System.out.println(cosplayIService.getAll());
        MyConnection cn = MyConnection.getInstance();
        Materiaux m = new Materiaux(12, "hukuji", "coton", "dispo a sfax");
        CrudMateriaux crud = new CrudMateriaux();
         crud.add(m);
        System.out.println(crud.getAll());

            crud.update(m);

    }
}