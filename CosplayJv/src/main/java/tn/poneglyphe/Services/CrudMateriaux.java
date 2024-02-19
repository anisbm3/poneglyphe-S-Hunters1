package tn.poneglyphe.Services;

import tn.poneglyphe.Interfaces.IService;

import tn.poneglyphe.Models.entities.Materiaux;
import tn.poneglyphe.Utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

public class CrudMateriaux implements IService<Materiaux> {
    private final Connection cnx;
    public CrudMateriaux() {
        cnx = MyConnection.getInstance().getCnx();
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

    @Override
    public void update(Materiaux materiaux) {
        String qry = " UPDATE `materiaux` SET idMa=?,`nomMa`=?,`typeMa`=?,`disponibilite`=? WHERE idMa=?";
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
}
