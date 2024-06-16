package com.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.coinDAO;

/**
 * Servlet implementation class DeleteCoinServlet
 */
@SuppressWarnings("serial")
@WebServlet("/DeleteCoinServlet")
public class DeleteCoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sr_no = request.getParameter("sr_no");
		coinDAO.deleteCoin(Integer.parseInt(sr_no));
		response.sendRedirect("AllCoinServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
