package by.epam.task03.parser;

import by.epam.task03.entity.Dish;
import by.epam.task03.parser.handler.MenuSaxHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class SAX {
    public static void main(String[] args) throws SAXException, IOException, URISyntaxException {
        URL resource = SAX.class.getResource("/menu.xml");
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(resource.toURI().getPath()));

        List<Dish> menu = handler.getDishList();
        for (Dish dish : menu){
            System.out.println(dish);
        }
    }
}
