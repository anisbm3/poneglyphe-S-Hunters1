package tn.poneglyphe.Interfaces;

import java.util.ArrayList;

public interface IService<T> {
    void add (T t);
    ArrayList<T> getAll();
    void update(T t);
    void delete(T t);
}
