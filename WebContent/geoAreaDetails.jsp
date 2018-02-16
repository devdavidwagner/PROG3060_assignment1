<!--  DAVID W + ERIC T. GEO AREA DETAILS PAGE  -->
<!--  CREATE 2/14/2018  -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			
	<a href="./menu.jsp">Back to Menu</a>
	<h2>Geographic Area Details List</h2>
	
	<table>
		<tr>
			<th>Name</th>
			<th>Code</th>
			<th>Level</th>
			<th>Total Population</th>
			<th>Areas Within</th>
		</tr>
		<c:forEach items="${geoArea}" var="geoArea">
		<tr class="spaceUnder">
			 				
			<td><c:out value="${geoArea.name}"></c:out></td>	
			<td><c:out value="${geoArea.code}"></c:out></td>
			<td><c:out value="${geoArea.level}"></c:out></td>
			<td><c:out value="${geoArea.population}"></c:out></td>
			<td>
				 <table>
				 	<c:forEach items="${geoArea.areasWithin}" var="within">
			            <tr class ="subElement">
			              <td>
			                <c:out value="${within}"></c:out>
			              </td>
			            </tr>
		           	</c:forEach>
          		 </table>
			</td>
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
		<p>Eric Tossell, David Wagner. PROG3060 Assignment 1</p>
	</div>

</body>




</html>