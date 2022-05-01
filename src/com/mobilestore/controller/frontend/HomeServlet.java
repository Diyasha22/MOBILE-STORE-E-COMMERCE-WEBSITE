package com.mobilestore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.CategoryDAO;
import com.mobilestore.dao.MobileDAO;
import com.mobilestore.entity.Category;
import com.mobilestore.entity.Mobile;


@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HomeServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MobileDAO mobileDAO=new MobileDAO();
		
		List<Mobile> listNewMobiles=mobileDAO.listNewMobiles();
		List<Mobile> listBestSellingMobiles=mobileDAO.listBestSellingMobiles();
		List<Mobile> listMostFavoredMobiles=mobileDAO.listMostFavoredMobiles();
		
		request.setAttribute("listNewMobiles", listNewMobiles);
		request.setAttribute("listBestSellingMobiles", listBestSellingMobiles);
		request.setAttribute("listMostFavoredMobiles", listMostFavoredMobiles);

		
		String homepage="frontend/index.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

	
}
