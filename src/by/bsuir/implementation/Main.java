package by.bsuir.implementation;

import by.bsuir.bean.characters.Client;
import by.bsuir.dao.DaoClient;
import by.bsuir.parser.ClientXmlParser;
import by.bsuir.service.ClientService;
import by.bsuir.service.Comparer.ClientByFirstNameComparer;
import by.bsuir.service.Comparer.ClientByMoneyComparer;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ClientXmlParser clientParser = new ClientXmlParser("./client.xml");
        ClientService clientService = new ClientService(new DaoClient(clientParser));

        clientService.getAll().forEach(x -> System.out.print(x.getId() + " "));
        System.out.println();

        //Create
        System.out.println("Create:");
        clientService.create(new Client(5, "FirstName5", "LastName5", 100));
        System.out.println(clientService.read(2).getFirstName());

        //Read
        System.out.println("Read:");
        Client client = clientService.read(1);
        System.out.println(client.getId() + " - " + client.getFirstName());

        //Update
        System.out.println("Update:");
        client.setMoney(50000);
        clientService.update(client);
        clientService.getAll().forEach(x -> System.out.println(x.getId() + " - " + x.getMoney()));

        //Delete
        System.out.println("Delete:");
        clientService.delete(1);
        clientService.getAll().forEach(x -> System.out.println(x.getId()));

        //Sort
        System.out.println("Sort:");
        clientService.sort(new ClientByMoneyComparer());
        clientService.getAll().forEach(x -> System.out.println(x.getMoney() + " - " + x.getFirstName()));

        //Find
        System.out.println("Find:");
        client = clientService.find(new ClientByFirstNameComparer(), clientService.getAll().get(0)).get(0);
        System.out.println(client.getId());
    }
}
