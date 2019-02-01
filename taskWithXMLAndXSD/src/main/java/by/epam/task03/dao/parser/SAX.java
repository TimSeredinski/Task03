package by.epam.task03.dao.parser;

import by.epam.task03.dao.parser.handler.MenuSaxHandler;
import by.epam.task03.entity.Dish;
import by.epam.task03.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class SAX {

    private static final Logger logger = LogManager.getLogger(SAX.class.getName());

    public static List<Dish> parseWithSAX() throws DaoException {
        logger.info("SAX");
        MenuSaxHandler handler = new MenuSaxHandler();
        try {
            URL resource = SAX.class.getResource("/menu.xml");
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(resource.toURI().getPath()));
        } catch (SAXException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        List<Dish> menu = handler.getDishList();
        return menu;
    }
}
