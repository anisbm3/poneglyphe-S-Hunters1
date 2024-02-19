package tn.poneglyphe.Services;

import tn.poneglyphe.Interfaces.IService;
import tn.poneglyphe.Models.entities.Cosplay;
import tn.poneglyphe.Utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

public class CrudCosplay implements IService<Cosplay> {

    private final Connection cnx;

    public CrudCosplay() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(Cosplay cosplay) {
        String qry = "INSERT INTO `cosplay`( `nomCp`, `descriptionCp`, `personnage`, `imageCp`, `dateCreation`, `idmateriaux`) VALUES (?,?,?,?,?,?)";

        try {
            int matId = cosplay.getIdmateriaux();
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, cosplay.getNomCp());
            stm.setString(2, cosplay.getDescriptionCp());
            stm.setString(3, cosplay.getPersonnage());
            stm.setString(4, cosplay.getImageCp());
            stm.setDate(5, cosplay.getDateCreation());
            stm.setInt(6,matId );

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public ArrayList<Cosplay> getAll() {
        ArrayList<Cosplay> cosplays = new ArrayList<>();
        String qry = "SELECT * FROM cosplay";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Cosplay cosplay=new Cosplay();
                cosplay.setImageCp(rs.getString("imageCp"));
                cosplay.setNomCp(rs.getString("nomCp"));
                cosplay.setDescriptionCp(rs.getString("descriptionCp"));
                cosplay.setPersonnage(rs.getString("personnage"));
                cosplay.setDateCreation(rs.getDate(6));
                cosplay.setIdCp(rs.getInt(1));
                cosplay.setIdmateriaux(rs.getInt(7));
                cosplays.add(cosplay);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return cosplays;
    }

    @Override
    public void update(Cosplay cosplay) {
        String qry = "UPDATE `cosplay` SET `nomCp`=?,`descriptionCp`=?,`personnage`=?,`imageCp`=?,`dateCreation`=?,`idmateriaux`=? WHERE `idCp`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, cosplay.getNomCp());
            stm.setString(2, cosplay.getDescriptionCp());
            stm.setString(3, cosplay.getPersonnage());
            stm.setString(4, cosplay.getImageCp());
            stm.setDate(5, cosplay.getDateCreation());
            stm.setInt(6, 1);
            stm.setInt(7,cosplay.getIdCp());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

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



