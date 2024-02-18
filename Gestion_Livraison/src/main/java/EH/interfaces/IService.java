package EH.interfaces;

import java.util.List;
import java.sql.SQLException;

public interface IService <T> {

    public void ajouter(T t);
    public List<T> afficher() throws SQLException;

    public void supprimer(int id) throws SQLException;

    public void modifier(int id, T t) throws SQLException;

}


