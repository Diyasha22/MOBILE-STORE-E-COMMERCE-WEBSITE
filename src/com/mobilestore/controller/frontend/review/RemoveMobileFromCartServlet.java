package com.mobilestore.controller.frontend.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.controller.frontend.shoppingcart.ShoppingCart;
import com.mobilestore.entity.Mobile;

@WebServlet("/remove_from_cart")
public class RemoveMobileFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveMobileFromCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer mobileId=Integer.parseInt(request.getParameter("mobile_id"));
		
		Object cartObject =request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart=(ShoppingCart) cartObject;  //since to delete cart must be present so direct
		
		shoppingCart.removeItem(new Mobile(mobileId));  //created a new game obj with id as we have function to delete game obj
		
		//Refresh after delte
		String cartPage=request.getContextPath().concat("/view_cart"); //carts url
		response.sendRedirect(cartPage);
	}

}