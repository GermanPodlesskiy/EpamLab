package by.bsuir.dao;

import by.bsuir.bean.characters.Client;
import by.bsuir.bean.menu.MenuItem;
import by.bsuir.bean.space.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client sql dao.
 */
public class ClientSqlDao extends BaseMysql<Client> implements Dao<Client> {

    private static final String CREATE = "";
    private static final String READ = "select \tc.id, c.money, c.human_id, c.table_id, h.first_name, h.last_name, t.is_free, t.`number`, cmix_mn.menu_item_id, cmix_mn.price, cmix_mn.`name`\n" +
            "from `client` c\n" +
            "inner join `table` t on c.table_id = t.id\n" +
            "inner join human h on c.human_id = h.id\n" +
            "left join (\n" +
            "\tSELECT cmix.client_id, cmix.menu_item_id, mn.price, mn.`name`  \n" +
            "    FROM client_menu_item_xref cmix\n" +
            "\tinner join menu_item mn on mn.id = cmix.menu_item_id) cmix_mn\n" +
            "    on c.id = cmix_mn.client_id\n" +
            "where c.id = ?";
    private static final String READ_MENU_ITEMS = "SELECT cmix.client_id, cmix.menu_item_id, mn.price, mn.`name`  FROM client_menu_item_xref cmix\n" +
            "inner join menu_item mn on mn.id = cmix.menu_item_id";

    private static final String CLIENT_ID = "id";
    private static final String MONEY = "money";
    private static final String HUMAN_ID = "human_id";
    private static final String TABLE_ID = "table_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String IS_FREE = "is_free";
    private static final String NUMBER = "number";
    private static final String MENU_ITEM_ID = "menu_item_id";
    private static final String PRICE = "price";
    private static final String NAME = "name";

    /**
     * Instantiates a new Client sql dao.
     *
     * @throws SQLException the sql exception
     */
    public ClientSqlDao() throws SQLException {
    }

    @Override
    Client fillFieldsObject(ResultSet resultSet) throws SQLException {
        return initializeEntity(resultSet);
    }

    @Override
    void setFieldStatement(PreparedStatement statement, Client entity) throws SQLException {
        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setDouble(4, entity.getMoney());
        statement.setString(5, entity.getChosenMenuItems().toString());
        statement.setString(6, entity.getTable().toString());
    }

    @Override
    List<Client> fillList(ResultSet resultSet) throws SQLException {
        List<Client> clients = new ArrayList<>();

        while (resultSet.next()) {
            var menuItem = getMenuItemForClient(resultSet);
            var client = initializeEntity(resultSet);
            var clientId = client.getId();
            var clientsStream = clients.stream();
            var existClient = clientsStream.filter(x -> x.getId() == clientId).findFirst().orElse(null);

            if (existClient == null) {
                client.getChosenMenuItems().add(menuItem);
                clients.add(client);
            } else {
                existClient.getChosenMenuItems().add(menuItem);
            }

        }

        return clients;
    }

    @Override
    Client initializeEntity(ResultSet resultSet) throws SQLException {
        var client = new Client(resultSet.getInt(CLIENT_ID),
                resultSet.getDouble(MONEY),
                resultSet.getInt(HUMAN_ID),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(LAST_NAME));

        client.setTable(new Table(resultSet.getInt(TABLE_ID), resultSet.getBoolean(IS_FREE),
                resultSet.getInt(NUMBER)));

        return client;
    }


    @Override
    public void delete(Client obj) throws Exception {

    }

    @Override
    public void add(Client obj) throws Exception {

    }

    @Override
    public Client get(int id) throws Exception {
        return null;
    }

    @Override
    public void addRange(List<Client> items) {
    }

    @Override
    public List<Client> getAll() throws Exception {
        return defaultRead(READ.replaceAll("\\?", "c.id"));
    }

    private MenuItem getMenuItemForClient(ResultSet resultSet) throws SQLException {
        var name = resultSet.getString(CLIENT_ID);

        return name == null ? null : new MenuItem(resultSet.getInt(MENU_ITEM_ID),
                resultSet.getString(NAME), resultSet.getDouble(PRICE));
    }
}
