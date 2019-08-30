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
import java.util.List;

@WebServlet("/app/recipe/list")
public class AppRecipeList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");

        int id = admin.getId();
        String name = admin.getFirstName();

        RecipeDao rd = new RecipeDao();

        List<Recipe> list = rd.findAllRecipesByUser(id);
        request.setAttribute("name", name);
        request.setAttribute("list", list);

        getServletContext().getRequestDispatcher("/appRecipeList.jsp")
                .forward(request, response);
    }
}
