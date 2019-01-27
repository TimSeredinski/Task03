package by.epam.task03.controller;

import by.epam.task03.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.task03.dao.parser.SAX.parseWithSAX;

public class Controller extends HttpServlet {

    public Controller() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        List<Dish> dish = parseWithSAX();
        Object obj = dish;
        request.setAttribute("myMenu", obj);
        if("menu".equals(request.getParameter("command"))){
            request.getRequestDispatcher("/WEB-INF/jsp/dishes.jsp").forward(request, response);
        }
    }
}
