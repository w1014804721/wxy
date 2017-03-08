package ing.wxy.action;

import ing.wxy.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 17854 on 2016/8/23.
 */
@WebServlet(name = "teamDrawActCount",urlPatterns = "/teamDrawActCount")
public class TeamDrawActConut extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Team person= Team.find(req.getSession().getAttribute("user").toString()).get(0);
        int count=person.getMyDrawAct().split(",").length-1;
        resp.getWriter().write(""+count);
    }
}
