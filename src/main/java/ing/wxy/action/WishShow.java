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
@WebServlet(name="wishShow",urlPatterns="/wishShow")
public class WishShow extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id").toString());
		int all=Integer.parseInt(req.getParameter("all").toString());
		int limit=Integer.parseInt(req.getParameter("limit").toString());
		resp.setContentType("application/json");
		List<Wish> wishs= Wish.getAll();
		for(int i=0;i<limit;i++)wishs.add(new Wish());
		System.out.println(wishs);
		wishs=wishs.subList((id-1)*limit,id*limit);
		JSONArray jsonArray=JSONArray.fromObject(wishs);
		resp.getWriter().write(jsonArray.toString());
	}
}