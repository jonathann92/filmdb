package servlet;

import java.io.IOException;
import java.sql.*;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.*;
import site.Site;
import cache.Cache;
/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected Customer custInfo(String username, String password) throws Exception{
		Customer cust = null;
		if(username != null && password != null)
			cust = Site.verifyCredentials(username, password);


		return cust;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String error = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String referrer = (String) session.getAttribute("Referer");
        
        try{
        	Customer user = custInfo(username, password);
	        if(user != null){
	        	out.print("SUCCESS");
	        	session.setAttribute("user", user);
	        	session.removeAttribute("referrer");
	        	response.sendRedirect(referrer);
	        } else {
	        	session.setAttribute("error", "Invalid Credentials");
	        	response.sendRedirect("/filmdb/LoginPrompt.jsp");
	        }
        } catch (Exception e){
        	error = e.getMessage();
        	session.setAttribute("error", error);
        	response.sendRedirect("/filmdb/LoginPrompt.jsp");
        }
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
