package pl.coderslab.controller;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");
        int id = Integer.parseInt(request.getParameter("id"));
        RecipeDao rd = new RecipeDao();

        Recipe recipe = rd.read(id);
        String name = admin.getFirstName();

        request.setAttribute("name", name);
        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/appRecipeDetails.jsp")
                .forward(request, response);



    }
}
