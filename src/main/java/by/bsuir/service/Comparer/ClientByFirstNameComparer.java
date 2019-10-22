package by.bsuir.service.Comparer;

import by.bsuir.bean.characters.Client;
import by.bsuir.service.Finder;

import java.util.Comparator;

/**
 * The type Client by first name comparer.
 */
public class ClientByFirstNameComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Client value) {
        return obj.getFirstName().equals(value.getFirstName());
    }

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
