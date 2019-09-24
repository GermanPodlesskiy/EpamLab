package Service;

import bean.Characters.Client;
import dao.DaoClient;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Client service.
 */
public class ClientService implements Service<Client> {
    private DaoClient daoClient;

    /**
     * Gets dao client.
     *
     * @return the dao client
     */
    public DaoClient getDaoClient() {
        return daoClient;
    }

    /**
     * Instantiates a new Client service.
     *
     * @param daoClient the dao client
     */
    public ClientService(DaoClient daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public void create(Client item) throws Exception {
        if (item == null) {
            throw new Exception("Client not found.");
        }

        if (daoClient.getClients().stream().anyMatch(x -> x.getId() == item.getId())) {
            throw new Exception("Client not found.");
        }

        daoClient.add(item);
    }

    @Override
    public Client read(int id) throws Exception {
        return daoClient.get(id);
    }

    @Override
    public void update(Client item) throws Exception {
        for (Client x : daoClient.getClients()) {
            if (x.getId() == item.getId()) {
                daoClient.delete(x);
                daoClient.add(item);
                return;
            }
        }

        throw new Exception("Client not found.");
    }

    @Override
    public void delete(int id) throws Exception {
        daoClient.delete(daoClient.get(id));
    }

    @Override
    public void sort(Comparator<Client> comparator) throws Exception {
        if (comparator == null) {
            throw new Exception("Comparator is null.");
        }

        try {
            daoClient.getClients().sort(comparator);
        } catch (Exception e) {
            throw new Exception("No such comparator.");
        }
    }

    @Override
    public ArrayList<Client> find(Finder<Client> finder, Object value) throws Exception {
        if (finder == null) {
            throw new Exception("Finder is null.");
        }

        if (value == null) {
            throw new Exception("Value is null.");
        }

        ArrayList<Client> clients = new ArrayList<>();

        daoClient.getClients().stream().filter(x -> finder.isFound(x, value))
                .forEach(clients::add);

        if (clients.isEmpty()) {
            throw new Exception("Clients not found.");
        }

        return clients;
    }
}
