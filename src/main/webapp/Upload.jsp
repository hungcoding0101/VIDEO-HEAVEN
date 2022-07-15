<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ page isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <c:import url="/includes/Header.html"></c:import>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="includes/style.css" type="text/css">
</head>
<body>

	<form action="UploadControl" method="post" enctype="multipart/form-data"   style="padding-top: 100px">
		
		Choose video file: &nbsp; &nbsp;
		<input type="file" name="file">
		<span style="color: red">${fileErrorMessage}</span><br><br>
		Title
		<input type="text" name="title">
		&nbsp;&nbsp;<span style="color: red">${titleErrorMessage}</span><br><br>
		Description
		<input type="text" name="description"><br><br>
		<input type="submit" value="submit">
		
	</form>

</body>
</html>