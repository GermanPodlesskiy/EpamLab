package by.bsuir.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Migration.
 *
 * @param <T> the type parameter
 */
public interface Migration<T> {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<T> getAll();

    /**
     * Create.
     *
     * @param entity the entity
     * @throws SQLException the sql exception
     */
    void create(T entity) throws SQLException;
}
