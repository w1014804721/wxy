package ing.wxy.action;

import ing.wxy.model.Person;
import ing.wxy.util.Md5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name="personLogin",urlPatterns="/personLogin")
public class PersonLogIn extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String tel=req.getParameter("tel");
		List<Person> persons=Person.find(tel);
		if(persons==null||persons.size()==0||persons.size()>1)
		{
			resp.getWriter().write("2");
			return;
		}
		String password=req.getParameter("password");
		System.out.println("**"+password+"   "+tel);
		if(Md5.md5(password+persons.get(0).getDate()).equals(persons.get(0).getPassword()))
		{
			resp.getWriter().write("\""+persons.get(0).getName()+"\"");
			req.getSession().setAttribute("user",persons.get(0).getTel());
			req.getSession().setAttribute("type", "person");
			req.getSession().setAttribute("name",persons.get(0).getName());
		}else
		resp.getWriter().write("\"\"");
	}
}
