package by.bsuir.parser;

import by.bsuir.bean.characters.Client;
import by.bsuir.bean.menu.MenuItem;
import by.bsuir.bean.space.Table;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client xml parser.
 */
public class ClientXmlParser implements XmlParser<Client> {
    private static final String CLIENT = "client";
    private static final String CLIENTS = "clients";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first-name";
    private static final String LAST_NAME = "last-name";
    private static final String MONEY = "money";
    private static final String TABLE = "table";
    private static final String TABLE_NUMBER = "number";
    private static final String TABLE_IS_FREE = "is-free";
    private static final String CHOSEN_MENU_ITEMS = "chosen-menu-items";
    private static final String MENU_ITEM = "menu-item";
    private static final String MENU_ITEM_NAME = "name";
    private static final String MENU_ITEM_PRICE = "price";

    private DocumentBuilder documentBuilder;
    private String path;

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Instantiates a new Client xml parser.
     */
    public ClientXmlParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Instantiates a new Client xml parser.
     *
     * @param path the path
     */
    public ClientXmlParser(String path) {
        this();
        this.path = path;
    }

    @Override
    public List<Client> getData() throws XmlParserException {
        var file = new File(path);
        if (!file.exists()) {
            throw new XmlParserException(path + ": file not exists.");
        }

        List<Client> clients = new ArrayList<>();
        Document document;


        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            throw new XmlParserException(e.getMessage());
        }

        var element = document.getDocumentElement();
        var nodeClients = element.getElementsByTagName(CLIENT);

        for (var i = 0; i < nodeClients.getLength(); i++) {
            if (nodeClients.item(i).getNodeType() == Node.ELEMENT_NODE) {
                clients.add(getClientElement((Element) nodeClients.item(i)));
            }
        }

        return clients;
    }

    @Override
    public void setData(List<Client> clients) throws XmlParserException {
        var document = documentBuilder.newDocument();
        var root = document.createElement(CLIENTS);
        document.appendChild(root);

        for (var client : clients) {
            root.appendChild(getClientElement(document, client));
        }

        var impl = document.getImplementation();
        var implLs = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        var lsSerializer = implLs.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("format-pretty-print", true);
        var output = implLs.createLSOutput();
        output.setEncoding("UTF-8");

        try {
            output.setByteStream(Files.newOutputStream(Paths.get(path)));
        } catch (IOException e) {
            throw new XmlParserException(e.getMessage());
        }

        lsSerializer.write(document, output);
    }

    private Client getClientElement(Element element) {
        Client client;

        try {
            client = new Client(Integer.parseInt(getElementTextContent(element, ID)));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + ID + "'" + "incorrect");
        }

        try {
            client.setMoney(Integer.parseInt(getElementTextContent(element, MONEY)));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + MONEY + "'" + "incorrect");
        }

        client.setFirstName(getElementTextContent(element, FIRST_NAME));
        client.setLastName(getElementTextContent(element, LAST_NAME));

        var tableElement = (Element) element.getElementsByTagName(TABLE).item(0);
        if (tableElement != null) {
            client.setTable(getTableElement(tableElement));
        }

        var chosenMenuItemsElement = (Element) element.getElementsByTagName(CHOSEN_MENU_ITEMS).item(0);
        if (chosenMenuItemsElement != null) {
            client.setChosenMenuItems(getMenuItems(chosenMenuItemsElement));
        }

        return client;
    }

    private static String getElementTextContent(Element element, String elementName) {
        var nList = element.getElementsByTagName(elementName);
        var node = nList.item(0);
        return node.getTextContent();
    }

    private Table getTableElement(Element element) {
        var table = new Table();

        try {
            table.setFree(Boolean.parseBoolean(getElementTextContent(element, TABLE_IS_FREE)));
        } catch (Exception ex) {
            throw new IllegalArgumentException("'" + TABLE_IS_FREE + "'" + "item incorrect.");
        }

        try {
            table.setNumber(Integer.parseInt(getElementTextContent(element, TABLE_NUMBER)));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + TABLE_NUMBER + "'" + "incorrect");
        }

        return table;
    }

    private List<MenuItem> getMenuItems(Element element) {
        var nodeMenuItems = element.getElementsByTagName(MENU_ITEM);
        List<MenuItem> menuItems = new ArrayList<>();

        for (var i = 0; i < nodeMenuItems.getLength(); i++) {
            if (nodeMenuItems.item(i).getNodeType() == Node.ELEMENT_NODE) {
                menuItems.add(getMenuItem((Element) nodeMenuItems.item(i)));
            }
        }

        return menuItems;
    }

    private MenuItem getMenuItem(Element element) {
        var menuItem = new MenuItem();

        menuItem.setName(getElementTextContent(element, MENU_ITEM_NAME));

        try {
            menuItem.setPrice(Integer.parseInt(getElementTextContent(element, MENU_ITEM_PRICE)));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + MENU_ITEM_PRICE + "'" + "incorrect");
        }

        return menuItem;
    }

    private Element getClientElement(Document document, Client client) {
        var clientElement = document.createElement(CLIENT);

        var idElement = document.createElement(ID);
        idElement.appendChild(document.createTextNode(Integer.toString(client.getId())));

        var firstNameElement = document.createElement(FIRST_NAME);
        firstNameElement.appendChild(document.createTextNode(client.getFirstName()));

        var lastNameElement = document.createElement(LAST_NAME);
        lastNameElement.appendChild(document.createTextNode(client.getLastName()));

        var moneyElement = document.createElement(MONEY);
        moneyElement.appendChild(document.createTextNode(Double.toString(client.getMoney())));


        clientElement.appendChild(idElement);
        clientElement.appendChild(firstNameElement);
        clientElement.appendChild(lastNameElement);
        clientElement.appendChild(moneyElement);

        if (client.getTable() != null) {
            clientElement.appendChild(getTableElement(document, client.getTable()));
        }

        if (client.getChosenMenuItems() != null || !client.getChosenMenuItems().isEmpty()) {
            clientElement.appendChild(getChosenMenuItemsElement(document, client.getChosenMenuItems()));
        }

        return clientElement;
    }

    private Element getTableElement(Document document, Table table) {
        var tableElement = document.createElement(TABLE);

        var numberElement = document.createElement(TABLE_NUMBER);
        numberElement.appendChild(document.createTextNode(Integer.toString(table.getNumber())));

        var isFreeElement = document.createElement(TABLE_IS_FREE);
        isFreeElement.appendChild(document.createTextNode(Boolean.toString(table.getIsFree())));

        tableElement.appendChild(numberElement);
        tableElement.appendChild(isFreeElement);

        return tableElement;
    }

    private Element getChosenMenuItemsElement(Document document, List<MenuItem> menuItems) {
        var menuItemsElement = document.createElement(CHOSEN_MENU_ITEMS);

        for (MenuItem menuItem : menuItems) {
            menuItemsElement.appendChild(getMenuItemElement(document, menuItem));
        }

        return menuItemsElement;
    }

    private Element getMenuItemElement(Document document, MenuItem menuItem) {
        var menuItemElement = document.createElement(MENU_ITEM);

        var nameElement = document.createElement(MENU_ITEM_NAME);
        nameElement.appendChild(document.createTextNode(menuItem.getName()));

        var priceElement = document.createElement(MENU_ITEM_PRICE);
        priceElement.appendChild(document.createTextNode(Integer.toString(menuItem.getPrice())));

        menuItemElement.appendChild(nameElement);
        menuItemElement.appendChild(priceElement);

        return menuItemElement;
    }
}
