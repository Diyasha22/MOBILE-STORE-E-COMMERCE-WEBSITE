package com.mobilestore.controller.frontend;

import com.mobilestore.service.MobileServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view_mobile")
public class ViewMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewMobileServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MobileServices mobileServices=new MobileServices(request, response);
		mobileServices.viewMobileDetail();
	}

}
