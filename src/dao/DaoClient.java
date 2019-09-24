package dao;

import bean.Characters.Client;
import parser.ClientXmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Dao client.
 */
public class DaoClient implements Dao<Client> {
    private ClientXmlParser clientXmlParser;
    private ArrayList<Client> clients = new ArrayList<>();

    /**
     * Gets client xml parser.
     *
     * @return the client xml parser
     */
    public ClientXmlParser getClientXmlParser() {
        return clientXmlParser;
    }

    /**
     * Sets client xml parser.
     *
     * @param clientXmlParser the client xml parser
     */
    public void setClientXmlParser(ClientXmlParser clientXmlParser) {
        this.clientXmlParser = clientXmlParser;
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Instantiates a new Dao client.
     */
    public DaoClient() {
    }

    /**
     * Instantiates a new Dao client.
     *
     * @param clientXmlParser the client xml parser
     */
    public DaoClient(ClientXmlParser clientXmlParser) {
        this.clientXmlParser = clientXmlParser;

        try {
            clients = clientXmlParser.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client obj) throws DaoException {

        Stream<Client> stream = clients.stream().filter(x -> x.equals(obj));

        if (stream.count() != 0) {
            clients.forEach(x -> clients.remove(obj));

            try {
                clientXmlParser.setData(clients);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        throw new DaoException("This client isn't exist");
    }

    @Override
    public void add(Client obj) throws DaoException {
        if (obj == null) {
            throw new DaoException("Client is null");
        }

        if (clients.stream().anyMatch(x -> x.equals(obj))) {
            throw new DaoException("This client in exist");
        }

        clients.add(obj);

        try {
            clientXmlParser.setData(clients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client get(int id) throws DaoException {
        return clients.stream().filter(x -> x.getId() == id).findFirst()
                .orElseThrow(() -> new DaoException("This client is not exist"));
    }

    @Override
    public void addAll(List<Client> items) throws DaoException {
        if (items == null) {
            throw new DaoException("Clients is null");
        }

        Stream<Client> clients = this.clients.stream();
        if (items.stream().anyMatch(x -> clients.anyMatch(z -> z.equals(x)))) {
            throw new DaoException("Some of this obj is exist");
        }

        this.clients.addAll(items);

        try {
            clientXmlParser.setData(this.clients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
