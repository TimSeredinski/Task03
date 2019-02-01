package by.epam.task03.dao.parser;

import by.epam.task03.entity.Dish;
import by.epam.task03.exception.DaoException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DOM {

    private static final Logger logger = LogManager.getLogger(DOM.class.getName());

    public static List<Dish> parseWithDOM() throws DaoException {
        logger.info("DOM is working");
        System.out.println(DOM.class.getName());
        List<Dish> menu = new ArrayList<>();
        DOM dom = new DOM();
        DOMParser parser = new DOMParser();
        try {
            URL resource = DOM.class.getResource("/menu.xml");
            parser.parse(new InputSource(resource.toURI().getPath()));

            Document document = parser.getDocument();
            Element root = document.getDocumentElement();

            NodeList coldDishNodes = root.getElementsByTagName("Cold-Dish");
            NodeList warmDishNodes = root.getElementsByTagName("Warm-Dish");
            NodeList breakfastNodes = root.getElementsByTagName("Breakfast");
            menu = dom.addDishWithNodeList(menu, coldDishNodes);
            menu = dom.addDishWithNodeList(menu, warmDishNodes);
            menu = dom.addDishWithNodeList(menu, breakfastNodes);
        } catch (SAXException | URISyntaxException | IOException e) {
            throw new DaoException("Exception in DOMParser");
        }

        return menu;
    }

    private List<Dish> addDishWithNodeList(List<Dish> menu, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Dish dish = new Dish();
            Element dishElement = (Element) nodeList.item(i);
            dish.setId(Integer.parseInt(dishElement.getAttribute("id")));
            dish.setImg(getSingleChild(dishElement, "img").getTextContent().trim());
            dish.setName(getSingleChild(dishElement, "name").getTextContent().trim());
            dish.setDescription(getSingleChild(dishElement, "description").getTextContent().trim());
            dish.setPortion(getSingleChild(dishElement, "portion").getTextContent().trim());
            dish.setPrice(Double.parseDouble(getSingleChild(dishElement, "price").getTextContent().trim()));
            menu.add(dish);
        }
        return menu;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }
}
