package services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.*;
import java.util.List;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import objects.Movie;

public class Service {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql:///moviedb"; 
	public static  String db = "moviedb";
	public static  String user = "root";
	public static  String pass = "futurama5";	
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//EXAMPLE FOR CONNECTION POOLING!!!! 
	public static List<Genre> getAllGenres() {
		List<Genre> g = new ArrayList<>();
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");

			Connection conn = null;
			Statement select = null;
			ResultSet rs = null;
		
			conn = ds.getConnection();
			select =  conn.createStatement();
			String sql = "SELECT * FROM genres;";
			
			rs = (ResultSet) select.executeQuery(sql);
			while (rs.next()){
				g.add(new Genre(rs.getInt(1), rs.getString(2)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(NamingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public static void doXMLStuff(String title, String director, String year, String starFirst, String starLast, String genre)
	 {
		Connection connection = null;
		 	try {
	        	//Class.forName("com.mysql.jdbc.Driver").newInstance();
	            //Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:///"+db,user, pass);

				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
				connection = ds.getConnection();

	            String sql = "call add_movie(?, ?, ?, ?, ?, ?);";
	            
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setString(1, title);
	            stmt.setString(2, director);
	            stmt.setString(3, year);
	            stmt.setString(4, starFirst);
	            stmt.setString(5, starLast);
	            stmt.setString(6,  genre);
	        	stmt.execute();
	            
	            stmt.close();
	            connection.close();
			} 
           catch (Exception e) {
	            e.printStackTrace();
	        }
   }
	

	
}
