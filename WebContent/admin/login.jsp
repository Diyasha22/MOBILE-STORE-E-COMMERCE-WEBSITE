<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
	<h1>Mobile Store Administration</h1>
	<h2>Admin Login</h2><h3><a href="../">Login As A Customer</a></h3>
	
	<c:if test="${message!=null}">
	<div align="center">
	<h4><font color="red" size="5"><i><marquee>${message}</marquee></i></font></h4>
	</div>
	</c:if>
	
	<form id="loginForm" action="login" method="post">
	<table class="form">
	<tr>
	<td align="right">Email:</td>
	<td><input type="text" name="email" id="email" size="20"></td>
	</tr>
	<tr>
	<td align="right">Password:</td>
	<td ><input type="password" name="password" id="password" size="20"></td>
	</tr>
	<tr>
	<td colspan="2" align="center">
	<button type="submit">Login</button>
	</td>
	</tr>
	</table>
	</form>
	</div>
	<script type="text/javascript">
$(document).ready(function(){
	$("#loginForm").validate({
		rules: {
			email:{ 
			required: true,
			email: true
			},
			password: "required"
		},
		messages: {
			email:{
				required:"Please enter email",
				email:"Please enter a valid email address"
			},
			password: "Please enter password"
		}
	});
});

</script>
</body>
</html>