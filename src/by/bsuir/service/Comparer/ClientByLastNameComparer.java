package by.bsuir.service.Comparer;

import by.bsuir.bean.characters.Client;
import by.bsuir.service.Finder;

import java.util.Comparator;

/**
 * The type Client by last name comparer.
 */
public class ClientByLastNameComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Client value) {
        return obj.getLastName().equals(value.getLastName());
    }

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}