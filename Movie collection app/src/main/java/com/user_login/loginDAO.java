package com.user_login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginDAO {
	public static Connection getConnection()
	{
		Connection connection = null;
		
		String dbURL ="jdbc:mysql://localhost:3306/";
		String dbName ="imdb" ;
		String userName="root";
		String passwaord="akshay@123";
		String dbDriverClassName="com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(dbDriverClassName);
			connection = DriverManager.getConnection(dbURL + dbName, userName, passwaord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	//add user
	public static int addUser(UserInfo info)
	{
		int status=0;
		try {
		Connection connection =loginDAO.getConnection();
		String insertQuery ="insert into login(user_name,password) values(?,?)";
		PreparedStatement prst = connection.prepareStatement(insertQuery);
		
		prst.setString(1,info.getUserName());
		prst.setString(2,new String(info.getpassword()));
		status= prst.executeUpdate();
		if(status>0)
		{
			System.out.println("Record save succssfully");
		}else {
			System.out.println("record  save process failed");
		}
		
		connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}
	
	
	//check user
	
	public static int checkUser(String uName,String pass)
	{
		int check=0;
		try {
		
		Connection connection = loginDAO.getConnection();
		String query ="select * from login where user_name=? and password=?";
		PreparedStatement prst = connection.prepareStatement(query);
	
		prst.setString(1,uName);
		prst.setString(2,pass);
		ResultSet rs = prst.executeQuery();
		if(rs.next())
		{
			 check=1;
		}else
		{
			 check=0;
		}
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	    
		
	}

}
