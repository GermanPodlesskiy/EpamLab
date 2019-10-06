package by.bsuir.bean.characters;

import java.util.Objects;

/**
 * The type Administator.
 */
public class Administator extends Human {

    private int id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Instantiates a new Administator.
     */
    public Administator() {
    }

    /**
     * Instantiates a new Administator.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param id        the id
     */
    public Administator(String firstName, String lastName, int id) {
        super(firstName, lastName);
        this.id = id;
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

        Administator that = (Administator) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Administator{" +
                "id=" + id +
                '}';
    }
}
