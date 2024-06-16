package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.MovieDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@SuppressWarnings("serial")
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String movieId = request.getParameter("movieId");
		MovieDAO.delete(Integer.parseInt(movieId));
		response.sendRedirect("viewservlate");
	}

}
