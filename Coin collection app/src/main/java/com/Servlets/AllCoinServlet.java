package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Coin;
import com.services.coinDAO;

/**
 * Servlet implementation class AllCoinServlet
 */
@SuppressWarnings("serial")
@WebServlet("/AllCoinServlet")
public class AllCoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Coin> coins = new ArrayList<Coin>();

		out.print("<body>" + "    <h1>List of coin</h1>" + "    <table border=2 color=red>" + "        <tr>"
				+ "            <th>sr. no.</th>" + "            <th>country Name</th>"
				+ "            <th>denomination</th>" + "            <th>year of minting</th>"
				+ "            <th>current value</th>" + "            <th>acquired date</th>" + "        </tr>");
		coins = coinDAO.showAllCoin();
		for (Coin coin : coins) {
			out.print("<tr><td>" + coin.getSr_no() + "</td><td>" + coin.getCountryName() + "</td><td>"
					+ coin.getDenomination() + "</td>" + "<td>" + coin.getYearOfMinting() + "</td><td>"
					+ coin.getCurrentValue() + "</td><td>" + coin.getAcuquiredDate() + "</td>"
					+ "<td><a href='DeleteCoinServlet?sr_no=" + coin.getSr_no() + "'>delete</a></td>"
					+ "<td><a href='UpdateServlet?sr_no=" + coin.getSr_no() + "'>updat</a></td></tr>");

		}
		out.print("</table> </body>");
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
