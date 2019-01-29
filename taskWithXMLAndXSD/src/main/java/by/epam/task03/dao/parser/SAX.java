package by.epam.task03.dao.parser;

import by.epam.task03.dao.parser.handler.MenuSaxHandler;
import by.epam.task03.entity.Dish;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SAX {

    //private static final Logger logger = LogManager.getLogger(SAX.class.getName());

    public static List<Dish> parseWithSAX() {
        //logger.info("SAX is working");
        System.out.println(SAX.class.getName());
        URL resource = SAX.class.getResource("/menu.xml");
        MenuSaxHandler handler = new MenuSaxHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(resource.toURI().getPath()));
        }
        catch (SAXException|URISyntaxException|IOException e){
            e.printStackTrace();
        }
        List<Dish> menu = handler.getDishList();
        return menu;
        }
    }
