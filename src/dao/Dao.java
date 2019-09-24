package dao;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T> {
    /**
     * Delete.
     *
     * @param obj the obj
     * @throws DaoException the dao exception
     */
    void delete(T obj) throws DaoException;

    /**
     * Add.
     *
     * @param obj the obj
     * @throws DaoException the dao exception
     */
    void add(T obj) throws DaoException;

    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws DaoException the dao exception
     */
    T get(int id) throws DaoException;

    /**
     * Add all.
     *
     * @param items the items
     * @throws DaoException the dao exception
     */
    void addAll(List<T> items) throws DaoException;
}
