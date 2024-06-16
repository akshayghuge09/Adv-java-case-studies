package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String s1 = request.getParameter("user");
		String s2 = request.getParameter("mail");
		String s3 = request.getParameter("r1");
		String s4 = request.getParameter("c1");
		String s5 = request.getParameter("c2");
		String s6 = request.getParameter("c3");
		String s7 = request.getParameter("s");

		out.println("<body bgcolor='yellow'>");
		out.println("<form align='center' method='post' action='RegisterServlet'>"
				+ "Username<input type='text' name='user' value='" + s1 + "'><br><br>"
				+ "Email<input type='text' name='mail' value='" + s2 + "'><br><br>");
		if (s3.equals("Male")){
			out.println("Gender: Male <input type='radio' name='r1' value='Male' checked>"
				+ "	Female<input type='radio' name='r1' value='Female'><br><br>");
		} 
		else if (s3.equals("Female")) {
			out.println("Gender: Male <input type='radio' name='r1' value='Male' >"
				+ "Female<input type='radio' name='r1' value='Female' checked><br><br>");
		}
		if (s4 != null) {
			out.println(" <input type='checkbox' name='c1' value='Hindi' checked>Hindi");
		}
		else {
			out.println(" <input type='checkbox' name='c1' value='Hindi'>Hindi");
		}
		if (s5 != null) {
			out.println(" <input type='checkbox' name='c2' value='Marathi' checked>Marathi");
		}
		else {
			out.println(" <input type='checkbox' name='c2' value='Marathi'>Marathi");
		}
		if (s6 != null) {
			out.println(" <input type='checkbox' name='c3' value='English' checked>English<br><br>");
		}
		else {
			out.println(" <input type='checkbox' name='c3' value='English'>English<br><br>");
		}
		out.println("Select city<select><option>" + s7 + "</option></select><br><br> "
				+ "<input type='submit' value='register'>" 
				+ "<input type='submit' value='reset'>");
		out.println("</body>");
	}

}
