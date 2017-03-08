package ing.wxy.action;

import ing.wxy.model.Person;
import ing.wxy.util.Md5;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="personRegister",urlPatterns="/personRegister")
public class PersonRegister extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String date=new Date().toString();
		String tel = req.getParameter("tel");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		System.out.println(sex);
		String email = req.getParameter("email");
		String birthday = req.getParameter("birthday");
		String name=req.getParameter("name");
		Person person=new Person();
		person.setName(name);
		person.setDate(date);
		person.setBirthday(birthday);
		person.setEmail(email);
		person.setPassword(Md5.md5(password+date));
		person.setSex(sex);
		person.setTel(tel);
		int result=person.add();
		resp.getWriter().write(""+result);
	}
}
