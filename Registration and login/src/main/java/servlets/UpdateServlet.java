package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@SuppressWarnings("serial")
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("t1");
		String s2 = request.getParameter("t2");
		String s3 = request.getParameter("t3");
		String s4 = request.getParameter("t4");
		String s5 = request.getParameter("t5");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "akshay@123");
			PreparedStatement prst = con.prepareStatement(
					"update registration set username=?,gmail=?,contact=? where uid=? and password=?");
			prst.setString(1, s3);
			prst.setString(2, s4);
			prst.setString(3, s5);
			prst.setString(4, s1);
			prst.setString(5, s2);
			prst.executeUpdate();

			out.println("<h2>update succeessful</h2>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
