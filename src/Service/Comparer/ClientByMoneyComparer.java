package Service.Comparer;

import Service.Finder;
import bean.Characters.Client;

import java.util.Comparator;

/**
 * The type Client by money comparer.
 */
public class ClientByMoneyComparer implements Comparator<Client>, Finder<Client> {
    @Override
    public boolean isFound(Client obj, Object value) {
        return value.equals(obj.getMoney());
    }

    @Override
    public int compare(Client o1, Client o2) {
        return Integer.compare(o1.getMoney(), o2.getMoney());
    }
}