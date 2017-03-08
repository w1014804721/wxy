package ing.wxy.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(name="adminLogin",urlPatterns="/adminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		
		String adminId=request.getParameter("adminId");
		String adminPass=request.getParameter("adminPass");
		System.out.println(adminId);
		System.out.println(adminPass);
		 
		JSONObject json=new JSONObject();
		if(adminId.equals("wxyAdmin")&&adminPass.equals("wxyAdmin")){
			json.put("errorCode", 0);
			json.put("msg", "success");
			request.getSession().setAttribute("adminId",adminId);
		}else{
			json.put("errorCode", 1);
			json.put("msg", "用户名或密码错误");
		}
		System.out.println(json);
		response.getWriter().write(json.toString());
	}

}
