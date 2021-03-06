package com.mobilestore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobilestore.dao.MobileDAO;
import com.mobilestore.dao.ReviewDAO;
import com.mobilestore.entity.Customer;
import com.mobilestore.entity.Mobile;
import com.mobilestore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO=new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	
	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		
		request.setAttribute("listReviews", listReviews);
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher =request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		Review review=reviewDAO.get(reviewId);
		
		request.setAttribute("review", review);
		
		String editPage = "review_form.jsp";
		RequestDispatcher dispatcher =request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("reviewId"));
		
		String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		
		Review review=reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		
		String message = "The review has been updated successfully!";
		
		listAllReview(message);
		
		
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(reviewId);
		
		String message = "The review has been deleted successfully!";
		
		listAllReview(message);
		
	}

	public void showReviewForm() throws ServletException, IOException {
		Integer mobileId=Integer.parseInt(request.getParameter("mobile_id"));
		MobileDAO mobileDAO = new MobileDAO();
		Mobile mobile=mobileDAO.get(mobileId);
		
		HttpSession session=request.getSession();
		//as even after posting review we needed details of mobile so set in session
		session.setAttribute("mobile", mobile);
		
		Customer customer=(Customer) session.getAttribute("loggedCustomer");
		
		Review existReview = reviewDAO.findByCustomerAndMobile(customer.getCustomerId(), mobileId);
		
		String targetPage = "frontend/review_form.jsp";
		
		if(existReview!=null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}
		
		RequestDispatcher dispatcher =request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws ServletException, IOException {
		Integer mobileId= Integer.parseInt(request.getParameter("mobileId"));
		Integer rating= Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setRating(rating);
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		
		Mobile mobile=new Mobile();
		mobile.setMobileId(mobileId);
		newReview.setMobile(mobile);
		
		Customer customer= (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		
		reviewDAO.create(newReview);
		
		String messagePage= "frontend/review_done.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
		
	}
}
