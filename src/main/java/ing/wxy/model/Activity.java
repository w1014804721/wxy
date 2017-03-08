package ing.wxy.model;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Activity 
{
	private static QueryRunner qr = new TxQueryRunner();
	private String date = null;
	private String title = null;
	private String simpleDes = null;
	private String complexDes = null;
	private int status = 0;
	private String publisher = null;
	private int activityId;
	private int expectNum;
	private String deadLine = null;
	private String tel = null;
	private String activitypic=null;

	public String getActivitypic() {
		return activitypic;
	}

	public void setActivitypic(String activitypic) {
		this.activitypic = activitypic;
	}

	public String getDate()
	{
		return date;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
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
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public String getPublisher() 
	{
		return publisher;
	}
	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}
	public int getActivityId() 
	{
		return activityId;
	}
	public void setActivityId(int activityId) 
	{
		this.activityId = activityId;
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
	public String getTel() 
	{
		return tel;  
	}
	public void setTel(String tel)  
	{
		this.tel = tel;
	}
	public int add()
	{
		String sql = "insert into activity (activityId,date,title,simpleDes,complexDes,status,publisher,expectNum,deadLine,tel,activitypic) values (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {activityId,date,title,simpleDes,complexDes,status,publisher,expectNum,deadLine,tel,activitypic};
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
	public static List<Activity> findOne(int activityId)
	{
		String sql = "select * from activity where activityId =?";
		List<Activity> activities=null;
		try 
		{
			activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class), activityId);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return activities;
	}
	public static List<Activity> getAll()
	{
		String sql = "select * from activity where showit=0 order by date desc";
		List<Activity> activities = null;
		try 
		{
			activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return activities;
	}
	public static List<Activity> getAllA()
	{
		String sql = "select * from activity";
		List<Activity> activities = null;
		try
		{
			activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return activities;
	}
	public static int draw(int activityId,int volunteerAmount) 
	{
		String sql = "select * from activity where activityId=? ";
		try 
		{
			List<Activity> activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class), activityId);
			int after = activities.get(0).getStatus()+volunteerAmount; 
			if(after>activities.get(0).getExpectNum())
			{
				return 2;//如果人超过上限则报名错误
			}
			sql = "update activity set status="+after+" where activityId=?";
			qr.update(sql,activityId);
			sql = "select * from activity where activityId=? ";
			activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class), activityId);
			if(activities.get(0).getStatus()==activities.get(0).getExpectNum())
			{
				sql="update activity set showit=1 where activityId=?";
				System.out.println(sql);
				qr.update(sql,activityId);
			}
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return 2;
		}
	}
	public static List<Activity> findMyActivity(String user)
	{
		String sql = "select * from activity where tel=?";
		List<Activity> activities=null;
		try
		{
			activities = qr.query(sql, new BeanListHandler<Activity>(Activity.class),user);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return activities;
	}
	public static int delete(int actId) {
		try {
			String sql = "delete from activity where activityId=? ";
			qr.update(sql, actId);
			return 1;
		} catch (SQLException e) {
			System.out.println("wrong");
			e.printStackTrace();
			return 2;
		}
	}
}
