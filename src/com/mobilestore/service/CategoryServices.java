package com.mobilestore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.CategoryDAO;
import com.mobilestore.dao.MobileDAO;
import com.mobilestore.entity.Category;
import com.mobilestore.entity.Users;

public class CategoryServices {
	 private CategoryDAO categoryDAO;
	 private HttpServletRequest request;
	 private HttpServletResponse response;
	 
	 public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		 this.request=request;
		 this.response=response;
		 categoryDAO=new CategoryDAO();
	}

	 public void listCategory() throws ServletException, IOException
	 {
		 listCategory(null);
	 }
	 public void listCategory(String message) throws ServletException, IOException
	 {
		List<Category> listCategory =categoryDAO.listAll();
		
		    request.setAttribute("listCategory", listCategory);
		    if(message!=null)
		    	request.setAttribute("message", message);
				String listPage="category_list.jsp";
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);		
				requestDispatcher.forward(request, response);
	 }

	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category existcategory=categoryDAO.findByName(name);
	    if(existcategory!=null)
	    {
	    	String message="Could Not Create New Category | A Category Named- "+name  +" Already Exists!";
	     request.setAttribute("message", message);
	     RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
	     dispatcher.forward(request, response);
	    }
	    else {
	    	Category newCategory=new Category(name);
	categoryDAO.create(newCategory);
	 listCategory("New Category Created Successfully");
	    }
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId= Integer.parseInt(request.getParameter("id"));
		Category category=categoryDAO.get(categoryId);
		request.setAttribute("category", category);
		
		String editPage ="category_form.jsp";
		RequestDispatcher requestDispatcher= request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId= Integer.parseInt(request.getParameter("categoryId"));
		String categoryName=request.getParameter("name");
		
		Category categoryById=categoryDAO.get(categoryId);
		Category categoryByName=categoryDAO.findByName(categoryName);
		
		if(categoryByName!=null && categoryById.getCategoryId()!= categoryByName.getCategoryId()) {
			String message="Could not update category -"
					+ " A category with name " + categoryName + " already exists.";
			
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message="Category has been updated successfully.";
			listCategory(message);
			
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId= Integer.parseInt(request.getParameter("id"));
		MobileDAO mobileDAO=new MobileDAO();
		long numberOfMobiles = mobileDAO.countByCategory(categoryId);
		String message;
		
		if(numberOfMobiles > 0) {
			message ="Could not delete the category (ID: %d) because it currently contains some Products.";
			message =String.format(message, numberOfMobiles);
		}else {
			categoryDAO.delete(categoryId);
			message= "The category with ID "+ categoryId + " has been removed successfully.";
		}
			
		listCategory(message);
	}
	 
}
