package parser;

import java.util.ArrayList;
import java.util.List;

public interface XmlParser<T> {
    List<T> getData() throws Exception;
    void setData(ArrayList<T> items) throws Exception;
}
