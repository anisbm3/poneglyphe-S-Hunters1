package org.example;
import entities.*;
import service.personneService;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args){

        user p= new user("yas", 88888 ,"mohamed yassine","kassar","15", 416516515,"fnjkf ","1513","client");

        personneService ps=new personneService();
        try {
            //ps.add(p);
            //ps.delete(p);
            //ps.update(p);
            System.out.println(ps.Readall());

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}