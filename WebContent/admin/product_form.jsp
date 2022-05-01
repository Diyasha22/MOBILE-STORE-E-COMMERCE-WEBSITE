<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mobile store- create New Product</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="page-heading">
			<c:if test="${mobile!=null}">Edit Product Details</c:if>
			<c:if test="${mobile==null}">Create New Product</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${mobile!=null}">
			<form action="update_mobile" method="post" id="mobileForm" enctype="multipart/form-data">
			<input type="hidden" name="mobileId" value="${mobile.mobileId}" />
		</c:if>
		<c:if test="${mobile==null}">
			<form action="create_mobile" method="post" id="mobileForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Category:</td>
				<td align="left">
					<select name="category" id="cat">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq mobile.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne mobile.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Model:</td>
				<td align="left"><input type="text" id="model" name="model" size="30" value="${mobile.model}"/></td>
			</tr>
			<tr>
				<td align="right">Company:</td>
				<td align="left"><input type="text" id="company" name="company" size="30" value="${mobile.company}"/></td>
			</tr>
			<tr>
				<td align="right">IMEI:</td>
				<td align="left"><input type="text" id="imei" name="imei" size="30" value="${mobile.imei}"/></td>
			</tr>
			<tr>
				<td align="right">Launch Date:</td>
				<td align="left"><input type="text" id="published" name="published" size="30" 
				value="<fmt:formatDate pattern='MM/dd/yyyy' value='${mobile.publishDate}' />" /></td>
			</tr>
			<tr>
				<td align="right"> Image:</td>
				<td align="left">
					<input type="file" id="mobileImage" name="mobileImage" size="30"  onchange="loadFile(event)"/><br/>
					<img id="thumbnail" alt="Preview" style="width:25%; margin-top: 10px" 
						src="data:image/jpg;base64,${mobile.base64Image}"
					/>
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price" size="30" value="${mobile.price}"/></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="50" id="description" name="description">${mobile.description}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('#published').datepicker();
		
		
		
		$("#mobileForm").validate({
			rules: {
				category: "required",
				model: "required",
				company: "required",
				imei: "required",
				published: "required",
				
				<c:if test="${mobile==null}">
				mobileImage: "required",
				</c:if>
				
				price: "required",
				description: "required"
			},
			
			messages: {
				category: "Please select category!",
				model: "Please enter model!",
				company: "Please enter company!",
				imei: "Please enter IMEI!",
				published: "Please enter launch date!",
				mobileImage: "Please choose image!",
				price: "Please enter price!",
				description: "Please enter description!"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
	    });
		
	});
	
	var loadFile = function(event) {
	    var reader = new FileReader();
	    reader.onload = function(){
	      var output = document.getElementById('thumbnail');
	      output.src = reader.result;
	    };
	    reader.readAsDataURL(event.target.files[0]);
	  };
</script>
</body>

</html>