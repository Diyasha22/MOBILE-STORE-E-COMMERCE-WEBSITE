package com.mobilestore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mobilestore.dao.CategoryDAO;
import com.mobilestore.dao.MobileDAO;
import com.mobilestore.entity.Category;
import com.mobilestore.entity.Mobile;

public class MobileServices {

	private MobileDAO mobileDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public MobileServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		mobileDAO = new MobileDAO();
		categoryDAO = new CategoryDAO();
	}
	
	public void listMobiles() throws ServletException, IOException {
		listMobiles(null);
	}
	public void listMobiles(String message) throws ServletException, IOException {
		
		List<Mobile> listMobiles = mobileDAO.listAll();
		request.setAttribute("listMobiles", listMobiles);
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "mobile_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void showMobileNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		 request.setAttribute("listCategory", listCategory);
			
		String newPage = "product_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}

	public void createMobile() throws ServletException, IOException {
		String model= request.getParameter("model");
		
		Mobile existMobile = mobileDAO.findByModel(model);
		
		if(existMobile!=null) { 
			String message="Could not create new product - The model '" + model+ "' already exists.";
			listMobiles(message);
			return;
		}
		
		Mobile newMobile = new Mobile();
		readMobileFields(newMobile);
		
		Mobile createdMobile = mobileDAO.create(newMobile);
		
		if(createdMobile.getMobileId()>=0) {
			String message="A new Product was added successfully.";
			listMobiles(message);
		}
	}

	public void editMobile() throws ServletException, IOException {
		Integer mobileId = Integer.parseInt(request.getParameter("id"));
		Mobile mobile = mobileDAO.get(mobileId);
		List<Category> listCategory=categoryDAO.listAll();
		
		request.setAttribute("mobile", mobile);
		request.setAttribute("listCategory", listCategory);
		
		String editPage = "product_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void readMobileFields(Mobile mobile) throws ServletException, IOException {
		Integer categoryId= Integer.parseInt(request.getParameter("category"));
		String model= request.getParameter("model");
		String company= request.getParameter("company");
		String description= request.getParameter("description");
		String imei= request.getParameter("imei");
		Float price= Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Date published=null;
		try {
			published= dateFormat.parse(request.getParameter("published"));
		}catch(ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("error parsing date");
		}
		
		mobile.setModel(model);
		mobile.setCompany(company);
		mobile.setImei(imei);
		mobile.setDescription(description);
		mobile.setPublishDate(published);
		mobile.setPrice(price);
		
		Category category = categoryDAO.get(categoryId);
		mobile.setCategory(category);
		
		Part part=request.getPart("mobileImage");
		
		if(part!=null &&  part.getSize()> 0) {
			long size = part.getSize();
			byte [] imageBytes =new byte[(int) size];
			
			InputStream inputStream =part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			mobile.setImage(imageBytes);
		}
	}
	public void updateMobile() throws ServletException, IOException {
		Integer mobileId = Integer.parseInt(request.getParameter("mobileId"));
		String model= request.getParameter("model");
		
		Mobile existMobile = mobileDAO.get(mobileId);
		Mobile mobileByModel=mobileDAO.findByModel(model);
		
		if(mobileByModel!=null && !existMobile.equals(mobileByModel)) {
			String message="Could not update product - There's another product with same model!";
			listMobiles(message);
			return;
		}
		
		readMobileFields(existMobile);
		
		mobileDAO.update(existMobile);
		
		String message="The mobile has been updated successfully!";
		listMobiles(message);
	}

	public void deleteMobile() throws ServletException, IOException {
		Integer mobileId = Integer.parseInt(request.getParameter("id"));
		System.out.println(mobileId);
		mobileDAO.delete(mobileId);
		
		String message="The product has been deleted successfully!";
		listMobiles(message);
	}

	public void listMobilesByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List <Mobile> listMobiles=mobileDAO.listByCategory(categoryId);
		Category category = categoryDAO.get(categoryId);
		
		request.setAttribute("listMobiles", listMobiles);
		request.setAttribute("category", category);
		
		String listPage = "frontend/mobile_list_by_category.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void viewMobileDetail() throws ServletException, IOException {
		Integer mobileId = Integer.parseInt(request.getParameter("id"));
		Mobile mobile=mobileDAO.get(mobileId);
		
		request.setAttribute("mobile", mobile);
		
		String detailPage = "frontend/mobile_detail.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword= request.getParameter("keyword");
		List <Mobile> result=null;
		
		if(keyword.equals("")) {
			result=mobileDAO.listAll();
		}else {
			result=mobileDAO.search(keyword);
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}	
	
}
