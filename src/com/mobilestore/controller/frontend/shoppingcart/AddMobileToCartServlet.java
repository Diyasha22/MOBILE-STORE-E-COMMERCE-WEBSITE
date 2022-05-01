package com.mobilestore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.MobileDAO;
import com.mobilestore.entity.Mobile;

@WebServlet("/add_to_cart")
public class AddMobileToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddMobileToCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer mobileId=Integer.parseInt(request.getParameter("mobile_id"));
		
		Object cartObject =request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart=null;
		
		if(cartObject!=null && cartObject instanceof ShoppingCart) { //when cart already in session just typecast
			shoppingCart=(ShoppingCart) cartObject;
		}else {//we should create a new shopping cart
			shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
			
		}
		
		//as shown in the diagram we need to get all the details of the game so lets fetch game object by id
		MobileDAO mobileDAO=new MobileDAO();
		Mobile mobile=mobileDAO.get(mobileId);
		
		shoppingCart.addItem(mobile);
		
		//redirect to cart page
		String cartPage=request.getContextPath().concat("/view_cart"); //carts url
		response.sendRedirect(cartPage);
	}

}