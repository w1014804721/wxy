package ing.wxy.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

/**
 * Created by luck on 2016/5/25.
 */
@WebServlet(name = "activityPic",urlPatterns = "/activityPic")
@MultipartConfig
public class ActivityPic extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            Part part = req.getPart("file");
            String path = req.getParameter("path");
            String root=req.getSession().getServletContext().getRealPath("/");
            if(path==null)path="";
            if (path.length() == 0) path = req.getSession().getAttribute("user").toString() + new Date().toString().replace(" ","_").replace(":","-");
            part.write(root+"activitypic/"+path);
            resp.getWriter().write("activitypic/"+path);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
