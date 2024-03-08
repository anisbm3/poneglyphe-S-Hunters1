package tn.esprit.services;

import tn.esprit.interfaces.IService3;

import tn.esprit.models.Materiaux;
import tn.esprit.utils.MyDataBase;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;

public class CrudMateriaux implements IService3<Materiaux> {
    private final Connection cnx;
    public CrudMateriaux() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(Materiaux materiaux) {
        String qry="INSERT INTO `materiaux`( `nomMa`, `typeMa`, `disponibilite`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, materiaux.getNomMa());
            stm.setString(2, materiaux.getTypeMa());
            stm.setString(3, materiaux.getDisponibilite());


            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    public  int getIdMateriauxFromName (String nomMa) {
        int idMateriaux = 0;
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT idMa FROM materiaux WHERE nomMa = ?";
            PreparedStatement cs = cnx.prepareStatement(req);
            cs.setString(1, nomMa);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                idMateriaux = rs.getInt("idMa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idMateriaux;
    }
    public String getNomMateriauxById (int idmateriaux) {
        String nomMateriaux = "";
        try {

            String qry = "SELECT nomMa FROM materiaux WHERE idMa = ?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, idmateriaux);
            //débogage
           // System.out.println("Executing SQL query: " + qry);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                nomMateriaux = rs.getString("nomMa");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //débogage pour voir le nom du matériau récupéré
        System.out.println("Nom du matériau récupéré: " + nomMateriaux);

        return nomMateriaux;
    }
   /* public String getNomByIdMa(int idMa ) throws SQLException {
        String nomMa = null;
        String req = "SELECT nomMa FROM reservation WHERE idMa=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, idMa );
            try (ResultSet result = pre.executeQuery()) {
                if (result.next()) {
                    nomMa = result.getString("nomMa");
                }
            }
        }
            return nomMa;
    }*/
    @Override
    public ArrayList<Materiaux> getAll() {
        ArrayList<Materiaux> materiau = new ArrayList<>();
        String qry = "SELECT * FROM materiaux";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Materiaux  mat =new Materiaux();
                mat.setNomMa(rs.getString(2));
                mat.setTypeMa(rs.getString(3));
                mat.setDisponibilite(rs.getString(4));
                mat.setIdMa(rs.getInt(1));
                materiau.add( mat);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return materiau;
    }
/// recuperation des noms

    public ArrayList<String> getAllMateriauxNoms() {
        ArrayList<String> materiauxNoms = new ArrayList<>();
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT nomMa FROM materiaux";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                materiauxNoms.add(rs.getString("nomMa"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materiauxNoms;
    }

    @Override
    public void update(Materiaux materiaux) {
        String qry = " UPDATE `materiaux` SET`nomMa`=?,`typeMa`=?,`disponibilite`=? WHERE idMa=?";
        try {

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, materiaux.getNomMa());
            stm.setString(2, materiaux.getTypeMa());
            stm.setString(3, materiaux.getDisponibilite());
            stm.setInt(4,materiaux.getIdMa());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void delete(Materiaux materiaux) {
        String qry = " DELETE  from materiaux where idMa=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setInt(1,materiaux.getIdMa());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public ArrayList<Materiaux> getAllMateriaux() {
         return null;
    }
}
