package ing.wxy.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class Team 
{
	private static QueryRunner qr = new TxQueryRunner();
	private String date=null; 
	private String tel=null;  
	private String password=null;
	private String teamName=null;
	private String city=null;
	private String email=null;
	private String myDrawWish=null;
	private String myDrawAct=null;
	public String getMyDrawWish() {
		return myDrawWish;
	}

	public void setMyDrawWish(String myDrawWish) {
		this.myDrawWish = myDrawWish;
	}

	public String getMyDrawAct() {
		return myDrawAct;
	}

	public void setMyDrawAct(String myDrawAct) {
		this.myDrawAct = myDrawAct;
	}

	public String getDate()
	{
		return date;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}
	public String getTel() 
	{
		return tel;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getTeamName() 
	{
		return teamName;
	}
	public void setTeamName(String teamName) 
	{
		this.teamName = teamName;
	}
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public int add()
	{
		String sql = "insert into team (tel,password,teamName,email,city,date) values (?,?,?,?,?,?)";
		Object[] params = {tel,password,teamName,email,city,date};
		System.out.println(params+"***");
		try 
		{
			qr.update(sql, params);
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return 2;
		}
	}
	public static List<Team> find(String tel)
	{
		String sql = "select * from team where tel =?";
		List<Team> teams=null;
		try 
		{
			teams = qr.query(sql, new BeanListHandler<Team>(Team.class), tel);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return teams;
	}
	public String toString()
	{
		return tel+"  "+password+"  "+date;
	}
	public static void drawWish(String user,int id)//团队领取心愿
	{
		String s = "\""+id+","+"\"";
		try
		{
			String sql="update team set myDrawWish=concat(myDrawWish,"+s+")where tel=?";
			System.out.println("sql:"+sql);
			qr.update(sql,user);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void drawAct(String user, int id)//团队领取招募
	{
		String s = "\""+id+","+"\"";
		try
		{
			String sql="update team set myDrawAct=concat(myDrawWish,"+s+")where tel=?";
			System.out.println("sql:"+sql);
			qr.update(sql,user);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static boolean canDrawAct(String user, int id)//团队是否可以领取该招募
	{
		String sql="select * from team where tel=?";
		try
		{
			Team team=qr.query(sql, new BeanListHandler<Team>(Team.class), user).get(0);
			if(team.getMyDrawAct().contains(","+id+","))return false;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return  true;
	}
	public static boolean canDrawWish(String user, int id)//团队是否可以领取该心愿
	{
		String sql="select * from team where tel=?";
		try
		{
			Team team=qr.query(sql, new BeanListHandler<Team>(Team.class), user).get(0);
			if(team.getMyDrawWish().contains(","+id+","))return false;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return  true;
	}

	public static int changeName(String userId, String userName)
	{
		try
		{
			System.out.println(userName+"   "+userId);
			String sql = "update team set teamName=\"" + userName + "\" where tel=?";
			qr.update(sql, userId);
		}catch (SQLException e)
		{
			e.printStackTrace();
			return 2;
		}
		return 1;
	}
}
