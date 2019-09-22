package bean.Characters;

import bean.menu.MenuItem;
import bean.space.Table;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Client.
 */
public class Client extends Human {
    private int id;
    private int money;
    private ArrayList<MenuItem> chosenMenuItems = new ArrayList<MenuItem>();
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
    public int getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets chosen bean.menu items.
     *
     * @return the chosen bean.menu items
     */
    public ArrayList<MenuItem> getChosenMenuItems() {
        return chosenMenuItems;
    }

    /**
     * Sets chosen bean.menu items.
     *
     * @param chosenMenuItems the chosen bean.menu items
     */
    public void setChosenMenuItems(ArrayList<MenuItem> chosenMenuItems) {
        this.chosenMenuItems = chosenMenuItems;
    }

    /**
     * Instantiates a new Client.
     */
    public Client() {
    }

    /**
     * Instantiates a new Client.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param id        the id
     * @param money     the money
     */
    public Client(String firstName, String lastName, int id, int money) {
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
