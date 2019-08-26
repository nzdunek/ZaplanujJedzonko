package pl.coderslab.controller;

import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
// adres servletu niezmieniony - przy wstawieniu "/" nie implementuje się styl
public class ServletLanding extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
