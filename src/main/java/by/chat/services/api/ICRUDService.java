package by.chat.services.api;

import java.util.List;

public interface ICRUDService<T, S> {
    List<T> get();

    T save(S item);

    T get(int id);

}

