package parser;

import bean.Characters.Client;
import bean.menu.MenuItem;
import bean.space.Table;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The type Client xml parser.
 */
public class ClientXmlParser implements XmlParser<Client> {
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
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
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
    public ArrayList<Client> getData() throws Exception {
        File file = new File(path);
        ArrayList<Client> clients = new ArrayList<>();

        Document document = documentBuilder.parse(file);
        Element element = document.getDocumentElement();
        NodeList nodeClients = element.getElementsByTagName("client");

        for (int i = 0; i < nodeClients.getLength(); i++) {
            if (nodeClients.item(i).getNodeType() == Node.ELEMENT_NODE) {
                clients.add(getClientElement((Element) nodeClients.item(i)));
            }
        }

        return clients;
    }

    @Override
    public void setData(ArrayList<Client> clients) throws Exception {
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("clients");
        document.appendChild(root);

        for (Client client : clients) {
            root.appendChild(getClientElement(document, client));
        }

        DOMImplementation impl = document.getImplementation();
        DOMImplementationLS implLs = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        LSSerializer lsSerializer = implLs.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("format-pretty-print", true);
        LSOutput output = implLs.createLSOutput();
        output.setEncoding("UTF-8");
        output.setByteStream(Files.newOutputStream(Paths.get(path)));
        lsSerializer.write(document, output);
    }

    private Client getClientElement(Element element) {
        Client client;

        try {
            client = new Client(Integer.parseInt(getElementTextContent(element, "id")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'id' incorrect");
        }

        try {
            client.setMoney(Integer.parseInt(getElementTextContent(element, "money")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'money' incorrect");
        }

        client.setFirstName(getElementTextContent(element, "first-name"));
        client.setLastName(getElementTextContent(element, "last-name"));

        Element tableElement = (Element) element.getElementsByTagName("table").item(0);
        if (tableElement != null) {
            client.setTable(getTableElement(tableElement));
        }

        Element chosenMenuItemsElement = (Element) element.getElementsByTagName("chosen-menu-items").item(0);
        if (chosenMenuItemsElement != null) {
            client.setChosenMenuItems(getMenuItems(chosenMenuItemsElement));
        }

        return client;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private Table getTableElement(Element element) {
        Table table = new Table();

        try {
            table.setFree(Boolean.parseBoolean(getElementTextContent(element, "is-free")));
        } catch (Exception ex) {
            throw new IllegalArgumentException("'is-free' item incorrect.");
        }

        try {
            table.setNumber(Integer.parseInt(getElementTextContent(element, "number")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'number' incorrect");
        }

        return table;
    }

    private ArrayList<MenuItem> getMenuItems(Element element) {
        NodeList nodeMenuItems = element.getElementsByTagName("menu-item");
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (int i = 0; i < nodeMenuItems.getLength(); i++) {
            if (nodeMenuItems.item(i).getNodeType() == Node.ELEMENT_NODE) {
                menuItems.add(getMenuItem((Element) nodeMenuItems.item(i)));
            }
        }

        return menuItems;
    }

    private MenuItem getMenuItem(Element element) {
        MenuItem menuItem = new MenuItem();

        menuItem.setName(getElementTextContent(element, "name"));

        try {
            menuItem.setPrice(Integer.parseInt(getElementTextContent(element, "price")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'price' incorrect");
        }

        return menuItem;
    }

    private Element getClientElement(Document document, Client client) {
        Element clientElement = document.createElement("client");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(Integer.toString(client.getId())));

        Element firstNameElement = document.createElement("first-name");
        firstNameElement.appendChild(document.createTextNode(client.getFirstName()));

        Element lastNameElement = document.createElement("last-name");
        lastNameElement.appendChild(document.createTextNode(client.getLastName()));

        Element moneyElement = document.createElement("money");
        moneyElement.appendChild(document.createTextNode(Integer.toString(client.getMoney())));


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
        Element tableElement = document.createElement("table");

        Element numberElement = document.createElement("number");
        numberElement.appendChild(document.createTextNode(Integer.toString(table.getNumber())));

        Element isFreeElement = document.createElement("is-free");
        isFreeElement.appendChild(document.createTextNode(Boolean.toString(table.getIsFree())));

        tableElement.appendChild(numberElement);
        tableElement.appendChild(isFreeElement);

        return tableElement;
    }

    private Element getChosenMenuItemsElement(Document document, ArrayList<MenuItem> menuItems) {
        Element menuItemsElement = document.createElement("chosen-menu-items");

        for (MenuItem menuItem : menuItems) {
            menuItemsElement.appendChild(getMenuItemElement(document, menuItem));
        }

        return menuItemsElement;
    }

    private Element getMenuItemElement(Document document, MenuItem menuItem) {
        Element menuItemElement = document.createElement("menu-item");

        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(menuItem.getName()));

        Element priceElement = document.createElement("price");
        priceElement.appendChild(document.createTextNode(Integer.toString(menuItem.getPrice())));

        menuItemElement.appendChild(nameElement);
        menuItemElement.appendChild(priceElement);

        return menuItemElement;
    }
}
