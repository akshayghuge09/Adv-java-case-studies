package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Coin;
import com.services.coinDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@SuppressWarnings("serial")
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Coin coin = new Coin();
		String sr_no = request.getParameter("sr_no");
		try {
			Connection con = coinDAO.getconnetion();
			String getcoin = "select * from coin where sr_no=?";
			PreparedStatement prst = con.prepareStatement(getcoin);
			prst.setInt(1, Integer.parseInt(sr_no));
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				coin.setSr_no(rs.getInt(1));
				coin.setCountryName(rs.getString(2));
				coin.setDenomination(rs.getFloat(3));
				coin.setYearOfMinting(rs.getInt(4));
				coin.setCurrentValue(rs.getDouble(5));
				coin.setAcuquiredDate(rs.getDate(6));
			}

			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.print("<h2>Update coin</h2>");
		out.print("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset='ISO-8859-1'>" + "<title>Update coin</title>"
				+ "</head>" + "<body>" + "<form action='UpdateS2' method='post'>" + "	<table>" + "		<tr>"
				+ "			<td>Sr number</td>" + "			<td><input type='number' name='sr_no' value='"
				+ coin.getSr_no() + "'></td>" + "		</tr>" + "		<tr>" + "			<td>Country Name</td>"
				+ "			<td><input type='text' name='countryName' value='" + coin.getCountryName() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Denomination</td>"
				+ "			<td><input type='number' name='denomination'value='" + coin.getDenomination() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Year of minting</td>"
				+ "			<td><input type='number' name='mintingYear' value='" + coin.getYearOfMinting() + "'></td>"
				+ "		</tr>" + "		<tr>" + "			<td>Current value</td>"
				+ "			<td><input type='number' name='currentValue' value='" + coin.getCurrentValue() + "'></td>"
				+ "		</tr> " + "<tr><td>Acquried date</td>" + "<td><input type='date' name='date' value='"
				+ coin.getAcuquiredDate() + "'></td></tr> " + "		<tr>"
				+ "			<td><input type='submit' value='update coin'></td>"
				+ "			<td><a href='AllCoinServlet'>show all coins</a></td>" + "		</tr>" + "	</table>"
				+ "</form>" + "</body>" + "</html>");

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
