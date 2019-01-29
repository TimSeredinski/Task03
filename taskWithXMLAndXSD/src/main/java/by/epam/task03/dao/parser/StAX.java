package by.epam.task03.dao.parser;

import by.epam.task03.entity.Dish;
import by.epam.task03.entity.MenuTagName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StAX {

    //private static final Logger logger = LogManager.getLogger(StAX.class.getName());

    public static List<Dish> parseWithStAX() {
        //logger.info("StAX is working");
        System.out.println(StAX.class.getName());
        URL resource = StAX.class.getResource("/menu.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        List<Dish> menu = null;
        try {
            InputStream input = new FileInputStream(resource.toURI().getPath());
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            menu = process(reader);
        } catch (XMLStreamException|URISyntaxException|FileNotFoundException e) {
            e.printStackTrace();
        }
        return menu;
    }

    private static List<Dish> process(XMLStreamReader reader) throws XMLStreamException {
        List<Dish> dishList = new ArrayList<>();
        Dish dish = null;
        MenuTagName tagName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String qName = reader.getLocalName();
                    tagName = MenuTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
                    if (qName.equals("Cold-Dish") || qName.equals("Warm-Dish") || qName.equals("Breakfast")) {
                        dish = new Dish();
                        dish.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (tagName) {
                        case NAME:
                            dish.setName(text);
                            break;
                        case IMG:
                            dish.setImg(text);
                            break;
                        case DESCRIPTION:
                            dish.setDescription(text);
                            break;
                        case PORTION:
                            dish.setPortion(text);
                            break;
                        case PRICE:
                            dish.setPrice(Double.parseDouble(text));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    qName = reader.getLocalName();
                    if (qName.equals("Cold-Dish") || qName.equals("Warm-Dish") || qName.equals("Breakfast")) {
                        dishList.add(dish);
                    }
            }
        }
        return dishList;
    }
}
