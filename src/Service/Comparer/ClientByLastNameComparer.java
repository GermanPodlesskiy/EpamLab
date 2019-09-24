package Service.Comparer;

import Service.Finder;
import bean.Characters.Client;

import java.util.Comparator;

/**
 * The type Client by last name comparer.
 */
public class ClientByLastNameComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Object value) {
        return obj.getLastName().equals(value);
    }

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}