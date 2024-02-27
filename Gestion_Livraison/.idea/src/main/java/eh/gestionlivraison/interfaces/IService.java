package eh.gestionlivraison.interfaces;


import java.util.ArrayList;

public interface IService<T> {
    void add (T t);
    ArrayList<T> getAll();
    boolean update(T t);
    boolean delete(T t);
    boolean deleteAll();

}
