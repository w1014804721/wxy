package ing.wxy.model;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Wish 
{
	private static QueryRunner qr = new TxQueryRunner();
	private int wishId;
	private String simpleDes=null;
	private String complexDes=null;
	private String state=null;
	private String publisher=null;
	private String date=null;
	private String tel=null;
	private String expectTime=null;
	private int expectNum=0;
	private String deadLine=null;
	private String sex=null;
	private String wishpic=null;
	private int age=0;
	private String showit="0";
	public String getShowit()
	{
		return showit;
	}
	public void setShowit(String showit)
	{
		this.showit = showit;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getWishpic()
	{
		return wishpic;
	}
	public void setWishpic(String wishpic)
	{
		this.wishpic = wishpic;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
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
	public String getExpectTime() 
	{
		return expectTime;
	}
	public void setExpectTime(String expectTime) 
	{
		this.expectTime = expectTime;
	}
	public int getExpectNum() 
	{
		return expectNum;
	}
	public void setExpectNum(int expectNum) 
	{
		this.expectNum = expectNum;
	}
	public String getDeadLine() 
	{
		return deadLine;
	}
	public void setDeadLine(String deadLine) 
	{
		this.deadLine = deadLine;
	}
	public int getWishId() 
	{
		return wishId;
	}
	public void setWishId(int wishId) 
	{
		this.wishId = wishId;
	}
	public String getSimpleDes() 
	{
		return simpleDes;
	}
	public void setSimpleDes(String simpleDes) 
	{
		this.simpleDes = simpleDes;
	}
	public String getComplexDes() 
	{
		return complexDes;
	}
	public void setComplexDes(String complexDes)
	{
		this.complexDes = complexDes;
	}
	public String getState() 
	{
		return state;
	}
	public void setState(String state) 
	{
		this.state = state;
	}
	public String getPublisher() 
	{
		return publisher;
	}
	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;  
	}  
	public int add()
	{
		String sql = "insert into wish (wishId,simpleDes,complexDes,state,publisher,date,tel,expectTime,expectNum,deadLine,sex,wishpic,age) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {wishId,simpleDes,complexDes,state,publisher,date,tel,expectTime,expectNum,deadLine,sex,wishpic,age};
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
	public static List<Wish> findOne(int wishId)
	{
		String sql = "select * from wish where wishId =?";
		List<Wish> wishs=null;
		try 
		{
			wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class), wishId);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return wishs;
	}
	public static List<Wish> getAll()
	{
		String sql = "select * from wish  where showit='0' order by date desc";
		List<Wish> wishs=null;
		try 
		{
			wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("**"+wishs);
		return wishs;
	}
	public static List<Wish> getAllW()
	{
		String sql = "select * from wish";
		List<Wish> wishs=null;
		try
		{
			wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("**"+wishs);
		return wishs;
	}
	public static int draw(int wishId,int number)
	{
		String sql = "select * from wish where wishId=?";
		try 
		{
			System.out.println("132456");
			List<Wish> wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class),wishId);
			int after = Integer.parseInt(wishs.get(0).getState())+number;
			if(after>wishs.get(0).getExpectNum())
			{
				return 2;//如果人超过上限则报名错误
			}
			sql = "update wish set state="+after+" where wishId=?";
			qr.update(sql,wishId);
			sql="select * from wish where wishId=?";
			wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class),wishId);
			if(wishs.get(0).getState().equals(wishs.get(0).getExpectNum()+""))
			{
				sql="update wish set showit='进行中' where wishId=?";
				System.out.println(sql);
				qr.update(sql,wishId);
			}
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return 2;
		}
	}
	public static int findMyCount(String user)
	{
		List<Wish> count=null;
		try {
			String sql = "select * from wish where tel=?";
			count = qr.query(sql, new BeanListHandler<Wish>(Wish.class),user);
			return count.size();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return count.size();
	}

	public static List<Wish> getMyWish(String tel)
	{
		String sql = "select * from wish where tel=?";
		List<Wish> wishs=null;
		try
		{
			wishs = qr.query(sql, new BeanListHandler<Wish>(Wish.class),tel);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return wishs;
	}

	public static int changeStatus(String status, String wishid)
	{
		try
		{
			String sql="update wish set showit=? where wishId=?";
			qr.update(sql,status,wishid);
		}catch (SQLException e)
		{
			e.printStackTrace();
			return 2;
		}
		return 1;
	}
	public static int delete(int weishId) {
		try {
			String sql = "delete from wish where wishId=? ";
			qr.update(sql, weishId);
			return 1;
		} catch (SQLException e) {
			System.out.println("wrong");
			e.printStackTrace();
			return 2;
		}
	}
}
