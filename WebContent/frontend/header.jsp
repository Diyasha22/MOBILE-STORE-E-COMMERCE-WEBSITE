<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
<div>
<a href="${pageContext.request.contextPath}/">
<img  alt="logo" src="./images/mobile icon 2.jpg" height="200"/></a>
</div>
<div>
<form action="search" method="get"  style="margin-top: 30px;">
<input type="text" name="keyword" size="40"/>
<input type="submit" value="Search"/>
&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <c:if test="${loggedCustomer==null}">
			     	<a href="login">Sign in</a> | 
			    	<a href="register">Register</a> |
			    </c:if>
			    
			    <c:if test="${loggedCustomer!=null}">
			     	<a href="view_profile">Hello, ${loggedCustomer.fullname}</a> | 
			    	<a href="view_orders">My Orders</a> |
			    	<a href="logout">Logout</a> |
			    </c:if>
<a href="view_cart">Cart</a>
</form>
</div>

<div style="margin-top: 30px;">
	  <c:forEach var="category" items="${listCategory}" varStatus="status">
				<a href="view_category?id=${category.categoryId}">
					<font size="+1"><b><c:out value="${category.name}" /></b></font>
				</a>
				<c:if test="${not status.last}">
				&nbsp; | &nbsp;
				</c:if>
			</c:forEach>
		</div>

</div>