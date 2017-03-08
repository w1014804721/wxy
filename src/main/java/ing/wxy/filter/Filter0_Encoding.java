package ing.wxy.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(filterName="encoding",urlPatterns="/*")
public class Filter0_Encoding implements Filter
{
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		System.out.println("encoding setting filter start!");
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}
	public void destroy() 
	{
		System.out.println("encoding setting filter stop!");
	}
}
