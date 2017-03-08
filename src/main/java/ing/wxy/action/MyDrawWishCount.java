package ing.wxy.action;

import ing.wxy.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/24.
 */
@WebServlet(name = "myDrawWishCount",urlPatterns = "/myDrawWishCount")
public class MyDrawWishCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String tel=req.getSession().getAttribute("user").toString();
        Person person=Person.find(tel).get(0);
        int myDrawWish=person.getMyDrawWish().split(",").length-1;
        resp.getWriter().write(myDrawWish+"");

    }
}
