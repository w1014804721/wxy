package ing.wxy.action;

import ing.wxy.model.Activity;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 17854 on 2016/8/18.
 */
@WebServlet(name = "indexActivity",urlPatterns = "/indexActivity")
public class IndexActivity extends HttpServlet
{
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                resp.setCharacterEncoding("utf-8");
                List<Activity> wishList = Activity.getAll();
                List<Activity> wishes = wishList.size()>=4?wishList.subList(0,4):wishList;
                JSONArray jsonArray=JSONArray.fromObject(wishes);
                System.out.println(jsonArray);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonArray.toString());
            }
}
