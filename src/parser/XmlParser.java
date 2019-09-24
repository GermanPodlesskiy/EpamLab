package parser;

import java.util.ArrayList;

/**
 * The interface Xml parser.
 *
 * @param <T> the type parameter
 */
public interface XmlParser<T> {
    /**
     * Gets data.
     *
     * @return the data
     * @throws Exception the exception
     */
    ArrayList<T> getData() throws Exception;

    /**
     * Sets data.
     *
     * @param items the items
     * @throws Exception the exception
     */
    void setData(ArrayList<T> items) throws Exception;
}
