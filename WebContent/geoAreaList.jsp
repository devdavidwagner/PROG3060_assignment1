<!--  DAVID W + ERIC T. GEO AREA LIST PAGE  -->
<!--  CREATE 2/12/2018  -->

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
		<br>
		<br>

		<h1>Canadian Census</h1>
	</header>
	
	<c:choose>
  		<c:when test="${sessionScope.user != null}">
			
			<a href="./menu.jsp">Back to Menu</a>
			<h2>Geographic Area Classifcation List</h2>
	
		
			
			<div id ="tableDiv">
							
					<table>
					<br>
					  <tr>
					    <th>CATEGORY 0</th>
					
					  </tr>
					  <br>
					   <c:forEach items="${Cat0}" var="cat">
						  <tr>
						   <td> <c:out value="${cat}"></c:out></td>	
						  </tr>
					   </c:forEach>
					</table>
					
					<table>
						<br>
						  <tr>
						    <th>CATEGORY 1</th>
							
						  </tr>
						 <br>
						    <c:forEach items="${Cat1}" var="cat">
							  <tr>
							   <td> <c:out value="${cat}"></c:out></td>	
							  </tr>
						   </c:forEach>
					</table>
					
					<table>
					 <br>
					  <tr>
					    <th>CATEGORY 2</th>
					
					  </tr>
					  <br>
					  <c:forEach items="${Cat2}" var="cat">
						  <tr>
						   <td> <c:out value="${cat}"></c:out></td>	
						  </tr>
					   </c:forEach>
					</table>
					
					<table>
					  <br>
					  <tr>
					    <th>CATEGORY 3</th>
					
					  </tr>
					    <br>
					   <c:forEach items="${Cat3}" var="cat">
						  <tr>
						   <td> <c:out value="${cat}"></c:out></td>	
						  </tr>
					   </c:forEach>
					</table>
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