package ing.wxy.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ing.wxy.model.Activity;
import ing.wxy.model.Person;
import ing.wxy.model.Team;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ActShow
 */
@WebServlet(name="adminActShow",urlPatterns="/adminActShow")
public class AdminActShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminActShow() {
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
		System.out.println("enter the admin act show ");
		List<Activity> list= Activity.getAllA();
		JSONArray array = new JSONArray();
		if ((list!= null) && (list.size() >= 1)) {
			for (int i = 0; i < list.size(); i++) {
				Activity activity=list.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", activity.getActivityId());
				obj.put("publisher", activity.getPublisher());
				obj.put("pubTime", activity.getDate());
				
				Team team= Team.find(activity.getTel()).get(0);
				obj.put("pubName",team.getTeamName());//?
				obj.put("email",team.getEmail());
				obj.put("note",activity.getSimpleDes());
				array.add(obj);
			}
		} 
		JSONArray jsonArray=JSONArray.fromObject(array);
		System.out.println(jsonArray);
		response.getWriter().write(jsonArray.toString());
	}

}
