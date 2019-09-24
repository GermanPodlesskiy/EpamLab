package Service;

import java.util.ArrayList;
import java.util.Comparator;

public interface Service<T> {
    void create(T item) throws Exception;

    T read(int id) throws Exception;

    void update(T item) throws Exception;

    void delete(int id) throws Exception;

    void sort(Comparator<T> comparator) throws Exception;

    ArrayList<T> find(Finder<T> finder, Object value) throws Exception;
}
