package EH.interfaces;

import java.util.List;
import java.sql.SQLException;

public interface IService <T> {

    public void ajouter(T t);
    public List<T> afficher() throws SQLException;

    public boolean supprimer(int id) throws SQLException;
    boolean deleteAll();
    public void modifier(int id, T t) throws SQLException;

}


