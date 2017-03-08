package ing.wxy.action;

import ing.wxy.model.Wish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "changeActivityStatus",urlPatterns = "/changeActivityStatus")
public class ChangeActivityStatus extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String status=req.getParameter("status");
        String wishid=req.getParameter("wishid");
        int result= Wish.changeStatus(status,wishid);
        resp.getWriter().write(""+result);
    }
}
