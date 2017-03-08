package ing.wxy.action;

import ing.wxy.model.Team;
import ing.wxy.model.Activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="activityPub",urlPatterns="/activityPub")
public class ActivityPub  extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		SimpleDateFormat formatter   =   new SimpleDateFormat("yyyy.MM.dd  HH:mm");
		String date = formatter.format(new Date()).toString();
		String title = req.getParameter("title");
		String s= req.getParameter("simpleDes");
		String simpleDes =s.length()<15?s:s.substring(0,15);
		String complexDes = req.getParameter("complexDes");
		String deadLine = req.getParameter("expectTime");
		String path=req.getParameter("path");
		Team team = Team.find((String)req.getSession().getAttribute("user")).get(0);
		String tel = team.getTel();
		int status = 0;
		String publisher =team.getTeamName();
		int expectNum = Integer.parseInt(req.getParameter("expectNum"));
		Activity activity = new Activity();
		activity.setComplexDes(complexDes);
		activity.setDate(date);
		activity.setDeadLine(deadLine);
		activity.setExpectNum(expectNum);
		activity.setPublisher(publisher);
		activity.setSimpleDes(simpleDes);
		activity.setStatus(status);
		activity.setTel(tel);
		activity.setTitle(title);
		activity.setActivitypic(path);
		int result = activity.add();
		resp.getWriter().write(""+result);
		
	}
}
