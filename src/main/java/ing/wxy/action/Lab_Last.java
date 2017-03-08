package ing.wxy.action;

import ing.wxy.model.Activity;
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
@WebServlet(name = "lab-last",urlPatterns = "/lab-last")
public class Lab_Last extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Activity> activities=Activity.getAll().subList(0,8);
        JSONArray jsonArray=JSONArray.fromObject(activities);
        resp.getWriter().write(jsonArray.toString());
    }
}
