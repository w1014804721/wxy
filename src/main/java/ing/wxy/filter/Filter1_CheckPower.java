package ing.wxy.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName="checkPower",urlPatterns="/*")
public class Filter1_CheckPower implements Filter
{
	public static final String[] intercept=
			{"activityDraw","activityPub","myDrawWish","myPubWish","wishDraw","wishPub","userPerson"};
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("permissions validation filter start!");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		System.out.println(path + "     " + req.getSession().getAttribute("user"));
		String type=(String)req.getSession().getAttribute("type");
		if(type==null)type="";
		if(type.equals("person")&&path.equals("/userTeam.html")||type.equals("team")&&path.equals("/userPerson.html"))
		{
			((HttpServletResponse)response).sendRedirect("index.html");
			return;
		}
		if (req.getSession().getAttribute("user") != null)
		{
			chain.doFilter(req, response);
		}else
		{
			if(skip(path))
			{
				((HttpServletResponse)response).sendRedirect("index.html");
			}else
				chain.doFilter(req, response);
		}
	}
	public boolean skip(String str)
	{
		if(str.equals("/"))return false;
		for(int i=0;i<intercept.length;i++)
		{
			if(str.contains(intercept[i]))return true;
		}
		return false;
	}
	public void destroy()
	{
		System.out.println("permissions validation filter stop!");
	}
}