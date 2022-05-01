package com.mobilestore.controller.admin.mobile;

import com.mobilestore.service.MobileServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/create_mobile")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // 10 KB
		maxFileSize = 1024 * 30, // 30 KB
		maxRequestSize = 1024 * 1024 // 1 MB
)
public class CreateMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateMobileServlet() {
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MobileServices mobileServices=new MobileServices(request, response);
		mobileServices.createMobile();	
	}

}
