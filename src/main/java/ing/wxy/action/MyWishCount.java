package ing.wxy.action;

import ing.wxy.model.Wish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/24.
 */
@WebServlet(name = "myWishCount",urlPatterns = "/myWishCount")
public class MyWishCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("create button");
        int count= Wish.findMyCount(req.getSession().getAttribute("user").toString());
        resp.getWriter().write(""+count);
    }
}
