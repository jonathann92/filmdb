package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import objects.Customer;

public class CustomerService extends Service {
	
	public static Customer verifyCredentials(String username, String password) throws Exception{
		Class.forName(JDBC_DRIVER);
		
		Customer cust = null;
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		String query = "select * from customers "
				+ "where email=? and password =?";
		
		//try{
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.prepareStatement(query);
		select.setString(1, username);
		select.setString(2, password);
		rs = select.executeQuery();
		
		if(rs.next()) {
			cust = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), 
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}

//		} catch (Exception e){
//			throw new Exception(e.getMessage());
//		}

		return cust;
	}

}
