package pl.coderslab.controller;

import com.mysql.jdbc.StringUtils;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/add")
public class AddPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("users");

        Admin admin1 = new Admin(admin.getId());
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (!StringUtils.isNullOrEmpty(name) &&
                !StringUtils.isNullOrEmpty(description)) {
            Plan plan = new Plan(name, description, admin1);
            PlanDao pd = new PlanDao();
            pd.create(plan);
            response.sendRedirect("/app/plan/list");
        } else {
            request.setAttribute("warning", "true");
            doGet(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/addPlan.jsp").forward(request, response);
    }
}
