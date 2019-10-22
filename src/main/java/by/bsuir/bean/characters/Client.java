package by.bsuir.bean.characters;

import by.bsuir.bean.menu.MenuItem;
import by.bsuir.bean.space.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Client.
 */
public class Client extends Human {
    private int id;
    private double money;
    private List<MenuItem> chosenMenuItems = new ArrayList<>();
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
     * Gets chosen bean.menu items.
     *
     * @return the chosen bean.menu items
     */
    public List<MenuItem> getChosenMenuItems() {
        return chosenMenuItems;
    }

    /**
     * Sets chosen bean.menu items.
     *
     * @param chosenMenuItems the chosen bean.menu items
     */
    public void setChosenMenuItems(List<MenuItem> chosenMenuItems) {
        this.chosenMenuItems = chosenMenuItems;
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
     */
    public Client() {
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
     * @param firstName the first name
     * @param lastName  the last name
     * @param money     the money
     */
    public Client(int id, String firstName, String lastName, double money) {
        super(firstName, lastName);
        this.id = id;
        this.money = money;
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
                Objects.equals(chosenMenuItems, client.chosenMenuItems) &&
                Objects.equals(table, client.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, money, chosenMenuItems, table);
    }
}
