package ing.wxy.action;

import ing.wxy.model.Wish;
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
@WebServlet(name = "wishes-last",urlPatterns = "/wishes-last")
public class Wishes_Last extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Wish> wishes=Wish.getAll().subList(0,4);
        JSONArray jsonArray=JSONArray.fromObject(wishes);
        resp.getWriter().write(jsonArray.toString());
    }
}
