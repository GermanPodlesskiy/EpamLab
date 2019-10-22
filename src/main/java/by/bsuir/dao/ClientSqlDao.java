package by.bsuir.dao;

import by.bsuir.bean.characters.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientSqlDao extends BaseMysql<Client> implements Dao<Client> {

    private static final String CREATE = "INSERT INTO `client` (`id`,`name`, `pass`,`login`,`freemiles`,`discount`,`surname`,`gender`) VALUES (?,?,?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM `client` WHERE `id`=?";
    private static final String READ = "SELECT `id`,`name`, `pass`,`login`,`freemiles`,`discount`,`surname`,`gender` FROM `client` WHERE `id` = ? ";

    public ClientSqlDao() throws SQLException {
    }

    @Override
    Client fillFieldsObject(ResultSet resultSet) throws SQLException {
        var client = new Client();
        setParam(client, resultSet);

        return client;
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
        List<Client> users = new ArrayList<>();
        Client user;
        while (resultSet.next()) {
            user = new Client();
            setParam(user, resultSet);
            users.add(user);
        }

        return users;
    }

    @Override
    void setParam(Client obj, ResultSet resultSet) throws SQLException {
    }


    @Override
    public void delete(Client obj) throws Exception {
        var id = obj.getId();
        deleteByString(DELETE, String.valueOf(id), getConnection());
    }

    @Override
    public void add(Client obj) throws Exception {
        defaultCreate(CREATE, getConnection(), obj);
    }

    @Override
    public Client get(int id) throws Exception {
        return null;
    }

    @Override
    public void addRange(List<Client> items) throws Exception {

    }

    @Override
    public List<Client> getAll() throws Exception {
        return null;
    }
}
