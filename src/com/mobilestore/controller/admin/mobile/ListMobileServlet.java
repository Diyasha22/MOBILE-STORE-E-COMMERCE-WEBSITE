package com.mobilestore.controller.admin.mobile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.service.MobileServices;


@WebServlet("/admin/list_mobiles")
public class ListMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MobileServices mobileServices=new MobileServices(request, response);
		mobileServices.listMobiles();
	}

}
