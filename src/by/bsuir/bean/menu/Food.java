package by.bsuir.bean.menu;

import java.util.Objects;

/**
 * The type Food.
 */
public class Food extends MenuItem {
    private int weight;

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
     * @param name   the name
     * @param price  the price
     * @param weight the weight
     */
    public Food(String name, int price, int weight) {
        super(name, price);
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

        Food food = (Food) obj;

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
