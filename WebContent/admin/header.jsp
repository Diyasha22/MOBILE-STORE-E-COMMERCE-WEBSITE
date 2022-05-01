<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
<div>
<img src="../images/mobile icon 2.jpg" height="200"/>
</div>
<div>
   Welcome, <c:out value="${sessionScope.useremail }"/> | <a href="logout">Logout</a>
</div>
<br><br>
<div id="header_menu">
	<div>
		<a href="list_users">
		<img src="../images/users.png"></br>Users
		</a>
	</div>
	<div>
		<a href="list_category">
		<img src="../images/category.png"></br>Categories
		</a> 
	</div>
	<div>
		<a href="list_mobiles">
		<img src="../images/mobile icon.png" width="70" height="70"></br>Products
		</a>
	</div>
	<div>
		<a href="list_customer">
		<img src="../images/customer.png"></br>Customers
		</a>
	</div> 
	<div>
		<a href="list_reviews">
		<img src="../images/review.png"></br>Reviews
		</a>
	</div> 
	<div>
		<a href="list_orders">
		<img src="../images/order.png"></br>Orders
		</a>
	</div> 
</div>
</div>