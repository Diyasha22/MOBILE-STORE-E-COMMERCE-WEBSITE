package com.mobilestore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.MobileDAO;
import com.mobilestore.entity.Mobile;
@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		if(cartObject==null) {
			ShoppingCart shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
			
		
//			MobileDAO mobileDAO=new MobileDAO();
//			Mobile mobile1=mobileDAO.get(11);
//			Mobile mobile2=mobileDAO.get(16);
//			Mobile mobile3=mobileDAO.get(17);
//			
//			shoppingCart.addItem(mobile1);
//			
//			shoppingCart.addItem(mobile2);
//			shoppingCart.addItem(mobile2);
//		
//			shoppingCart.addItem(mobile3);
		}
		
		
		String cartPage="frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(cartPage);
		dispatcher.forward(request, response);
	}
}