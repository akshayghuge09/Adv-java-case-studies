package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Movie;
import com.services.MovieDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@SuppressWarnings("serial")
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<h1>Update movie record</h1>");
		String movieId = request.getParameter("movieId");
		Movie movie = MovieDAO.getmovie(Integer.parseInt(movieId));
		out.print("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset='ISO-8859-1'>"
				+ "<title>Update movie</title>" + "</head>" + "<body>" + "<h1>Update Movie</h1>"
				+ "<form action='Updateservlet2' method='post'>" + "	<table>" + "		<tr>"
				+ "			<td>Movie ID</td>" + "			<td><input type='hidden' name='movieId' value='"
				+ movie.getMovieId() + "'></td>" + "		</tr>" + "		<tr>" + "			<td>Movie Name</td>"
				+ "			<td><input type='text' name='moviename'value='" + movie.getMovieName() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Actorss</td>"
				+ "			<td><input type='text' name='actor'value='" + movie.getActors() + "'></td>" + "		</tr>"
				+ "		<tr>" + "			<td>Actorsses List</td>"
				+ "			<td><input type='text' name='actresseslist'value='" + movie.getActresses() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Director Name</td>"
				+ "			<td><input type='text' name='directorname'value='" + movie.getDirector() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Rating out of 5</td>"
				+ "			<td><select name='rating'value='" + movie.getRating() + "'>"
				+ "				<option>1</option>" + "				<option>2</option>"
				+ "				<option>3</option>" + "				<option>4</option>"
				+ "			    <option>5</option>	" + "			</select></td>" + "		</tr>" + "		<tr>"
				+ "			<td colspan='2'>" + "				<input type='submit' value='Update movie'>"
				+ "			</td>" + "		</tr>" + "	</table>" + "</form>"
				+ "<a href='/imdb/viewservlate'>View Movie List</a>" + "</body>" + "</html>");
	}

}
