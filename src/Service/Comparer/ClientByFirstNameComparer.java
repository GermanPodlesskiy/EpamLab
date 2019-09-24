package Service.Comparer;

import Service.Finder;
import bean.Characters.Client;

import java.util.Comparator;

public class ClientByFirstNameComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Object value) {
        return obj.getFirstName().equals(value);
    }

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
