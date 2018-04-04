<!--  DAVID W + ERIC T. GEO AREA DETAILS PAGE  -->
<!--  CREATE 2/14/2018  -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h2>Geographic Area Details List</h2>
	
	<table>
		<tr>
			<th>Name</th>
			<th>Code</th>
			<th>Alternative Code</th>
			<th>Level</th>
			<th>Description</th>
			<th>Male</th>
			<th>Female</th>
			<th>Total Population</th>
			
		</tr>
		<c:forEach items="${age}" var="age">
		<tr class="spaceUnder">
			 				
			<td><c:out value="${age.getGeoArea().name}"></c:out></td>	
			<td><c:out value="${age.getGeoArea().code}"></c:out></td>
			<td><c:out value="${age.getGeoArea().altCode}"></c:out></td>
			<td><c:out value="${age.getGeoArea().level}"></c:out></td>
			<td><c:out value="${age.getAgeGroup().description}"></c:out></td>
			<td><c:out value="${age.male}"></c:out></td>
			<td><c:out value="${age.female}"></c:out></td>
			<td><c:out value="${age.combined}"></c:out></td>
		</tr>
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