package ing.wxy.action;

import ing.wxy.model.Person;
import ing.wxy.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "teamMessChange",urlPatterns = "/teamMessChange")
public class TeamMessChange extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userId=req.getSession().getAttribute("user").toString();
        String userName=req.getParameter("username");
        int result= Team.changeName(userId,userName);
        resp.getWriter().write(""+result);
    }
}
