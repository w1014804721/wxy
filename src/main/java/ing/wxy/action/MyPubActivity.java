package ing.wxy.action;

import ing.wxy.model.Activity;
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
 * Created by luck on 2016/5/24.
 */
@WebServlet(name="myPubActivity",urlPatterns="/myPubActivity")
public class MyPubActivity extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String tel=(String)req.getSession().getAttribute("user");
        Team team = Team.find(tel).get(0);
        int id=Integer.parseInt(req.getParameter("id").toString());
        int all=Integer.parseInt(req.getParameter("all").toString());
        int limit=Integer.parseInt(req.getParameter("limit").toString());
        List<Activity> activities=Activity.findMyActivity(team.getTel());
        for(int i=0;i<limit;i++)activities.add(new Activity());
        activities=activities.subList((id-1)*limit,id*limit);
        resp.setContentType("application/json");
        JSONArray jsonArray=JSONArray.fromObject(activities);
        resp.getWriter().write(jsonArray.toString());
    }
}
