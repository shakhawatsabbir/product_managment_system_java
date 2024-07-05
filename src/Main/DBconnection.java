package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {

	static 	Connection con = null;
	
	static 	String   DATABASE = "emonProject",
			URL = "jdbc:mysql://localhost:3306/"+DATABASE, 
			USERNAME = "root",
	 		PASSWORD = "";
	 
	public static Connection connection() throws Exception {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
		return con;	
	} 
	 
	public static PreparedStatement studentInsart() throws Exception {
		
		PreparedStatement student = connection().prepareStatement("insert into students (name, email, password) values(?,?,?)");
		return student;
	}
	 
		public static PreparedStatement studentInsartDetail() throws Exception {
			
			PreparedStatement student = connection().prepareStatement("insert into students (name,mobile,gendar, email, password) values(?,?,?,?,?)");
			return student;
		}
	 
	public static PreparedStatement studentLogin() throws Exception {
					
		PreparedStatement student = connection().prepareStatement("select *from  students where email=?");
		
		return student;
	}
	
	public static PreparedStatement courseInsert() throws Exception {
		
		PreparedStatement course =   connection().prepareStatement("insert into courses (title, code, price, credit_houre) values(?,?,?,?)");
		return course;
	}
	
	
	public static PreparedStatement courseUpdate() throws Exception {
		
		PreparedStatement course = con.prepareStatement("update courses set title=? , code=?, price=?, credit_houre=? where id=?");

		return course;
	}
	
	public static PreparedStatement courseDelete() throws Exception {
		
		PreparedStatement course = con.prepareStatement("delete from courses where id=?");

		return course;
	}
	
	public static int totalCourses() throws Exception {
		
		PreparedStatement course =   connection().prepareStatement("select count(*) from courses");
		ResultSet rs = course.executeQuery();
		rs.next();
	    int count = rs.getInt(1);
		return count;
	}
	
}
