package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SRegistration
 */
@SuppressWarnings("serial")
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("uid");
		String s2 = request.getParameter("pass");
		String s3 = request.getParameter("uname");
		String s4 = request.getParameter("mail");
		int s5 = Integer.parseInt(request.getParameter("contact"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profound", "root", "akshay@123");
			PreparedStatement prst = con.prepareStatement("select * from registration where uid=?");
			prst.setString(1, s1);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				out.println("user id with uid = " + s1 + "is already present");
			} else {
				prst = con.prepareStatement("insert into registration values(?,?,?,?,?)");
				prst.setString(1, s1);
				prst.setString(2, s2);
				prst.setString(3, s3);
				prst.setString(4, s4);
				prst.setInt(5, s5);
				prst.executeUpdate();
				out.println("<h3>registration done suyccessfuly</h3>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.println(e);
		}
	}

}
