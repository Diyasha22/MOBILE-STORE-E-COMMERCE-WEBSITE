<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout - Mobile store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">

		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<c:set 	var="cart" value="${sessionScope['cart']}" />
		
		<c:if test="${cart.totalItems==0}">
			<h2>No Items in Cart. <a href="${pageContext.request.contextPath}/">Continue Shopping</a>!</h2>
		</c:if>
		
		<c:if test="${cart.totalItems>0}">
			<div>
				<h2>Review your Order Details <a href="view_cart">Edit</a></h2>
				<table border="1">
					<tr>
						<th>No</th>
						<th colspan="2">Product</th>
						<th>Company</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Subtotal</th>
					</tr>

					<c:forEach items="${cart.items}" var="item" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td colspan="2"><img style="vertical-align: middle;" src="data:image/jpg;base64,${item.key.base64Image}"
								width="128" height="164" />
							<span id="mobile-model">${item.key.model}</span></td>
							<td>${item.key.company}</td>
							<td>Rs. ${item.key.price}</td>
							
							<td>
								<input type="text" name="quantity${status.index+1}" value="${item.value}" size="5" readonly="readonly" />
							</td>
							
							<td>Rs. ${item.value * item.key.price}</td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="6" align="right">
							<p><b><i>Total Number of Products: ${cart.totalQuantity}</i></b></p></td>
							<td>
							<p><b><i>Rs. ${cart.totalAmount}</i></b></p>
						</td>
					</tr>
				</table>
				<h2>Recipient Information</h2>
				<form id="orderForm" action="place_order" method="post">
				<table>
					<tr>
						<td>Recipient Name:</td>
						<td><input type="text" name="fullname" value="${loggedCustomer.fullname}" /></td>
					</tr>
					<tr>
						<td>Recipient Contact:</td>
						<td><input type="text" name="phoneNo" value="${loggedCustomer.phoneNo}" /></td>
					</tr>
					<tr>
						<td>Recipient Address:</td>
						<td><input type="text" name="address" value="${loggedCustomer.address}" /></td>
					</tr>
					
				</table>

					<div>
						<h2>Payment</h2>
						Choose your payment method: &nbsp;&nbsp;&nbsp; <select
							name="paymentMethod">
							<option value="Cash On Delivery">Cash On Delivery</option>
							<option value="paypal">Paypal or Credit card</option>
						</select>
					</div>

					<div>
						<table class="normal" style="border: 0;">
							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td></td>
								<td><button type="submit">Place Order</button></td>
								<td><a href="${pageContext.request.contextPath}/">Continue
										Shopping</a></td>
							</tr>
						</table>
					</div>
				</form>
			</div>

		</c:if>

		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript" >
	$(document).ready(function(){
		
		$("#orderForm").validate({
			rules:{
				firstname: "required",
				lastname: "required",
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipcode: "required",
				country: "required",
			},
			
			messages:{
				firstname: "Please enter first name",
				lastname: "Please enter last name",
				phone: "Please enter phone",
				address1: "Please enter address Line 1",
				address2: "Please enter address Line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipcode: "Please enter zipcode",
				country: "Please enter country",
			}
			
		});
		
	});
	
</script>
</html>