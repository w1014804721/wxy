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
@WebServlet(name = "changeWishStatus",urlPatterns = "/changeWishStatus")
public class ChangeWishStatus extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String status=req.getParameter("status");
        String sta;
        if(status.equals("未领取"))sta="0"; else
        if(status.equals("进行中"))sta="1";  else
        sta="2";
        String wishid=req.getParameter("wishid");
        int result=Wish.changeStatus(sta,wishid);
        resp.getWriter().write(""+result);
    }
}
