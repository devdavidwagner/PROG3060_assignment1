<!--  DAVID W + ERIC T. GEO AREA LIST PAGE  -->
<!--  CREATE 2/12/2018  -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PROG3060 - Assignment 2</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
  	<script>
  	
  	 	function hide(){
	  		 var x0 = document.getElementById("cat0");
	  		 var x1 = document.getElementById("cat1");
	  		 var x2 = document.getElementById("cat2");
	  		 var x3 = document.getElementById("cat3");
	  		 x0.style.display = "none";
	  		 x1.style.display = "none";
	  		 x2.style.display = "none";
	  		 x3.style.display = "none";
  		 
  		 
  		 
  	  	}
     	function hideCat(category){
     			
     		if(category == 0)
     			{
	     			 var x = document.getElementById("cat0");
	      		    if (x.style.display === "none") {
	      		        x.style.display = "block";
	      		       document.getElementById("hide0").innerText = "Hide Category 0";
	      		    } else {
	      		        x.style.display = "none";
	      		       document.getElementById("hide0").innerText = "Show Category 0";
	      		    }
      				
     			}
     		else if(category == 1)
 			{
     			 var x = document.getElementById("cat1");
      		    if (x.style.display === "none") {
      		        x.style.display = "block";
      		       document.getElementById("hide1").innerText = "Hide Category 1";
      		    } else {
      		        x.style.display = "none";
      		        document.getElementById("hide1").innerText = "Show Category 1";
      		    }
  				
 			}
     		else if(category == 2)
 			{
     			 var x = document.getElementById("cat2");
      		    if (x.style.display === "none") {
      		        x.style.display = "block";
      		       document.getElementById("hide2").innerText = "Hide Category 2";
      		    } else {
      		        x.style.display = "none";
      		       document.getElementById("hide2").innerText = "Show Category 2";
      		    }
  				
 			}
     		else if(category == 3)
 			{
     			 var x = document.getElementById("cat3");
      		    if (x.style.display === "none") {
      		        x.style.display = "block";
      		       document.getElementById("hide3").innerText = "Hide Category 3";
      		    } else {
      		        x.style.display = "none";
      		       document.getElementById("hide3").innerText = "Show Category 3";
      		    }
  				
 			}
     			
     		
     		
     		 
     	}
    </script>
</head>
<body onload="hide()">
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
	
		
			
			<div id ="hides">
				<button onclick="hideCat(0)" id ="hide0">Show Category 0</button>
				<button onclick="hideCat(1)" id ="hide1">Show Category 1</button>
				<button onclick="hideCat(2)" id ="hide2">Show Category 2</button>
				<button onclick="hideCat(3)" id ="hide3">Show Category 3</button>
			</div>
			
			
			<div id ="tableDiv">
					<div id="cat0">
						<table>
						<br>
						  <tr>
						    <p>CATEGORY 0</p>
						
						  </tr>
						  <br>
						   <c:forEach items="${geoCat0}" var="geoArea">
							  <tr  class="spaceUnder">
							   <td> <a href="GeoAreaDetailServlet?geoAreaID=<c:out value="${geoArea.geoAreaID}"></c:out>">  
							   <c:out value="${geoArea.name}"></c:out></a></td>	
							  </tr>
						   </c:forEach>
						</table>
					</div>
				
					<div id="cat1">
						<table>
							<br>
							  <tr >
							    <p>CATEGORY 1</p>
								
							  </tr>
							 <br>
							    <c:forEach items="${geoCat1}" var="geoArea">
								  <tr  class="spaceUnder">
								    <td> <a href="GeoAreaDetailServlet?geoAreaID=<c:out value="${geoArea.geoAreaID}"></c:out>">  
							   			<c:out value="${geoArea.name}"></c:out></a></td>		
								  </tr>
							   </c:forEach>
						</table>
					</div>
					
					<div id="cat2">
						<table>
						 <br>
						  <tr>
						    <p>CATEGORY 2</p>
						
						  </tr>
						  <br>
						  <c:forEach items="${geoCat2}" var="geoArea">
							  <tr  class="spaceUnder">
							   <td> <a href="GeoAreaDetailServlet?geoAreaID=<c:out value="${geoArea.geoAreaID}"></c:out>">  
							   <c:out value="${geoArea.name}"></c:out></a></td>		
							  </tr>
						   </c:forEach>
						</table>
					</div>
				
					<div id="cat3">
						<table>
						  <br>
						  <tr>
						    <p>CATEGORY 3</p>
						
						  </tr>
						    <br>
						   <c:forEach items="${geoCat3}" var="geoArea">
							  <tr  class="spaceUnder">
							    <td> <a href="GeoAreaDetailServlet?geoAreaID=<c:out value="${geoArea.geoAreaID}"></c:out>">  
							    <c:out value="${geoArea.name}"></c:out></a></td>	
							  </tr>
						   </c:forEach>
						</table>
					</div>

			</div>
	
		</c:when>
		<c:otherwise>
    		<p>You do not have access to the database!Click below to navigate to log-in</p>
    		<a href="./index.jsp">Log-In</a>
  		</c:otherwise>
	</c:choose>
	
	
	
	

	<div id="foot">
		<p>Eric Tossell, David Wagner. PROG3060 Assignment 2</p>
	</div>

</body>




</html>