package ing.wxy.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ing.wxy.model.Person;
/**
 * Created by 王镜鑫 on 2016/5/21.
 */
@WebServlet(name = "personMessChange",urlPatterns = "/personMessChange")
public class PersonMessChange extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userId=req.getSession().getAttribute("user").toString();
        String userName=req.getParameter("userName");
        int result=Person.changeName(userId,userName);
        resp.getWriter().write(""+result);
    }
}
