package ing.wxy.action;

import ing.wxy.model.Person;
import ing.wxy.model.Wish;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="wishPub",urlPatterns="/wishPub")
public class WishPub extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		System.out.println("enter");
		String o=null;
		SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy.MM.dd  HH:mm");
		String date = formatter.format(new Date()).toString();
		String tel = (String) req.getSession().getAttribute("user");
		String sim=req.getParameter("simpleDes");
		String simpleDes =sim.length()<15?sim:sim.substring(0,15);
		String complexDes = req.getParameter("complexDes");
		String expectTime = req.getParameter("expectTime");
		String deadLine = req.getParameter("deadLine");
		String wishPic=req.getParameter("picSrc");
		int expectNum = Integer.parseInt(req.getParameter("expectNum"));
		String state = "0";
		Person person = Person.find(tel).get(0);
		String publisher = person.getName();
		String sex = person.getSex();
		String name = person.getName();
		int age=person.getAge();
		Wish wish = new Wish();
		wish.setComplexDes(complexDes);
		wish.setDate(date);
		wish.setDeadLine(deadLine);
		wish.setExpectNum(expectNum);
		wish.setPublisher(publisher);
		wish.setSex(sex);
		wish.setSimpleDes(simpleDes);
		wish.setExpectTime(expectTime);
		wish.setState(state);
		wish.setTel(tel);
		wish.setAge(age);
		wish.setWishpic(wishPic);
		int result = wish.add();
		resp.getWriter().write(""+result);
 	}
}
