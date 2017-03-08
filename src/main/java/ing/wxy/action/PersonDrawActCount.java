package ing.wxy.action;

import ing.wxy.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "personDrawActCount",urlPatterns = "/personDrawActCount")
public class PersonDrawActCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Person person= Person.find(req.getSession().getAttribute("user").toString()).get(0);
        int count=person.getMyDrawAct().split(",").length-1;
        resp.getWriter().write(""+count);
    }
}
