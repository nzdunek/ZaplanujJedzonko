package pl.coderslab.controller;

import com.mysql.jdbc.StringUtils;
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

@WebServlet("/app/recipe/add")
public class AddRecipe extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");

        Admin admin1 = new Admin(admin.getId());

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparation_time = Integer.parseInt(request.getParameter("preparation_time"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");


        if (!StringUtils.isNullOrEmpty(name) &&
                !StringUtils.isNullOrEmpty(ingredients) &&
                !StringUtils.isNullOrEmpty(description) &&
                !StringUtils.isNullOrEmpty(ingredients) &&
                !StringUtils.isNullOrEmpty(String.valueOf(preparation_time)) &&
                !StringUtils.isNullOrEmpty(preparation)) {
            Recipe recipe = new Recipe(name, ingredients, description, preparation_time, preparation, admin1);
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.create(recipe);
            response.sendRedirect("/app/recipe/add");
        } else {
            request.setAttribute("warning", "true");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/addrecipe.jsp").forward(request, response);
    }
}
