package pl.coderslab.controller;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.DayMealRecipe;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo przypisanie id z sesji, a nie na sztywno!!!

        PlanDao pd = new PlanDao();
        RecipeDao rd = new RecipeDao();
        Plan plan = new Plan();
        plan = pd.lastPlan(1);
        int countPlan;
        countPlan = pd.countUsersPlans(1);
        int countRecipe;
        countRecipe = rd.countUsersRecipies(1);
        List <DayMealRecipe> list = new ArrayList<DayMealRecipe>();
        list = pd.printDashboardInfo(1);

        request.setAttribute("list", list);
        request.setAttribute("plan", plan);
        request.setAttribute("countPlan", countPlan);
        request.setAttribute("countRecipe", countRecipe);

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }
}
