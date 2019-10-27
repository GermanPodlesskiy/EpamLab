package by.bsuir.bean.characters;

import java.util.Objects;

/**
 * The type Human.
 */
public class Human {
    private int humanId;
    private String firstName;
    private String lastName;

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets human id.
     *
     * @return the human id
     */
    public int getHumanId() {
        return humanId;
    }

    /**
     * Instantiates a new Human.
     */
    public Human() {
    }

    /**
     * Instantiates a new Human.
     *
     * @param id the id
     */
    public Human(int id) {
        this.humanId = id;
    }

    /**
     * Instantiates a new Human.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new Human.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Human(int id, String firstName, String lastName) {
        this(id);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var human = (Human) obj;

        return Objects.equals(getFirstName(), human.getFirstName()) &&
                Objects.equals(getLastName(), human.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
