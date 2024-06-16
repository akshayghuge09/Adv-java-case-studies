package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/SetCookiesServlet")
public class SetCookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("t1");
		String s2 = request.getParameter("t2");

		Cookie c1 = new Cookie("desc", s1);
		Cookie c2 = new Cookie("qunt", s2);
		response.addCookie(c1);
		response.addCookie(c2);

		out.println("<h1>Store cookies</h1>" + "<form method='get' action='CookieServlet'>"
				+ "<input type='submit' value='click here'>" + "</form>");

		out.println("<h1>Store hidden data</h1>" + "<form method='get' action='GetCookieServlet'> "
				+ "<input type='hidden' value='" + s1 + "' name='user'>" + "<input type='submit' value='click here'>"
				+ "</form>");

	}

}
