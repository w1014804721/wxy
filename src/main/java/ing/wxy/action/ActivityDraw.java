package ing.wxy.action;

import java.io.IOException;
import ing.wxy.model.Activity;
import ing.wxy.model.Person;
import ing.wxy.model.Team;
import ing.wxy.model.Wish;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="activityDraw",urlPatterns="/activityDraw")
public class ActivityDraw extends HttpServlet//招募领取
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String tel=req.getSession().getAttribute("user").toString();
		if(!req.getSession().getAttribute("type").toString().equals("team"))
		{
			personDraw(req,resp);
		}else
		{
			teamDraw(req,resp);
		}
	}
	private void teamDraw(HttpServletRequest req, HttpServletResponse resp)throws IOException//团队领取招募
	{
		String user=req.getSession().getAttribute("user").toString();//登陆的团队的信息
		int number=Integer.parseInt(req.getParameter("baomingrenshu"));//团队可以报名的人数
		System.out.println(req.getParameter("activityId"+"***"));
		int id = Integer.parseInt(req.getParameter("activityId"));//招募的id
		Activity activity=Activity.findOne(id).get(0);//招募的具体信息
		if(!Team.canDrawAct(user.toString(),id))//团队是否可以领取招募
		{
			resp.getWriter().write("-1");
			return;
		}
		int result=Activity.draw(id,number);//招募被领取
		if(result==2)
		{
			resp.getWriter().write("");
			return ;
		}
		Team.drawAct(user,id);//团队领取招募
		Team team = Team.find(activity.getTel()).get(0);
		JSONArray jsonArray=JSONArray.fromObject(team);
		resp.getWriter().write(jsonArray.toString());
	}
	public void personDraw(HttpServletRequest req, HttpServletResponse resp)throws IOException//个人领取招募
	{
		String user=req.getSession().getAttribute("user").toString();//当前登陆的个人
		int id = Integer.parseInt(req.getParameter("activityId"));//招募的id
		Activity activity=Activity.findOne(id).get(0);//招募的信息
		if(!Person.canDrawAct(user.toString(),id))//个人是否可以领取招募
		{
			resp.getWriter().write("-1");
			return;
		}
		int result=Activity.draw(id,1);//招募被领取
		if(result==2)
		{
			resp.getWriter().write("");
			return ;
		}
		Person.drawAct(user,id);//个人领取招募
		Team team = Team.find(activity.getTel()).get(0);
		JSONArray jsonArray=JSONArray.fromObject(team);
		resp.getWriter().write(jsonArray.toString());
	}
}
