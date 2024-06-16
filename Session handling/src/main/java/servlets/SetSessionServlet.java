package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@SuppressWarnings("serial")
@WebServlet("/SetSessionServlet")
public class SetSessionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("user");
		String s2 = request.getParameter("pass");

		HttpSession session = request.getSession();
		session.setAttribute("uid", s1);
		session.setAttribute("pass", s2);

		out.println("values stored in session ");
		out.println("<a href='GetSessionServlet'>click here</a>");
	}

}
