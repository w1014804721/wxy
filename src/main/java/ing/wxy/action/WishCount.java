package ing.wxy.action;

import ing.wxy.model.Wish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "wishCount",urlPatterns = "/wishCount")
public class WishCount extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int count=Wish.getAll().size();
        resp.getWriter().write(""+count);
    }
}
