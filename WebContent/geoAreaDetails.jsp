<!--  DAVID W + ERIC T. GEO AREA DETAILS PAGE  -->
<!--  CREATE 2/14/2018  -->
<!--  FINISH A2 4/6/2018  -->


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
	<h2>Geographic Area Details for ${geoArea.getName()}</h2>
	<p>Code: ${geoArea.getCode()}</p>
	<p>Alternative Code: ${geoArea.getAltCode()}</p>
	<p>Level: ${geoArea.getLevel()}</p>
	
	
	<c:if test="${geoArea.getLevel() == 0 || geoArea.getLevel() ==1 || geoArea.getLevel() == 2}">
	
			<p>Households that Fit Criteria = ${numberHouseholdsWithin}</p>

		<hr>
		<h4>Areas within: ${geoArea.getName()}</h4>
		
		<table>
			<tr>
				<th>Name</th>
				<th>Alternative Code</th>
			</tr>
		<c:forEach items="${geoAreasWithin}" var="geoArea">
			<tr>
			
				<td><c:out value="${geoArea.getName()}"></c:out></td>
				<td><c:out value="${geoArea.getAltCode()}"></c:out></td>
			
			
			</tr>
		</c:forEach>
		</table>
	
	</c:if>
	<hr>
	
	<table>
		<tr>
			<th>Census Year</th>
			<th>Age Group</th>
			<th>Male</th>
			<th>Female</th>
			<th>Total Population</th>
			
			
		</tr>

		<c:forEach items="${age}" var="age">
		<tr class="spaceUnder">
			<td><c:out value="${age.getCensusYear().getCensusYear()}"></c:out></td>
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