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
 * Servlet implementation class Add_servlet
 */
@SuppressWarnings("serial")
@WebServlet("/Add_servlet")
public class Add_servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String movieId = request.getParameter("movieId");
		String movieName = request.getParameter("moviename");
		String actors = request.getParameter("actor");
		String actresses = request.getParameter("actresseslist");
		String director = request.getParameter("directorname");
		String rating = request.getParameter("rating");

		Movie movie = new Movie();
		movie.setMovieId(Integer.parseInt(movieId));
		movie.setMovieName(movieName);
		movie.setActors(actors);
		movie.setActresses(actresses);
		movie.setDirector(director);
		movie.setRating(Integer.parseInt(rating));

		int status = MovieDAO.save(movie);
		if (status > 0) {
			response.sendRedirect("viewservlate");
		} else {
			out.print("Record  save process failed");
		}
		out.close();
	}

}