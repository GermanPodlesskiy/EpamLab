package by.bsuir.bean.menu;

import java.util.Objects;

/**
 * The type Food.
 */
public class Food extends MenuItem {
    private int id;
    private int weight;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Instantiates a new Food.
     */
    public Food() {
    }

    /**
     * Instantiates a new Food.
     *
     * @param id  the id
     * @param id1 the id 1
     */
    public Food(int id, int id1) {
        super(id);
        this.id = id1;
    }

    /**
     * Instantiates a new Food.
     *
     * @param id         the id
     * @param menuItemId the menu item id
     * @param name       the name
     * @param price      the price
     * @param weight     the weight
     */
    public Food(int id, int menuItemId, String name, double price, int weight) {
        super(menuItemId,name, price);
        this.weight = weight;
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

        var food = (Food) obj;

        return weight == food.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public String toString() {
        return "Food{" +
                "weight=" + weight +
                '}';
    }
}
