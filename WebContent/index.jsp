<!--  DAVID W + ERIC T. INTRO/INDEX PAGE  -->
<!--  CREATE 2/16/2018  -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PROG3060 - Assignment 1</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

</head>
<body>
<header>
	
	<img src="img/can.jpg" id="canLogo"/>
	<h1>Canadian Census</h1>
</header>
			
		

		<p>Please enter provided credentials to gain access to census database</p>
		<span id="error" style="color:red;">${error}</span>
	
		<form action="LoginServlet" method="post">
			<label for="username">Username</label>
			<input type ="text" name ="username"/>
			<label for="username">Password</label>
			<input type ="password" name ="password"/>
		
			<input type ="submit" name ="submit" value= "Submit">
		</form>

	
	
	

	<div id="foot">
		<p>Eric Tossell, David Wagner. PROG3060 Assignment 1</p>
	</div>

</body>




</html>