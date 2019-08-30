package pl.coderslab.controller;
import pl.coderslab.dao.PlanDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class PlanDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao pd = new PlanDao();
        int id = Integer.parseInt(request.getParameter("id"));
        pd.delete(id);

        response.sendRedirect("/app/plan/list");
    }
}
