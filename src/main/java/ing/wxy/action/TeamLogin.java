package ing.wxy.action;

import ing.wxy.model.Team;
import ing.wxy.util.Md5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name="teamLogin",urlPatterns="/teamLogin")
public class TeamLogin extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String tel=req.getParameter("tel");
		List<Team> teams=Team.find(tel);
		if(teams==null||teams.size()>1)
		{
			resp.getWriter().write("2");
			return;
		}
		String password=req.getParameter("password");
		if(Md5.md5(password+teams.get(0).getDate()).equals(teams.get(0).getPassword()))
		{
			System.out.println(teams.get(0).getTeamName());
			req.getSession().setAttribute("user", teams.get(0).getTel());
			req.getSession().setAttribute("type", "team");
			req.getSession().setAttribute("name",teams.get(0).getTeamName());
			resp.getWriter().write("\""+teams.get(0).getTeamName()+"\"");
		}
		else
		resp.getWriter().write("");
	}
}
