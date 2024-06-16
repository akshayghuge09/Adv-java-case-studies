package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Movie;
import com.services.MovieDAO;

/**
 * Servlet implementation class viewservlate
 */
@SuppressWarnings("serial")
@WebServlet("/viewservlate")
public class viewservlate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<a href='movie.html'>Add Mvoie</a>");
		out.print("<h1>Movie List</h1>");
		List<Movie> movies = new ArrayList<Movie>();
		out.print("<table border='1' withd='100%'><thead>");
		out.print("<tr><th>Movie ID</th><th>Movie Name</th><th>Actor</th><th>actresses</th>"
				+ "<th>director</th><th>Rating</th><th>Update</th><th>Delete</th></tr></thead><tbody>");
		movies = MovieDAO.allMovie();
		for (Movie movie : movies) {

			out.print("<tr><td>" + movie.getMovieId() + "</td><td>" + movie.getMovieName() + "</td>" + "<td>"
					+ movie.getActors() + "</td><td>" + movie.getActresses() + "</td><td>" + movie.getDirector()
					+ "</td>" + "<td>" + movie.getRating() + "</td><td><a href='UpdateServlet?movieId="
					+ movie.getMovieId() + "'>Update</a></td>" + "</td><td><a href='DeleteServlet?movieId="
					+ movie.getMovieId() + "'>Delete</a></td></tr>");

		}
		out.print("</tbody></table>");

	}

}
