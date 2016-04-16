package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.*;
import services.MovieService;
import services.Service;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ref = request.getHeader("Referer");
        out.print(ref);
        HttpSession session = request.getSession(true);
        
        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String value = request.getHeader(name);
        out.println(name + " = " + value);
        out.println("<br/>");
        }
        
        
        Map<Movie, MutableInt> cart = (Map<Movie, MutableInt>) session.getAttribute("cart");
        if(cart == null){
        	cart = new HashMap<Movie, MutableInt>();
        }
        
        String parameter = request.getParameter("id");
        
//        try{
//        	if(parameter == null || parameter.length() <= 0)
//        		throw new Exception("no item selected");
//        	
//        	int id = Integer.parseInt(parameter);
//        	Movie m = MovieService.getMovieInfo(id);
//	        if(m == null)
//	        	throw new Exception("no movie found");
//	        
//        } catch (Exception e){
//        	String error = e.getMessage();
//        	request.setAttribute("error", error);
//        	out.print(ref);
//        	//Service.forward(request, response, ref);
//        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}