package ing.wxy.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ing.wxy.model.Person;
import ing.wxy.model.Wish;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class WishShow
 */
@WebServlet(name="adminWishShow",urlPatterns="/adminWishShow")
public class AdminWishShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminWishShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Wish> list= Wish.getAllW();
		JSONArray array = new JSONArray();
		if ((list!= null) && (list.size() >= 1)) {
			for (int i = 0; i < list.size(); i++) {
				Wish wish=list.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", wish.getWishId());
				obj.put("publisher", wish.getPublisher());//?
				obj.put("pubTime", wish.getDate());
				
				Person person= Person.find(wish.getTel()).get(0);
				obj.put("pubName",person.getName());//?
				obj.put("email",person.getEmail());
				obj.put("note",wish.getSimpleDes());


				array.add(obj);
			}
		} 
		JSONArray json=new JSONArray().fromObject(array);
		System.out.println(json);
		response.getWriter().write(json.toString());
	}

}
