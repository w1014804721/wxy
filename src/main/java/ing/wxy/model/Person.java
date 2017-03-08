package ing.wxy.model;
import cn.itcast.jdbc.TxQueryRunner;
import ing.wxy.util.ParseAge;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
public class Person
{
	private static QueryRunner qr = new TxQueryRunner();
	private int age;
	private String tel=null;
	private String password=null;
	private String sex=null;
	private String birthday=null;
	private String email=null;
	private String date=null;
	private String name=null;
	private String myDrawWish=",";
	private String myDrawAct=",";
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getMyDrawWish()
	{
		return myDrawWish;
	}
	public void setMyDrawWish(String myDrawWish)
	{
		this.myDrawWish = myDrawWish;
	}

	public String getMyDrawAct() {
		return myDrawAct;
	}

	public void setMyDrawAct(String myDrawAct) {
		this.myDrawAct = myDrawAct;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
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
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	public int add()
	{
		System.out.println(tel);
		String sql = "insert into person (tel,password,sex,birthday,email,date,name,myDrawWish,myDrawAct) values (?,?,?,?,?,?,?,?,?)";
		Object[] params = {tel,password,sex,birthday,email,date,name,myDrawWish,myDrawAct};
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
	public static List<Person> find(String tel)
	{
		String sql = "select * from person where tel =?";
		List<Person> persons=null;
		try
		{
			persons = qr.query(sql, new BeanListHandler<Person>(Person.class), tel);
			persons.get(0).setAge(ParseAge.getAge(new SimpleDateFormat("yyyy-MM-dd").parse(persons.get(0).getBirthday()))   );
		} catch (SQLException e)
		{
			e.printStackTrace();
		}catch (ParseException e2)
		{
			e2.printStackTrace();
		}
		return persons;
	}
	public static int changeName(String userId,String userName)
	{
        try
        {
			System.out.println(userName+"   "+userId);
			String sql = "update person set name=\"" + userName + "\" where tel=?";
            qr.update(sql, userId);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return 2;
        }
        return 1;
	}
	public static void drawWish(String user, int id)//个人领取心愿
	{
		String s = "\""+id+","+"\"";
		try
		{
			String sql="update person set myDrawWish=concat(myDrawWish,"+s+")where tel=?";
			System.out.println("sql:"+sql);
			qr.update(sql,user);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void drawAct(String user,int id)//个人领取招募
	{
		String s = "\""+id+","+"\"";
		try
		{
			String sql="update person set myDrawAct=concat(myDrawWish,"+s+")where tel=?";
			System.out.println("sql:"+sql);
			qr.update(sql,user);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public String toString()
	{
		return tel+"  "+password+"  "+date;
	}

	public static boolean canDrawWish(String user, int id)//个人是否可以领取该心愿
	{
		String sql="select * from person where tel=?";
		try
		{
			Person person=qr.query(sql, new BeanListHandler<Person>(Person.class), user).get(0);
			if(person.getMyDrawWish().contains(","+id+","))return false;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return  true;
	}
	public static boolean canDrawAct(String user, int id)//个人是否可以领取该招募
	{
		String sql="select * from person where tel=?";
		try
		{
			Person person=qr.query(sql, new BeanListHandler<Person>(Person.class), user).get(0);
			if(person.getMyDrawAct().contains(","+id+","))return false;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return  true;
	}
}
