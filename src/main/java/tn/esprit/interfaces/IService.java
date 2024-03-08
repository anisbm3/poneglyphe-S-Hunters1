package tn.esprit.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public void supprimer(T t) throws SQLException;

    void supprimerParId(int idCommentaire) throws SQLException;

    public List<T> afficher() throws SQLException;


//getOne getById
}