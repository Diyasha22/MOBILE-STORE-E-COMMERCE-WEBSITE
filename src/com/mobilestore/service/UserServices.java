package com.mobilestore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.UserDAO;
import com.mobilestore.entity.Users;

public class UserServices {
 private UserDAO userDAO;
 private HttpServletRequest request;
 private HttpServletResponse response;
 
 public UserServices(HttpServletRequest request, HttpServletResponse response) {
	 this.request=request;
	 this.response=response;
	userDAO=new UserDAO();
}
 
 public void listUser() throws ServletException, IOException
 {
	 listUser(null);
 }
 
 public void listUser(String message) throws ServletException, IOException
 {
	List<Users> listUsers =userDAO.listAll();
	
	    request.setAttribute("listUsers", listUsers);
	    if(message!=null)
	    request.setAttribute("message", message);
	    
			String listPage="user_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);		
			requestDispatcher.forward(request, response);
 }
 
 public void createUser() throws ServletException, IOException
 {
	 String email=request.getParameter("email");
	    String fullName=request.getParameter("fullname");
	    String password=request.getParameter("password");	

	    Users existuser=userDAO.findByEmail(email);
	    if(existuser!=null)
	    {
	    	String message="Could Not Create New User | A User With The Email Address - "+ email +" Already Exists!";
	     request.setAttribute("message", message);
	     RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
	     dispatcher.forward(request, response);
	    }
	    else {
	 Users newUser=new Users(email,fullName,password);
	 userDAO.create(newUser);
	 listUser("New User Created Successfully");
	    }
 }

public void editUser() throws ServletException, IOException {
	
	int userId=Integer.parseInt(request.getParameter("id"));
	Users user=userDAO.get(userId);	
	String editPage="user_form.jsp";
	request.setAttribute("user", user);
	RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
	requestDispatcher.forward(request, response);
}

public void updateUser() throws ServletException, IOException {
	int userId=Integer.parseInt(request.getParameter("userId"));
	 String email=request.getParameter("email");
	    String fullName=request.getParameter("fullname");
	    String password=request.getParameter("password");	
	    
	    Users userById=userDAO.get(userId);
	    Users userByEmail=userDAO.findByEmail(email);
	    
	    if(userByEmail!=null && userById.getUserId()!=userByEmail.getUserId())
	    {
	    	String message="Could Not Update User Detail ! "+email+" already exists!";
	    	 request.setAttribute("message", message);
	    	 RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
	    	 requestDispatcher.forward(request, response);
	    }
	    else {
	Users user=new Users(userId,email,fullName,password);
	userDAO.update(user);
	
	String message="User Detail Has Been Updated Successfully";
	listUser(message);
	    }
}

public void deleteUser() throws ServletException, IOException {
	int userId=Integer.parseInt(request.getParameter("id"));
	userDAO.delete(userId);
	String message="User with ID "+userId+" has been deleted successfully!";
	listUser(message);
}
public void login() throws ServletException, IOException {
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	boolean loginResult=userDAO.checkLogin(email, password);
	if(loginResult)
	{
		request.getSession().setAttribute("useremail", email);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
		dispatcher.forward(request, response);
	}
	else
	{
		String message="Login failed";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
}
}
