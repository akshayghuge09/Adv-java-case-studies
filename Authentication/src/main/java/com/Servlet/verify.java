package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.VerifyClass;

/**
 * Servlet implementation class verify
 */
@WebServlet("/verify")
public class verify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public verify() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Integer attempt;
		// 1 retrieve data from web

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		// 2 create an object of business logic class

		VerifyClass obj = new VerifyClass();

		// 3 execute the method and receive output
		// 4 take a decision based on result
		HttpSession css = request.getSession();
		int isvalid = obj.verify(user, pass);
		if (isvalid > 0) {
			response.sendRedirect("home.html");
			attempt = null;
			css.setAttribute("tries", attempt);

		} else {
			attempt = (Integer) css.getAttribute("tries");
			System.out.println(attempt);
			if (attempt == null) {
				attempt = 0;
			}
			attempt++;
			css.setAttribute("tries", attempt);
			if (attempt > 3) {
				css.invalidate();
				response.sendRedirect("index.html");
			} else {
				css.setAttribute("relogin", "<p style= 'color:red'> invalid user name or password</p>");
				response.sendRedirect("login.jsp");
			}
		}

	}

}
