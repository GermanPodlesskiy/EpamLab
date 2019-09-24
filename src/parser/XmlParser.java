package parser;

import java.util.ArrayList;

public interface XmlParser<T> {
    ArrayList<T> getData() throws Exception;
    void setData(ArrayList<T> items) throws Exception;
}
