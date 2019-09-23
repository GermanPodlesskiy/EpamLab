package dao;

import java.util.List;

public interface Dao<T> {
    void delete(T obj) throws DaoException;

    void add(T obj) throws DaoException;

    T get(int id) throws DaoException;

    void addAll(List<T> items) throws DaoException;
}
