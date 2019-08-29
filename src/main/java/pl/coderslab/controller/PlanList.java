package pl.coderslab.controller;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayMealRecipe;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlanList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");
        int id = admin.getId();
        String name = admin.getFirstName();

        PlanDao pd = new PlanDao();
        RecipeDao rd = new RecipeDao();
        int countPlan;
        countPlan = pd.countUsersPlans(id);
        int countRecipe;
        countRecipe = rd.countUsersRecipies(id);

        List<Plan> list = pd.findAllUserPlans(id);

        request.setAttribute("list", list);
        request.setAttribute("name", name);
        request.setAttribute("countRecipe", countRecipe);
        request.setAttribute("countPlan", countPlan);

        getServletContext().getRequestDispatcher("/adminplans.jsp")
                .forward(request, response);
    }
}
