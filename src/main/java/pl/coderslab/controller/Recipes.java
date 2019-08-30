package pl.coderslab.controller;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recipes")
public class Recipes extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao rd = new RecipeDao();

        List<Recipe> recipes = rd.findAll();

        request.setAttribute("recipes", recipes);

        getServletContext().getRequestDispatcher("/recipes.jsp")
                .forward(request, response);
    }
}
