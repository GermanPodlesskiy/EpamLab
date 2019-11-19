package by.bsuir.bean.characters;

import by.bsuir.bean.space.Table;

import java.util.Objects;

/**
 * The type Client.
 */
public class Client extends Human {
    private int id;
    private double money;
    private Table table;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(double money) {
        if (money < 0) {
            return;
        }

        this.money = money;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Instantiates a new Client.
     *
     * @param anInt           the an int
     * @param aDouble         the a double
     * @param string          the string
     * @param resultSetString the result set string
     */
    public Client(int anInt, double aDouble, String string, String resultSetString) {
    }

    /**
     * Instantiates a new Client.
     *
     * @param id the id
     */
    public Client(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Client.
     *
     * @param id        the id
     * @param money     the money
     * @param humanId   the human id
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Client(int id, double money, int humanId, String firstName, String lastName) {
        super(humanId, firstName, lastName);

        this.id = id;
        setMoney(money);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Client client = (Client) obj;
        return id == client.id &&
                money == client.money &&
                Objects.equals(table, client.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, money, table);
    }
}
