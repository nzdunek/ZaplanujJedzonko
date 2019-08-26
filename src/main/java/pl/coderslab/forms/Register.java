package pl.coderslab.forms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");





        String name = request.getParameter("name");
        String lastname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
       // String passwordrepeat = request.getParameter("passwordrepeat");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/registration.jsp");
    }


}
