package ing.wxy.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
public class Md5
{
	public static String md5(String s) 
	{   
		MessageDigest md5=null;
		String newstr=null;
		try    
		{
			md5 = MessageDigest.getInstance("md5");
			BASE64Encoder base64en = new BASE64Encoder();
			newstr=base64en.encode(md5.digest(s.getBytes("utf-8")));
		}catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
        return newstr;
	}
}