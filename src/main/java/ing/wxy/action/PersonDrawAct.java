package ing.wxy.action;

import ing.wxy.model.Activity;
import ing.wxy.model.Person;
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
@WebServlet(name = "personDrawAct",urlPatterns = "/personDrawAct")
public class PersonDrawAct extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id=Integer.parseInt(req.getParameter("id").toString());
        int all=Integer.parseInt(req.getParameter("all").toString());
        int limit=Integer.parseInt(req.getParameter("limit").toString());
        String tel=(String)req.getSession().getAttribute("user");
        Person person = Person.find(tel).get(0);
        String number = person.getMyDrawAct();
        String[] draws = number.split(",");
        List<Activity> activities=new ArrayList<Activity>();
        for(int i=0;i<draws.length;i++)
        {
            try{
            List<Activity> activiti = Activity.findOne(Integer.parseInt(draws[i]));
            if(activiti.size()>0&&draws[i].length()!=0)activities.add(activiti.get(0));
            }
            catch (Exception e){continue;}

        }
        for(int i=0;i<limit;i++)activities.add(new Activity());
        activities.subList((id-1)*limit,id*limit);
        resp.setContentType("application/json");
        JSONArray jsonArray=JSONArray.fromObject(activities);
        resp.getWriter().write(jsonArray.toString());
    }
}
