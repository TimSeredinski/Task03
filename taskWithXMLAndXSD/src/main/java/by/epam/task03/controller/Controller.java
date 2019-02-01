package by.epam.task03.controller;

import by.epam.task03.entity.Dish;
import by.epam.task03.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static by.epam.task03.dao.parser.DOM.parseWithDOM;
import static by.epam.task03.dao.parser.SAX.parseWithSAX;
import static by.epam.task03.dao.parser.StAX.parseWithStAX;

public class Controller extends HttpServlet {

    private final static Logger logger = Logger.getLogger(Controller.class);

    public Controller() {
    }

    public void init() {
        String realConfiguration = getServletContext().getRealPath("/config/log4j2.xml");
        new DOMConfigurator().doConfigure(realConfiguration, LogManager.getLoggerRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Controller's doPost() called");
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("local") == null) {
            request.getSession(true).setAttribute("local", new Locale("ru", "RU"));
        } else {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
        }
        request.getSession(true).setAttribute("req", request.getRequestURI());
        response.setContentType("text/html");
        List<Dish> dish;
        try {
            switch (request.getRequestURI()) {
                case "/SAXParser":
                    dish = parseWithSAX();
                    break;
                case "/StAXParser":
                    dish = parseWithStAX();
                    break;
                case "/DOMParser":
                    dish = parseWithDOM();
                    break;
                default:
                    dish = null;
                    break;
            }
        } catch (DaoException e) {
            dish = null;
            logger.error("DaoException", e);
        }
        Object obj = dish;
        request.setAttribute("myMenu", obj);
        request.getRequestDispatcher("/WEB-INF/jsp/dishes.jsp").forward(request, response);
    }
}
