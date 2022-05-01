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
<jsp:directive.include file="header.jsp"/>
<div align="center">
<br><br>
<h2 class="page-heading"> Latest Products: </h2>
<div align="center" style="width:90%; margin:0 auto;">
		<c:forEach items="${listNewMobiles}" var="mobile" >
			<jsp:directive.include file="mobile_group.jsp" />
		</c:forEach>
	</div>	
	<div align="center" style="clear:both" style="width:90%; margin:0 auto;">
<h2 class="page-heading"> Best-selling Products: </h2>
<c:forEach items="${listBestSellingMobiles}" var="mobile" >
			<jsp:directive.include file="mobile_group.jsp" />
		</c:forEach>
</div>
<div align="center" style="clear:both; width:90%; margin:0 auto;">
<h2 class="page-heading"> Most Favoured Products: </h2>
<c:forEach items="${listMostFavoredMobiles}" var="mobile" >
	<jsp:directive.include file="mobile_group.jsp" />
</c:forEach>
</div>
<br><br>
</div>
<jsp:directive.include file="footer.jsp"/>
</body>
</html>
