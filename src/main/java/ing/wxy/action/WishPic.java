package ing.wxy.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;

/**
 * Created by 王镜鑫 on 2016/5/21.
 */
@WebServlet(name = "wishPic",urlPatterns = "/wishPic")
@MultipartConfig
public class WishPic extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            Part part = req.getPart("file");
            String path = req.getParameter("path");
            String root=req.getSession().getServletContext().getRealPath("/");
            if(path==null)path="";
            System.out.println(root);
            if (path.length() == 0) path = req.getSession().getAttribute("user").toString() + new Date().toString().replace(" ","_").replace(":","-");
            part.write(root+"wishpic/"  +path);
            resp.getWriter().write("wishpic/"+path);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
