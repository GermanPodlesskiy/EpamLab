package by.bsuir.implementation;

import by.bsuir.dao.ClientSqlDao;
import by.bsuir.dao.DaoClient;
import by.bsuir.parser.ClientXmlParser;
import by.bsuir.service.ClientService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Main.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ClientXmlParser clientParser = new ClientXmlParser("D:\\learn\\Epam\\EpamLab1\\src\\main\\resources\\client.xml", "D:\\learn\\Epam\\EpamLab1\\src\\main\\resources\\client.xsd");
        ClientService clientService = new ClientService(new DaoClient(clientParser));

        clientService.getAll().forEach(x -> System.out.print(x.getHumanId() + " "));
        System.out.println();

        ClientSqlDao conn = null;

        try{
            conn = new ClientSqlDao();
            conn.setConnection(getConnection());
            var statement = conn.getConnection().createStatement();
            //  System.out.println(statement.executeUpdate("insert into `table` (is_free, number) VALUES (1, 100)"));
        }
        catch(SQLException | IOException e) {
            logger.error(e.getMessage());
        }

        var f = conn.getAll();
        var ff = 3;
//
//        //Create
//        System.out.println("Create:");
//        clientService.create(new Client(5, "FirstName5", "LastName5", 100));
//        System.out.println(clientService.read(2).getFirstName());
//
//        //Read
//        System.out.println("Read:");
//        Client client = clientService.read(1);
//        System.out.println(client.getId() + " - " + client.getFirstName());
//
//        //Update
//        System.out.println("Update:");
//        client.setMoney(50000);
//        clientService.update(client);
//        clientService.getAll().forEach(x -> System.out.println(x.getId() + " - " + x.getMoney()));
//
//        //Delete
//        System.out.println("Delete:");
//        clientService.delete(1);
//        clientService.getAll().forEach(x -> System.out.println(x.getId()));
//
//        //Sort
//        System.out.println("Sort:");
//        clientService.sort(new ClientByMoneyComparer());
//        clientService.getAll().forEach(x -> System.out.println(x.getMoney() + " - " + x.getFirstName()));
//
//        //Find
//        System.out.println("Find:");
//        client = clientService.find(new ClientByFirstNameComparer(), clientService.getAll().get(0)).get(0);
//        System.out.println(client.getId());
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
