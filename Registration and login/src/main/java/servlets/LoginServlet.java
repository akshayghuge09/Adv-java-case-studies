package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("uid");
		String s2 = request.getParameter("pass");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "akshay@123");
			PreparedStatement prst = con.prepareStatement("select * from registration where uid=? and password=?");
			prst.setString(1, s1);
			prst.setString(2, s2);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				out.println("<form method='post' action='UpdateServlet'>\n" + "uid<input type='text' value='" + s1
						+ "' name='t1'><br>\n" + "password<input type='password' value='" + s2 + "' name='t2'><br>\n"
						+ "username<input type='text' name='t3'><br>\n" + "mail id<input type='text' name='t4'><br>\n"
						+ "contact<input type='text' name='t5'><br>\n"
						+ "<input type='submit' value='update profile'>\n" + "</form>");
			} else {
				out.println("login faild u cann't update profile");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
