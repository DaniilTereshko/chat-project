package by.chat.services.api;

import by.chat.core.exception.IncorrectUserValueException;

import java.util.List;

public interface ICRUDService<T, S> {
    List<T> get();

    T save(S item) throws IncorrectUserValueException;

    T get(int id);

}

