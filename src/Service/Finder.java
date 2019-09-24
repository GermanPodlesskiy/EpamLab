package Service;

public interface Finder<T> {
    boolean isFound(T obj, Object value);
}
