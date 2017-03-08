package ing.wxy.action;

import ing.wxy.model.Activity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/24.
 */
@WebServlet(name = "myActivityCount",urlPatterns = "/myActivityCount")
public class MyActivityCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int count= Activity.findMyActivity(req.getSession().getAttribute("user").toString()).size();
        resp.getWriter().write(""+count);
    }
}
