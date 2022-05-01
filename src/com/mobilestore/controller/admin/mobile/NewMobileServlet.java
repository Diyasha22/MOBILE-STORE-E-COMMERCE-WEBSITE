package com.mobilestore.controller.admin.mobile;

import com.mobilestore.service.MobileServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/new_mobile")
public class NewMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewMobileServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MobileServices mobileServices=new MobileServices(request, response);
		mobileServices.showMobileNewForm();
	}

}
