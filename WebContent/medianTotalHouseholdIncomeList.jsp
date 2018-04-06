<!--  DAVID W + ERIC T. MEDIAN TOTAL INCOME PAGE  -->
<!--  CREATE 2/14/2018  -->
<!--  FINISH A2 4/6/2018  -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PROG3060 - Assignment 2</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

</head>
<body>
<header>
	
	<img src="img/can.jpg" id="canLogo"/>
	<h1>Canadian Census</h1>
</header>
	<c:choose>
  	<c:when test="${sessionScope.user != null}">
		<a href="./menu.jsp">Back to Menu</a>
		<h2>Median Total Income List</h2>
		<br>
		
		
		
		<table>
			<tr>
			    <th>Name</th>
				<th>Median Total Income</th>
			
			</tr>
			
			
			 <c:forEach items="${households}" var="house">
			 		<tr>
			 			<td><c:out value="${house.getGeographicArea().getName()}"></c:out></td>
						<td><c:out value="${house.getTotalIncome().getDescription()}"></c:out></td>
					<tr>
			 </c:forEach>
			
		
		
		</table>
	
	</c:when>
	<c:otherwise>
   		<p>You do not have access to the database! Click below to navigate to log-in</p>
   		<a href="./index.jsp">Log-In</a>
 		</c:otherwise>
	</c:choose>
	
	<div id="foot">
		<p>Eric Tossell, David Wagner. PROG3060 Assignment 2</p>
	</div>

</body>




</html>