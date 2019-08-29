package pl.coderslab.controller;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        AdminDao adminDao = new AdminDao();
        Admin userValidate = adminDao.authorization(admin);

        if (userValidate.equals(admin)) {
            session.setAttribute("users", admin);
            response.sendRedirect("/app/dashboard");
        } else {
            request.setAttribute("warning2", "true");
            doGet(request, response);
        }
        if (session.getAttribute("users") == admin) {
            System.out.println("Połączono z sesją");
        } else {
            System.out.println("Nie połączono z sesją");
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
