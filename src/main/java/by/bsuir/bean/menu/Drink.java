package by.bsuir.bean.menu;

import java.util.Objects;

/**
 * The type Drink.
 */
public class Drink extends MenuItem {
    private int id;
    private int capacity;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Instantiates a new Drink.
     *
     * @param capacity the capacity
     */
    public Drink(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Instantiates a new Drink.
     *
     * @param id  the id
     * @param id1 the id 1
     */
    public Drink(int id, int id1) {
        super(id);

        this.id = id1;
    }

    /**
     * Instantiates a new Drink.
     *
     * @param id         the id
     * @param menuItemId the menu item id
     * @param name       the name
     * @param price      the price
     * @param capacity   the capacity
     */
    public Drink(int id, int menuItemId, String name, int price, int capacity) {
        super(menuItemId, name, price);
        this.capacity = capacity;
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

        Drink drink = (Drink) obj;

        return capacity == drink.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "capacity=" + capacity +
                '}';
    }
}
