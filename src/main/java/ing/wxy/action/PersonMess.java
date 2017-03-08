package ing.wxy.action;
import ing.wxy.model.Person;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 王镜鑫 on 2016/5/21.
 */
@WebServlet(name = "personMess",urlPatterns = "/personMess")
public class PersonMess extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("123456789");
        String tel=req.getSession().getAttribute("user").toString();
        List<Person> person=Person.find(tel);
        JSONArray jsonArray=JSONArray.fromObject(person);
        resp.getWriter().write(jsonArray.toString());
    }
}
