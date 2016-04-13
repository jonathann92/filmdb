package servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import site.Site;

/**
 * Servlet implementation class AdvSearch
 */
@WebServlet("/AdvSearch")
public class AdvSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String error = "No error";
        
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        
        try{
        	List<Movie> movieList;
        	movieList = Site.searchMovie(title, year, director, first, last);
        	session.setAttribute("fullMovieList", movieList);
        	response.sendRedirect("/filmdb/MovieList");
        	//Site.forward(request, response, "/MovieList");
        } catch (Exception e){
        	error = e.getMessage();
        	request.setAttribute("error", error);
        	out.print(error);
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
