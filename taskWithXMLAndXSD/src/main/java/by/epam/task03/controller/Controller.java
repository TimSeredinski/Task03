package by.epam.task03.controller;

import by.epam.task03.entity.Dish;

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

    public Controller() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        if (request.getParameter("local") == null) {
            request.getSession(true).setAttribute("local", new Locale("ru", "RU"));
        } else {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
        }
        request.getSession(true).setAttribute("req", request.getRequestURI());
        response.setContentType("text/html");
        List<Dish> dish;
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
        Object obj = dish;
        request.setAttribute("myMenu", obj);
        request.getRequestDispatcher("/WEB-INF/jsp/dishes.jsp").forward(request, response);
    }
}
