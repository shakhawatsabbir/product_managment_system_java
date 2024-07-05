package Main;

import java.security.MessageDigest;

public class Password {
	public static String passwordHash(String password)  {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			
			md.update(password.getBytes());
			byte[] rbt = md.digest();
			StringBuilder sb = new StringBuilder();
			
			for(byte b: rbt)
			{
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
}
