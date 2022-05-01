<div align="center">
		<h2>Order Overview:</h2>
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Order status: </b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>No of Product: </b></td>
				<td>${order.productCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td>Rs. ${order.total}</td>
			</tr>
		</table>
		<h2>Recipient Information</h2>
		<table>
			<tr>
				<td><b>Full Name: </b></td>
				<td>${order.receipentName}</td>
			</tr>
			<tr>
				<td><b>Phone: </b></td>
				<td>${order.receipentPhoneno}</td>
			</tr>
			<tr>
				<td><b>Shipping Address: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			
		</table>
	</div>
	<div align="center">
		<h2>Ordered Items</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Product Model</th>
				<th>Brand</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.mobile.base64Image}" width="48" hieght="64" />
						${orderDetail.mobile.model}
					</td>
					<td>${orderDetail.mobile.company}</td>
					<td>Rs. ${orderDetail.mobile.price}</td>
					<td>${orderDetail.quantity}</td>
					<td>Rs. ${orderDetail.subtotal}</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4" align="right">
					<b>Total: </b></td>
					<td>
					${order.productCopies}</td>
					<td>Rs. ${order.total}
				</td>
			</tr>
		</table>
	</div>