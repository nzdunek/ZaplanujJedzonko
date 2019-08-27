package pl.coderslab.controller;

import com.mysql.jdbc.StringUtils;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrationPage")
public class RegistrationPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("firstName");
        String surname = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (!StringUtils.isNullOrEmpty(name) &&
                !StringUtils.isNullOrEmpty(surname) &&
                !StringUtils.isNullOrEmpty(email) &&
                !StringUtils.isNullOrEmpty(password)) {
            Admin admin = new Admin(name, surname, email, password, 0, 1);
            AdminDao adminDao = new AdminDao();
            adminDao.create(admin);
            response.sendRedirect("/login");
        } else {
            request.setAttribute("warning", "true");
            doGet(request, response);
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
