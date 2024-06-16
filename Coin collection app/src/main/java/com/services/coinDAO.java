package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Model.Coin;

public class coinDAO {

	public static Connection getconnetion() {
		// you have to create database and table according to coni class entity
		String dbURL = "jdbc:mysql://localhost:3306/database";

		String dbUserName = "root";
		String dbPass = "Akshay@123";

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, dbUserName, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Add coin
	public static int addCoin(Coin coin) {
		int flag = 0;
		try {
			Connection connection = coinDAO.getconnetion();
			String addQuery = "insert into coin (sr_no,countryName,denomination,yearOf_Minting,currentValue,acuquiredDate) values(?,?,?,?,?,?)";

			PreparedStatement prst = connection.prepareStatement(addQuery);
			prst.setInt(1, coin.getSr_no());
			prst.setString(2, coin.getCountryName());
			prst.setFloat(3, coin.getDenomination());
			prst.setInt(4, coin.getYearOfMinting());
			prst.setDouble(5, coin.getCurrentValue());
			prst.setDate(6, coin.getAcuquiredDate());
			flag = prst.executeUpdate();
			if (flag > 0) {
				System.out.println("Record save succssfully");
			} else {
				System.out.println("record  save process failed");
			}

			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	// delete coin
	public static int deleteCoin(int sr_no) {
		int status = 0;
		try {
			Connection connection = coinDAO.getconnetion();
			String deleteQuery = "delete from coin where sr_no=?";
			PreparedStatement prst = connection.prepareStatement(deleteQuery);
			prst.setInt(1, sr_no);

			status = prst.executeUpdate();
			if (status > 0) {
				System.out.println("Record deleted succssfully");
			} else {
				System.out.println("record  delete process failed");
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	// update coin
	public static int update(Coin coin) {

		int status = 0;
		try {
			Connection con = coinDAO.getconnetion();
			String updateQuery = "update coin set countryName=?,denomination=?,yearOf_minting=?,currentValue=?,acuquiredDate=? where sr_no=?";
			PreparedStatement prst = con.prepareStatement(updateQuery);

			prst.setString(1, coin.getCountryName());
			prst.setFloat(2, coin.getDenomination());
			prst.setInt(3, coin.getYearOfMinting());
			prst.setDouble(4, coin.getCurrentValue());
			prst.setDate(5, coin.getAcuquiredDate());
			prst.setInt(6, coin.getSr_no());
			status = prst.executeUpdate();
			if (status > 0) {
				System.out.println("Record update succssfully");
			} else {
				System.out.println("record  update process failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;

	}

	// search coin
	// view all
	public static List<Coin> showAllCoin() {
		List<Coin> coinlist = new ArrayList<Coin>();
		try {
			Connection con = coinDAO.getconnetion();
			String coinQuery = "select * from coin";

			PreparedStatement prst = con.prepareStatement(coinQuery);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Coin coin = new Coin();
				coin.setSr_no(rs.getInt(1));
				coin.setCountryName(rs.getString(2));
				coin.setDenomination(rs.getFloat(3));
				coin.setYearOfMinting(rs.getInt(4));
				coin.setCurrentValue(rs.getDouble(5));
				coin.setAcuquiredDate(rs.getDate(6));
				coinlist.add(coin);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coinlist;
	}
}
