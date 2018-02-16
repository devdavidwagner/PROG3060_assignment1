<!--  DAVID W + ERIC T. MAIN MENU PAGE  -->
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
		<c:choose>
  			<c:when test="${sessionScope.user != null}">
				<p>Access Granted! Click below to query the database.</p>
		
		
				<div id ="links">
			
					<a href="GeoAreaServlet">Geographic Area Classifcation List</a>
					
					<a href="GeoAreaDetailServlet">Individual Geographic Area Details Page</a>
					
					<a href="AgeGroupServlet">Age Group List</a>
				</div>
			</c:when>
			
			<c:otherwise>
	    		<p>You do not have access to the database!Click below to navigate to log-in</p>
	    		<a href="./index.jsp">Log-In</a>
	  		</c:otherwise>
		</c:choose>

	

	<div id="foot">
		<p>Eric Tossell, David Wagner. PROG3060 Assignment 1</p>
	</div>

</body>




</html>