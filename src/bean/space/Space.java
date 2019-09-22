package bean.space;

import bean.Characters.Client;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Space.
 */
public class Space {
    private static ArrayList<Table> freeTablesList = new ArrayList<Table>();
    private static HashMap<Table, Client> busyTablesList = new HashMap<Table, Client>();
    private static final int MAX_QUANTITY_OF_TABLES = 6;

    /**
     * Gets free tables list.
     *
     * @return the free tables list
     */
    public static ArrayList<Table> getFreeTablesList() {
        return freeTablesList;
    }

    /**
     * Sets free tables list.
     *
     * @param freeTablesList the free tables list
     */
    public static void setFreeTablesList(ArrayList<Table> freeTablesList) {
        Space.freeTablesList = freeTablesList;
    }

    /**
     * Gets busy tables list.
     *
     * @return the busy tables list
     */
    public static HashMap<Table, Client> getBusyTablesList() {
        return busyTablesList;
    }

    /**
     * Sets busy tables list.
     *
     * @param busyTablesList the busy tables list
     */
    public static void setBusyTablesList(HashMap<Table, Client> busyTablesList) {
        Space.busyTablesList = busyTablesList;
    }

    /**
     * Gets max quantity of tables.
     *
     * @return the max quantity of tables
     */
    public static int getMaxQuantityOfTables() {
        return MAX_QUANTITY_OF_TABLES;
    }
}
