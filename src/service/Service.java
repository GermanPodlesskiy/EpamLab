package service;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The interface service.
 *
 * @param <T> the type parameter
 */
public interface Service<T> {
    /**
     * Create.
     *
     * @param item the item
     * @throws Exception the exception
     */
    void create(T item) throws Exception;

    /**
     * Read t.
     *
     * @param id the id
     * @return the t
     * @throws Exception the exception
     */
    T read(int id) throws Exception;

    /**
     * Update.
     *
     * @param item the item
     * @throws Exception the exception
     */
    void update(T item) throws Exception;

    /**
     * Delete.
     *
     * @param id the id
     * @throws Exception the exception
     */
    void delete(int id) throws Exception;

    /**
     * Sort.
     *
     * @param comparator the comparator
     * @throws Exception the exception
     */
    void sort(Comparator<T> comparator) throws Exception;

    /**
     * Find array list.
     *
     * @param finder the finder
     * @param value  the value
     * @return the array list
     * @throws Exception the exception
     */
    ArrayList<T> find(Finder<T> finder, Object value) throws Exception;
}
