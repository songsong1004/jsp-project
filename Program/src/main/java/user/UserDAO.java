package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost:3306/web";
			String dbID = "root";
			String dbPassword = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public int login(String userid, String userpassword) {
		String SQL = "SELECT userpassword FROM user WHERE userid = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getNString(1).contentEquals(userpassword)) {
					return 1;
				}
				return 0;
			}
			return -1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int signUp (User user) {
		String SQL = "INSERT INTO user VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getUserpassword());
			pstmt.setString(3, user.getUsername());
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
