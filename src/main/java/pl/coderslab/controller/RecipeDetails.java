package pl.coderslab.controller;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.RecursiveAction;

@WebServlet("/recipeDetails")
public class RecipeDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao rd = new RecipeDao();
        int id = Integer.parseInt(request.getParameter("id"));

        Recipe recipe = rd.read(id);

        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/recipe-details.jsp")
                .forward(request, response);
    }
}
