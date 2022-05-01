<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Mobile Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>


<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2>${category.name}</h2>
	</div>
	
	<div align="center" style="margin:0 auto; margin-left:60px;">
		<c:forEach items="${listMobiles}" var="mobile" >
			<div style="float:left; display: inline-block; width:20%; margin: 20px;">
				<div>
					<a href="view_mobile?id=${mobile.mobileId}" >
						<img src="data:image/jpg;base64,${mobile.base64Image}" width="128" height="164" />
					</a>
				</div>
				<div>
					<a href="view_mobile?id=${mobile.mobileId}" >
				 		<b>${mobile.model}</b>
				 	</a>
				 </div>
				<div><i>By ${mobile.company}</i></div>
				<div><b>Rs.${mobile.price}</b></div>
				<div><jsp:directive.include file="mobile_rating.jsp" /></div>
			</div>
		</c:forEach>
	</div>	
	
	
	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>