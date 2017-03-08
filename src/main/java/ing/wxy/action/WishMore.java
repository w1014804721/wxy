package ing.wxy.action;
import ing.wxy.model.Wish;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
@WebServlet(name="wishMore",urlPatterns="/wishMore")
public class WishMore extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		resp.setContentType("application/json");
		String wishId = req.getParameter("wishId");
		System.out.println(wishId);
		List<Wish> wishs = Wish.findOne(Integer.parseInt(wishId));
		JSONArray jsonArray=JSONArray.fromObject(wishs);
		System.out.println(jsonArray.toString());
		resp.getWriter().write(jsonArray.toString());
	}
}
