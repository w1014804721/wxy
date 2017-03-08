package ing.wxy.action;

import ing.wxy.model.Person;
import ing.wxy.model.Wish;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
	@WebServlet(name="myPubWish",urlPatterns="/myPubWish")
public class MyPubWish extends HttpServlet //返回自己发布的心愿
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{

		String tel=(String)req.getSession().getAttribute("user");
		Person person = Person.find(tel).get(0);
		int id=Integer.parseInt(req.getParameter("id").toString());
		int all=Integer.parseInt(req.getParameter("all").toString());
		int limit=Integer.parseInt(req.getParameter("limit").toString());
		List<Wish> wishs=Wish.getMyWish(person.getTel());
		for(int i=0;i<limit;i++)wishs.add(new Wish());
		wishs=wishs.subList((id-1)*limit,id*limit);
		resp.setContentType("application/json");
		JSONArray jsonArray=JSONArray.fromObject(wishs);
		resp.getWriter().write(jsonArray.toString());
	}
}
