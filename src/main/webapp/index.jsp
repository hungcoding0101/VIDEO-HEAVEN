<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="includes/style.css" type="text/css">
</head>
<body>

 <h1 style="text-align: center" >WELCOME TO VIDEOHEAVEN.COM!</h1>


<form action="LogControl" method="post">
		LOG IN: <br><br>
		<input type="hidden" name="notFirstTime" value="notFirstTime">
		Email: <input type="email" name="email" value = "${email}">
		&nbsp;&nbsp;<span style="color: red">${EmailMessage}</span>
		<br><br>
		Password: <input type="password" name="pass"  value = "${pass}" >
		&nbsp;&nbsp;<span style="color: red">${PassMessage}</span>
		
		<br><br>
		<input type="submit" value="Log in">
		
		<p style="color: red"><i>${Message}</i></p>
		
		
	</form>
	<p>
	<form action="SignupControl" method="post">
		SIGN UP : <br><br>
		User name: <input type="text" name="username" value = "${username}">
		&nbsp;&nbsp;<span style="color: red">${nameSignUpMessage}</span>
		<br><br>
		Email: <input type="email" name="email" value = "${emailSignup}" >
		&nbsp;&nbsp;<span style="color: red">${emailSignUpMessage}</span>
		<br><br>
		Password: <input type="password" name="pass" value = "${passSignup}">
		&nbsp;&nbsp;<span style="color: red">${passSignUpMessage}</span>
		<br><br>
		<input type="submit" value="Register">	
	</form>


</body>
</html>