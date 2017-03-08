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
@WebServlet(name="activityShow",urlPatterns="/activityShow")
public class ActivityShow extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id").toString());
		int all=Integer.parseInt(req.getParameter("all").toString());
		int limit=Integer.parseInt(req.getParameter("limit").toString());
		resp.setContentType("application/json");
		List<Activity> activities= Activity.getAll();
		for(int i=0;i<limit;i++)activities.add(new Activity());
		activities = activities.subList((id-1)*limit,id*limit);
		JSONArray jsonArray=JSONArray.fromObject(activities);
		resp.getWriter().write(jsonArray.toString());
	}
}
