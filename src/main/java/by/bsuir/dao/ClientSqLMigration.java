package by.bsuir.dao;

import by.bsuir.bean.characters.Client;
import by.bsuir.bean.space.Table;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client sq l migration.
 */
public class ClientSqLMigration extends BaseMysql<Client> implements Migration<Client> {
    private static final String READ = "select \tc.id, c.first_name, c.last_name, c.money, c.table_id, c.first_name, c.last_name, t.is_free, t.`number`\n" +
            "from `client` c\n" +
            "inner join `table` t on c.table_id = t.id\n" +
            "where c.id = ?";
    private static final String READ_ID = "select c.id from `client` c";
    private static final String READ_TABLE_ID = "select t.id from `table` t";
    private static final String INSERT_CLIENT = "insert into `client` (`id`, `first_name`, `last_name`, `money`, `table_id`) values (?, ?, ?, ?, ?)";
    private static final String INSERT_TABLE = "insert into `table` (`id`, `is_free`, `number`) values";

    private static final String CLIENT_ID = "id";
    private static final String MONEY = "money";
    private static final String TABLE_ID = "table_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String IS_FREE = "is_free";
    private static final String NUMBER = "number";

    private static final Logger logger = Logger.getLogger(ClientSqLMigration.class);

    /**
     * Instantiates a new Client sq l migration.
     */
    public ClientSqLMigration() {
    }

    @Override
    protected Client fillFieldsObject(ResultSet resultSet) throws SQLException {
        return initializeEntity(resultSet);
    }

    @Override
    protected void setFieldStatement(PreparedStatement statement, Client entity) throws SQLException {
        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setDouble(4, entity.getMoney());

        if (entity.getTable() == null) {
            statement.setNull(5, Types.INTEGER);
        } else {
            statement.setInt(5, entity.getTable().getId());
        }
    }

    @Override
    protected List<Client> fillList(ResultSet resultSet) throws SQLException {
        List<Client> clients = new ArrayList<>();

        while (resultSet.next()) {
            var client = initializeEntity(resultSet);
            clients.add(client);
        }

        return clients;
    }

    @Override
    protected Client initializeEntity(ResultSet resultSet) throws SQLException {
        var client = new Client(resultSet.getInt(CLIENT_ID),
                resultSet.getDouble(MONEY),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(LAST_NAME));

        client.setTable(new Table(resultSet.getInt(TABLE_ID), resultSet.getBoolean(IS_FREE),
                resultSet.getInt(NUMBER)));

        return client;
    }

    @Override
    public List<Client> getAll() throws DaoException {
        return defaultRead(READ.replaceAll("\\?", "c.id"));
    }

    @Override
    public void create(Client entity) throws SQLException {
        if (getClientIds().stream().noneMatch(x -> x == entity.getId())) {
            if (entity.getTable() != null && getTableIds().stream().noneMatch(x -> x == entity.getId())) {
                statement.executeUpdate(INSERT_TABLE + "(" + entity.getTable().getId() + "," + entity.getTable().isFree() + "," + entity.getTable().getNumber() + ")");
            }

            defaultCreate(INSERT_CLIENT, entity);
            return;
        }

        logger.warn("WARN: id = " + entity.getId() + "; the client with that id already exists");
    }

    private List<Integer> getClientIds() throws SQLException {
        var resultSet = statement.executeQuery(READ_ID);
        var ids = new ArrayList<Integer>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        resultSet.close();

        return ids;
    }

    private List<Integer> getTableIds() throws SQLException {
        var resultSet = statement.executeQuery(READ_TABLE_ID);
        var ids = new ArrayList<Integer>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        resultSet.close();

        return ids;
    }
}
