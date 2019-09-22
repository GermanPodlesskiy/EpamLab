package bean.menu;

import java.util.Objects;

/**
 * The type Drink.
 */
public class Drink extends MenuItem {
    private int capacity;

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
     * @param name     the name
     * @param price    the price
     * @param capacity the capacity
     */
    public Drink(String name, int price, int capacity) {
        super(name, price);
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
