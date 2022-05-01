<div style="display: inline-block; width:20%; margin: 20px;">
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
				 <div>
				<jsp:directive.include file="mobile_rating.jsp" />
				</div>
				<div><b>Brand: ${mobile.company}</b></div>
				<div><b>Rs.${mobile.price}</b></div>
				
		</div>