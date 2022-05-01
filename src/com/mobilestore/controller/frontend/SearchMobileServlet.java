package com.mobilestore.controller.frontend;

import com.mobilestore.service.MobileServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchMobileServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MobileServices mobileServices=new MobileServices(request, response);
		mobileServices.search();
	}

}
