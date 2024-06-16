package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SqlServlet
 */

public class SqlServlet extends HttpServlet {
	String url;
    String driver;
    String username;
    String password;
	@Override
	public void init() throws ServletException {
		
		ServletContext context = getServletContext();
	    driver =context.getInitParameter("driver");
	    url = context.getInitParameter("databaseUrl");
	    username=context.getInitParameter("databaseUsername");
	    password=context.getInitParameter("databasePassword");
	}

	public SqlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.println("<h1>Database Configuration:</h1>");
		out.println("<h3>Driver:" + driver + " </h3>");
		out.println("<h3>sql url:" + url + " </h3>");
		out.println("<h3>sql username:" + username + " </h3>");
		out.println("<h3>sql password:" + password + " </h3>");
	
	}

}
