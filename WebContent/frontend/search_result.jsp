<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Mobile</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:directive.include file="header.jsp" />
	<div align="center">
	<c:if test="${fn:length(result) == 0}">
		<h2 class="page-heading">No Results Found For "${keyword}"</h2>
	</c:if>
	
	<c:if test="${fn:length(result) > 0}">
		<div align="left" style="width: 80%; margin: 0 auto;">
			<center><h2 class="page-heading">Results for "${keyword}"</h2></center>
			<c:forEach items="${result}" var="mobile">
				<div>
				<div style="display: inline-block; margin: 20px; width: 10%;">
					<div align="left">
						<a href="view_mobile?id=${mobile.mobileId}"> <img
							src="data:image/jpg;base64,${mobile.base64Image}" width="128"
							height="164" />
						</a>
					</div>
				</div>
				<div style="display: inline-block; margin: 20px; vertical-align: top; width: 50%;" align="left">
					<div>
						<h2><a href="view_mobile?id=${mobile.mobileId}"> <b>${mobile.model}</b></a></h2>
					</div>
					<div>
					<jsp:directive.include file="mobile_rating.jsp" /> &nbsp;&nbsp;
					<a href="view_mobile?id=${mobile.mobileId}">${fn:length(mobile.reviews)} Reviews</a>
					</div>
					<div>
						<i>By ${mobile.company}</i>
					</div>
					<div>
						<p>${fn:substring(mobile.description,0 , 100)}...</p>
					</div>
				</div>
				<div style="display: inline-block; margin: 20px; vertical-align: top;">
					<h3>Price: Rs.${mobile.price}</h3>
					<h3><a href="">Add to Cart</a></h3>
				</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	
	</div>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>