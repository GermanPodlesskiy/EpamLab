package by.bsuir.service.Comparer;

import by.bsuir.bean.characters.Client;
import by.bsuir.service.Finder;

import java.util.Comparator;

/**
 * The type Client by money comparer.
 */
public class ClientByMoneyComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Client value) {
        return value.getMoney() == obj.getMoney();
    }

    @Override
    public int compare(Client o1, Client o2) {
        return Double.compare(o1.getMoney(), o2.getMoney());
    }
}