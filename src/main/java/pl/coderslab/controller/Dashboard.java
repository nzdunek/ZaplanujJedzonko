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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {

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

        List<DayMealRecipe> main = pd.printDashboardInfo(id);
        List<DayMealRecipe> pon = pd.printList(main, id, "poniedziałek");
        List<DayMealRecipe> wt = pd.printList(main, id, "wtorek");
        List<DayMealRecipe> sr = pd.printList(main, id, "środa");
        List<DayMealRecipe> czw = pd.printList(main, id, "czwartek");
        List<DayMealRecipe> pt = pd.printList(main, id, "piątek");
        List<DayMealRecipe> sb = pd.printList(main, id, "sobota");
        List<DayMealRecipe> nd = pd.printList(main, id, "niedziela");

        request.setAttribute("pon", pon);
        request.setAttribute("wt", wt);
        request.setAttribute("sr", sr);
        request.setAttribute("czw", czw);
        request.setAttribute("pt", pt);
        request.setAttribute("sb", sb);
        request.setAttribute("nd", nd);
        request.setAttribute("name", name);
        request.setAttribute("countPlan", countPlan);
        request.setAttribute("countRecipe", countRecipe);

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }
}
