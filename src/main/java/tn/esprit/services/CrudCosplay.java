package tn.esprit.services;

import tn.esprit.models.Cosplay;
import tn.esprit.utils.MyDataBase;
import tn.esprit.interfaces.IService3;


import java.sql.*;
import java.util.ArrayList;

public class CrudCosplay implements IService3<Cosplay> {

    private final Connection cnx;

    public CrudCosplay() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
   public void add(Cosplay cosplay) {
       // String disableForeignKeyChecks = "SET FOREIGN_KEY_CHECKS = 0;";
        //String enableForeignKeyChecks = "SET FOREIGN_KEY_CHECKS = 1;";
        String qry = "INSERT INTO `cosplay`( `nomCp`, `descriptionCp`, `personnage`, `imageCp`, `dateCreation`, `idmateriaux`,`nomMa`) VALUES (?,?,?,?,?,?,?)";

        try {

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, cosplay.getNomCp());
            stm.setString(2, cosplay.getDescriptionCp());
            stm.setString(3, cosplay.getPersonnage());
            stm.setString(4, cosplay.getImageCp());
            stm.setDate(5, cosplay.getDateCreation());
            stm.setInt(6,cosplay.getIdmateriaux() );
            System.out.println("Identifiant du matériau à insérer: " + cosplay.getIdmateriaux());
            stm.setString(7, cosplay.getNomMa());

            stm.executeUpdate();
            System.out.println("Cosplay inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting cosplay: " + e.getMessage());

        }
    }




 /*  @Override
     public void add(Cosplay cosplay) {

        String qry = "INSERT INTO `cosplay`( `nomCp`, `descriptionCp`, `personnage`, `imageCp`, `dateCreation`, `idmateriaux`) VALUES (?,?,?,?,?,(SELECT idMa FROM materiaux WHERE nomMa = ?))";

        try {

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, cosplay.getNomCp());
            stm.setString(2, cosplay.getDescriptionCp());
            stm.setString(3, cosplay.getPersonnage());
            stm.setString(4, cosplay.getImageCp());
            stm.setDate(5, cosplay.getDateCreation());
            stm.setString(6,cosplay.getNomMa() );

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }*/



    @Override
    public ArrayList<Cosplay> getAll() {
        ArrayList<Cosplay> cosplays = new ArrayList<>();
        String qry = "SELECT * FROM cosplay";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Cosplay cosplay=new Cosplay();
                cosplay.setIdCp(rs.getInt("idCp"));
                cosplay.setNomCp(rs.getString("nomCp"));
                cosplay.setDescriptionCp(rs.getString("descriptionCp"));
                cosplay.setPersonnage(rs.getString("personnage"));
                cosplay.setImageCp(rs.getString("imageCp"));
                cosplay.setPersonnage(rs.getString("personnage"));
                cosplay.setDateCreation(rs.getDate("dateCreation"));
                cosplay.setIdmateriaux(rs.getInt("idmateriaux"));
                cosplay.setNomMa(rs.getString("nomMa"));

                cosplays.add(cosplay);

            }
        }catch (SQLException e){

            // Rethrow the exception or handle it as needed

            throw new RuntimeException("Erreur lors de l'exécution de la requête SQL",e);
        }
       /* for (Cosplay cosplay : cosplays) {
            System.out.println(cosplay);
        }*/
        return cosplays;
    }

     @Override
        public void update(Cosplay cosplay) {
         if (!idExists(cosplay.getIdCp())) {
             System.out.println("The provided ID does not exist.");
             return;
         }
            String qry = "UPDATE `cosplay` SET `nomCp`=?,`descriptionCp`=?,`personnage`=?,`imageCp`=?,`dateCreation`=?,`idmateriaux`=?,`nomMa`=? WHERE `idCp`=?";

            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setString(1, cosplay.getNomCp());
                stm.setString(2, cosplay.getDescriptionCp());
                stm.setString(3, cosplay.getPersonnage());
                stm.setString(4, cosplay.getImageCp());
                stm.setDate(5, cosplay.getDateCreation());
                stm.setInt(6, cosplay.getIdmateriaux());
                stm.setString(7,cosplay.getNomMa());
                stm.setInt(8,cosplay.getIdCp());
                int cosplayId = cosplay.getIdCp();
                System.out.println("Cosplay ID: " + cosplayId);


                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Record updated successfully.");
                } else {
                    System.out.println("No records were updated.");
                }


            } catch (SQLException e) {
                System.out.println("Error updating  "+ e.getMessage());
    // return false;
            }
        }
    private boolean idExists(int id) {
        try {
            String qry = "SELECT * FROM `cosplay` WHERE `idCp`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            return rs.next(); // Returns true if the ID exists in the database
        } catch (SQLException e) {
            System.out.println("Error checking ID existence: " + e.getMessage());
            return false; // Return false if an exception occurs
        }
    }
    @Override
    public void delete(Cosplay cosplay) {
        String qry = "DELETE FROM `cosplay` WHERE `idCp`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setInt(1,cosplay.getIdCp());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}



