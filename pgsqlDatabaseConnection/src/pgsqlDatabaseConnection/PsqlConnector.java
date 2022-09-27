//$Id$
package pgsqlDatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class PsqlConnector {
		
	    private final static String url = "jdbc:postgresql://localhost:5432/demo_db";
	    private final static String user = "postgres";
	    private final static String password = "postgres";

	    @SuppressWarnings("resource")
	    public static void main(String args[]){
		String SQL = "INSERT INTO users (username,email,age,firstname,lastname,location,gender,phoneno) VALUES(?,?,?,?,?,?,?,?)";
	        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {

	            pstmt.setString(1, "spidey");
	            pstmt.setString(2, "marvel@gmail.com");
	            pstmt.setInt(3, 20);
	            pstmt.setString(4, "peter");
	            pstmt.setString(5, "parker");
	            pstmt.setString(6, "New york");
	            pstmt.setString(7, "male");
	            pstmt.setString(8, "9934736726");

	            int affectedRows = pstmt.executeUpdate();
	            System.out.println(affectedRows);
	            delete();
			
	        } 
	        catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	    }

	  public static Connection connect() throws SQLException {
		 return DriverManager.getConnection(url, user, password);
	  }
		
	  public static int delete() {
	         String SQL = "DELETE FROM users WHERE username= ? ";
	   	 try (Connection conn = connect();  PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
		        	@SuppressWarnings("resource")
				Scanner sc=new Scanner(System.in);
		        	System.out.println("Enter usename:");
		        	String x=sc.next();
		        	System.out.println(x);
		            	pstmt.setString(1, x);

		            	int affectedRows = pstmt.executeUpdate();
		           	System.out.println(affectedRows);
		 }
		 catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		 }
		return 0;
	  }
	
}
