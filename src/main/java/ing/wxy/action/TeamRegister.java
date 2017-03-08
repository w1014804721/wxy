package ing.wxy.action;


import ing.wxy.model.*;
import ing.wxy.util.Md5;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="teamRegister",urlPatterns="/teamRegister")
public class TeamRegister extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		System.out.println("1345689");
		String date = new Date().toString();
		String tel = req.getParameter("tel");
		String password = req.getParameter("password");
		String teamName = req.getParameter("teamName");
		String city = req.getParameter("city");
		String email = req.getParameter("email");
		Team team=new Team();
		team.setDate(date);
		team.setCity(city);
		team.setEmail(email);
		team.setPassword(Md5.md5(password+date));
		team.setTeamName(teamName);
		team.setTel(tel);
		int result=team.add();
		resp.getWriter().write(""+result);
	}
}
