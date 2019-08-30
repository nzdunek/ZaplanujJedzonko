package pl.coderslab.controller;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddPlanMealRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameMeal = request.getParameter("mealName");
        int numberMeal = Integer.parseInt(request.getParameter("numberMeal"));



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");

        int id = admin.getId();
        String name = admin.getFirstName();

        PlanDao planDao = new PlanDao();
        List<Plan> plans = planDao.findAllUserPlans(id);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAllRecipesByUser(id);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayNames = dayNameDao.findAll();


        request.setAttribute("dayNames", dayNames);
        request.setAttribute("plans", plans);
        request.setAttribute("recipes", recipes);
        request.setAttribute("name", name);

        getServletContext().getRequestDispatcher("/addPlanMealRecipe.jsp")
                .forward(request, response);

    }
}
