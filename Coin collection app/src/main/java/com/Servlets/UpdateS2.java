package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Coin;
import com.services.coinDAO;

/**
 * Servlet implementation class UpdateS2
 */
@SuppressWarnings("serial")
@WebServlet("/UpdateS2")
public class UpdateS2 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sr_no = request.getParameter("sr_no");
		String countryName = request.getParameter("countryName");
		String denomination = request.getParameter("denomination");
		String yearOfminting = request.getParameter("mintingYear");
		String currentValue = request.getParameter("currentValue");
		String acquiredDate = request.getParameter("date");

		Coin coin = new Coin();
		coin.setSr_no(Integer.parseInt(sr_no));
		coin.setCountryName(countryName);
		coin.setDenomination(Float.parseFloat(denomination));
		coin.setYearOfMinting(Integer.parseInt(yearOfminting));
		coin.setCurrentValue(Double.parseDouble(currentValue));
		coin.setAcuquiredDate(Date.valueOf(acquiredDate));

		int status = coinDAO.update(coin);

		if (status > 0) {
			response.sendRedirect("AllCoinServlet");
		} else {
			out.print("Record  update process failed");
		}
		out.close();

	}

}
