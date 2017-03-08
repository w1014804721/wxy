package ing.wxy.action;
import ing.wxy.model.Team;
import net.sf.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "teamMess",urlPatterns = "/teamMess")
public class TeamMess extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("123456789");
        String tel=req.getSession().getAttribute("user").toString();
        List<Team> team=Team.find(tel);
        JSONArray jsonArray=JSONArray.fromObject(team);
        resp.getWriter().write(jsonArray.toString());
    }
}
