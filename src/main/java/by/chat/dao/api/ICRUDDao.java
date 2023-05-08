package by.chat.dao.api;


import java.util.List;

public interface ICRUDDao<T> {
    List<T> get();

    T save(T item);

    T get(int id);

}
