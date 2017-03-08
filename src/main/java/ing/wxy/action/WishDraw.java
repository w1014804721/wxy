package ing.wxy.action;

import ing.wxy.model.Activity;
import ing.wxy.model.Person;
import ing.wxy.model.Team;
import ing.wxy.model.Wish;
import net.sf.json.JSONArray;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="wishDraw",urlPatterns="/wishDraw")
public class WishDraw extends HttpServlet
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
	public void personDraw(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException //个人领取心愿
	{
		String user=req.getSession().getAttribute("user").toString();//当前登录的个人用户
		int id = Integer.parseInt(req.getParameter("wishId"));//需要领取的心愿id
		Wish wish=Wish.findOne(id).get(0);//需要领取的心愿信息
		if(!Person.canDrawWish(user.toString(),id))//该人是否可以领取心愿
		{
			resp.getWriter().write("-1");
			return;
		}
		int result=Wish.draw(id,1);
		if(result==2)
		{
			resp.getWriter().write("");
			return ;
		}
		Person.drawWish(user,id);//个人领取心愿
		Person person = Person.find(wish.getTel()).get(0);
		JSONArray jsonArray=JSONArray.fromObject(person);
		resp.getWriter().write(jsonArray.toString());

	}
	public void teamDraw(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException //团队领取心愿
	{
		String user=req.getSession().getAttribute("user").toString();//当前登录的团队用户
		int id = Integer.parseInt(req.getParameter("wishId"));//需要领取的心愿id
		int number=Integer.parseInt(req.getParameter("baomingrenshu"));
		Wish wish=Wish.findOne(id).get(0);//需要领取的心愿信息
		if(!Team.canDrawWish(user.toString(),id))//该团队是否可以领取心愿
		{
			resp.getWriter().write("-1");
			return;
		}
		int result=Wish.draw(id,number);//心愿被领取
		if(result==2)
		{
			resp.getWriter().write("");
			return ;
		}
		Team.drawWish(user,id);
		Person person = Person.find(wish.getTel()).get(0);
		JSONArray jsonArray=JSONArray.fromObject(person);
		resp.getWriter().write(jsonArray.toString());
	}
}
