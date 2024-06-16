package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user_login.UserInfo;
import com.user_login.loginDAO;

/**
 * Servlet implementation class newUser_servlet
 */
@SuppressWarnings("serial")
@WebServlet("/newUser_servlet")
public class newUser_servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserInfo info = new UserInfo();
		info.setUserName(userName);
		info.setpassword(password);
		int status = loginDAO.addUser(info);
		if (status > 0) {
			out.print("Registraion successful");
		} else {
			out.print("Registration faild plz try again");
		}
	}

}
