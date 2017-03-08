package ing.wxy.action;
import ing.wxy.model.Team;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/24.
 */
@WebServlet(name = "myDrawActivityCount" ,urlPatterns = "/myDrawActivityCount")
public class MyDrawActivityCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String tel=req.getSession().getAttribute("user").toString();
        Team team=Team.find(tel).get(0);
        System.out.println(team.getMyDrawAct()+"***");
        int myDrawActivity=team.getMyDrawAct().split(",").length-1;
        resp.getWriter().write(myDrawActivity+"");

    }
}
