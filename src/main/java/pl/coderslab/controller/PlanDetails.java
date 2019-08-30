package pl.coderslab.controller;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayMealRecipe;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao pd = new PlanDao();
        Admin admin = (Admin) session.getAttribute("users");
        String name = admin.getFirstName();
        int id = Integer.parseInt(request.getParameter("id"));

        Plan plan = pd.read(id);
        List<DayMealRecipe> main = pd.allPlansRecipies(id);
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
        request.setAttribute("plan", plan);


        getServletContext().getRequestDispatcher("/adminPlanDetails.jsp")
                .forward(request, response);
    }
}
