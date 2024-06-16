package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VerifyClass {

	public int verify(String user, String pass) {
		int flag = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "akshay@123");
			String query = "select * from login where username=? and password=?";
			PreparedStatement prst = conn.prepareStatement(query);
			prst.setString(1, user);
			prst.setString(2, pass);
			ResultSet rs = prst.executeQuery();
			System.out.println();
			if (rs.next()) {
				flag = 1;
			} else {
				flag = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

}
