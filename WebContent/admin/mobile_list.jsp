<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Products - Mobile Store Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="page-heading">Product Management</h2>
		<h3><a href="new_mobile">Create New Product</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4><font color="orange" size="6"><i><marquee>${message}</marquee></i></font></h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5" >
			<tr>
				<th>Index</th>
				<th>Product Id</th>
				<th>Image</th>
				<th>Model</th>
				<th>Company</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach var="mobile" items="${listMobiles}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${mobile.mobileId}</td>
					
					<td>
						<img src="data:image/jpg;base64,${mobile.base64Image}" width="84" height="110" />
					</td>
					
					<td>${mobile.model}</td>
					<td>${mobile.company}</td>
					<td>${mobile.category.name}</td>
					<td>Rs. ${mobile.price}</td>
					<td>
					<fmt:formatDate pattern='MM/dd/yyyy' value='${mobile.lastUpdateTime}' />
					</td>
					<td>
						<a href="edit_mobile?id=${mobile.mobileId}">Edit</a> &nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${mobile.mobileId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					mobileId = $(this).attr("id");
					if(confirm('Are you sure you want to delete the product with ID '+ mobileId + ' ?')){
						window.location='delete_mobile?id=' + mobileId;
					}
				});
			});
		});	
		
			
	</script>



</body>
</html>