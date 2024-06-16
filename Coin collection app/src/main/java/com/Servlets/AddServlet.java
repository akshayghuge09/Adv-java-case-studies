package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Coin;
import com.services.coinDAO;

/**
 * Servlet implementation class AddServlet
 */
@SuppressWarnings("serial")
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sr_no = request.getParameter("sr_no");
		String countryName = request.getParameter("countryName");
		String denomination = request.getParameter("denomination");
		String yearOfminting = request.getParameter("mintingYear");
		String currentValue = request.getParameter("currentValue");

		Coin coin = new Coin();
		coin.setSr_no(Integer.parseInt(sr_no));
		coin.setCountryName(countryName);
		coin.setDenomination(Float.parseFloat(denomination));
		coin.setYearOfMinting(Integer.parseInt(yearOfminting));
		coin.setCurrentValue(Double.parseDouble(currentValue));
		coin.setAcuquiredDate(Date.valueOf(LocalDate.now()));

		int status = coinDAO.addCoin(coin);
		if (status > 0) {

			response.sendRedirect("AllCoinServlet");
		} else {
			out.print("Record  save process failed");
		}
		out.close();
	}

}
