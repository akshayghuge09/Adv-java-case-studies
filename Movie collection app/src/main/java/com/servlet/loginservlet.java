package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user_login.loginDAO;

@SuppressWarnings("serial")
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int check = 0;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		check = loginDAO.checkUser(userName, password);
		System.out.println(check);
		if (check > 0) {
			out.print(check);
			response.sendRedirect("movie.html");
		} else {
			out.print("invalid user name or password");
		}
	}

}
