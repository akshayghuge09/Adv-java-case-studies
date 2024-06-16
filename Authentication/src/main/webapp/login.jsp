<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="verify" method="post">
	username<input type="text" name="user">
	password<input type="password" name="pass">
	<input type="submit" value="login"> 
	<% 
	  String relogin=(String) session.getAttribute("relogin");
	  out.print(relogin);%>
	  
</form>
</body>
</html>