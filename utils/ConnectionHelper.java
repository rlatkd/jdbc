package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionHelper {
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		try {
			  if(dsn.equals("oracle")) {
				  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
			  }else if(dsn.equals("mariadb")) {
				  conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb","KOSA","1004");
			  }
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public static Connection getConnection(String dsn , String id , String pwd) {
		Connection conn = null;
		try {
			  if(dsn.equals("oracle")) {
				  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", id, pwd);
			  }else if(dsn.equals("mariadb")) {
				  conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb",id,pwd);
			  }
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				 conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				 rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}