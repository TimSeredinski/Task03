package by.epam.task03.parser.handler;

import by.epam.task03.entity.Dish;
import by.epam.task03.entity.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler {
    private List<Dish> dishList = new ArrayList<>();
    private Dish dish;
    private StringBuilder text;

    public List<Dish> getDishList() {
        return dishList;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName
                + ", qName: " + qName);
        text = new StringBuilder();
        if (qName.equals("Cold-Dish") || qName.equals("Warm-Dish") || qName.equals("Breakfast")) {
            dish = new Dish();
            dish.setId((Integer.parseInt(attributes.getValue("id"))));
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
            case NAME:
                dish.setName(text.toString());
                break;
            case IMG:
                dish.setImg(text.toString());
                break;
            case DESCRIPTION:
                dish.setDescription(text.toString());
                break;
            case PORTION:
                dish.setPortion(text.toString());
                break;
            case PRICE:
                dish.setPrice(Double.parseDouble(text.toString()));
                break;
            case COLD_DISH:
                dishList.add(dish);
                dish = null;
                break;
            case WARM_DISH:
                dishList.add(dish);
                dish = null;
                break;
            case BREAKFAST:
                dishList.add(dish);
                dish = null;
                break;
        }
    }
}
