package ing.wxy.action;

import ing.wxy.model.Team;
import ing.wxy.model.Wish;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "teamDrawWish",urlPatterns = "/teamDrawWish")
public class TeamDrawWish extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id=Integer.parseInt(req.getParameter("id").toString());
        int all=Integer.parseInt(req.getParameter("all").toString());
        int limit=Integer.parseInt(req.getParameter("limit").toString());
        String tel=(String)req.getSession().getAttribute("user");
        Team team = Team.find(tel).get(0);
        String number = team.getMyDrawWish();
        String[] draws = number.split(",");
        List<Wish> wishs=new ArrayList<Wish>();
        for(int i=0;i<draws.length;i++)
        {
            if(draws[i].length()!=0)wishs.add(Wish.findOne(Integer.parseInt(draws[i])).get(0));
        }
        for(int i=0;i<limit;i++)wishs.add(new Wish());
        wishs.subList((id-1)*limit,id*limit);
        resp.setContentType("application/json");
        JSONArray jsonArray=JSONArray.fromObject(wishs);
        resp.getWriter().write(jsonArray.toString());
    }
}
