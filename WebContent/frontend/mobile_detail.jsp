<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${mobile.model} - Mobile Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp"/>
<div align="center">
		<table width="80%" style="border:0;">
			<tr>
				<td colspan="3" align="left">
					<h2 class="page-heading">${mobile.model}</h2>
				</td>
			</tr>
			<tr>
				<td rowspan="2" align="left">
					<img src="data:image/jpg;base64,${mobile.base64Image}" width="300" hieght="400" />
				</td>
				<td valign="top" align="left">
					<jsp:directive.include file="mobile_rating.jsp" /> &nbsp;&nbsp;
					<a href="#reviews">${fn:length(mobile.reviews)} Reviews</a>
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>Price: Rs.${mobile.price}</h2>
					<br/><br/>
					<button id="buttonAddToCart" >Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align:justify;">
					${mobile.description}
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			<td colspan="3" align="center">
					<button id="buttonWriteReview">Write a Customer Review</button>
				</td>
			</tr>
			<tr>
				<td><h2 class="page-heading"><a id="reviews">Customer Reviews</a></h2></td>
			</tr>
			<tr>
				<td colspan="3" align="left" style="border:0;">
					<table style="border:0;" class="normal" >
						<c:forEach items="${mobile.reviews}" var="review">
							<tr>
								<td>
									${review.customer.fullname}
								</td>
							</tr>
							<tr>
								<td>
									<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on' }">
											<img alt="*" src="images/rating_on.png" />
										</c:if>
										<c:if test="${star eq 'off' }">
											<img alt="*" src="images/rating_off.png" />
										</c:if>
									</c:forTokens>&nbsp;&nbsp;
									 <b>${review.headline}</b>
								</td>
							</tr>
							<tr><td><i>${review.comment}</i></td></tr>
							<tr>
								<td>
									Reviewed in ${review.customer.country} on ${review.reviewTime}
								</td>
							</tr>
							<tr><td><hr></td></tr>
						</c:forEach>
						
					</table>
				</td>
			</tr>
			
		</table>

	</div>
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function(){
			$("#buttonWriteReview").click(function() {
				window.location = 'write_review?mobile_id=' + ${mobile.mobileId};
		    });
			
				 $("#buttonAddToCart").click(function() {
				window.location = 'add_to_cart?mobile_id=' + ${mobile.mobileId};
		    });
		});
	</script>
</body>
</html>