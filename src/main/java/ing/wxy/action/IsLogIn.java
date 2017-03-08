package ing.wxy.action;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王镜鑫 on 2016/5/21.
 */
@WebServlet(name = "isLogIn",urlPatterns = "/isLogIn")
public class IsLogIn extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Map<String,String> result = new HashMap<String,String>();
        if(req.getSession().getAttribute("user")==null)
            result.put("name",null);
        else
        {
            result.put("name",req.getSession().getAttribute("name")+"");
        }
        if((req.getSession().getAttribute("type")+"").equals("person"))result.put("type","1");else result.put("type","2");
        resp.setContentType("application/json");
        JSONArray jsonArray=JSONArray.fromObject(result);
        resp.getWriter().write(jsonArray.toString());
    }
}