package ing.wxy.action;

import ing.wxy.model.Activity;
import ing.wxy.model.Wish;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
@WebServlet(name="activityMore",urlPatterns="/activityMore")
public class ActivityMore extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		resp.setContentType("application/json");
		String activityId = req.getParameter("activityId");
		List<Activity> activities = Activity.findOne(Integer.parseInt(activityId));
		JSONArray jsonArray=JSONArray.fromObject(activities);
		resp.getWriter().write(jsonArray.toString());
	}
}
