package by.bsuir.bean.menu;

import java.util.Objects;

/**
 * The type Menu item.
 */
public class MenuItem {
    private int menuItemId;
    private double price;
    private String name;

    /**
     * Gets menu item id.
     *
     * @return the menu item id
     */
    public int getMenuItemId() {
        return menuItemId;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Menu item.
     */
    public MenuItem() {
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param id the id
     */
    public MenuItem(int id) {
        this.menuItemId = id;
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param id    the id
     * @param name  the name
     * @param price the price
     */
    public MenuItem(int id, String name, double price) {
        this(id);

        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var menuItem = (MenuItem) obj;
        return price == menuItem.price &&
                Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
