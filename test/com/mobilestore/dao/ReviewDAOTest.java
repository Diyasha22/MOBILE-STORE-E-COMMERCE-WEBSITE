package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Customer;
import com.mobilestore.entity.Mobile;
import com.mobilestore.entity.Review;

public class ReviewDAOTest {

private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review=new Review();
		Mobile mobile=new Mobile();
		
		mobile.setMobileId(16);
		
		Customer customer=new Customer();
		customer.setCustomerId(6);
		
		review.setMobile(mobile);
		review.setCustomer(customer);
		
		review.setHeadline("Average!");
		review.setRating(3);
		review.setComment("Quality is OK!");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId()>0);
	}
	
	@Test
	public void testGet() {
		Integer reviewId=2;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId=5;
		Review review = reviewDao.get(reviewId);
		review.setRating(2);
		
		Review updatedReview=reviewDao.update(review);
		
		assertEquals(review.getRating(), updatedReview.getRating());
		
	}
	
	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
	    for(Review review : listReview) {
	    	System.out.println(review.getReviewId()+ " "+ review.getMobile().getModel() 
	    			+" " +review.getCustomer().getFullname()+" "+review.getHeadline());
	    }
		
		assertTrue(listReview.size()>0);
	}
	
	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		assertTrue(totalReviews>0);
	}
	
	@Test
	public void testDeleteReview() {
		int reviewId =13;
		reviewDao.delete(reviewId);
		
		Review review=reviewDao.get(reviewId);
		
		assertNull(review);
	}
	
	@Test
	public void testFindByCustomerAndMobileNotFound() {
		Integer customerId = 1;
		Integer mobileId =8;
		
		Review result = reviewDao.findByCustomerAndMobile(customerId, mobileId);
		
		assertNull(result);
	}
	
	@Test
	public void testFindByCustomerAndMobileFound() {
		Integer customerId = 5;
		Integer mobileId =8;
		
		Review result = reviewDao.findByCustomerAndMobile(customerId, mobileId);
		
		assertNotNull(result);
	}
	
	@Test
	public void testListMostRecentReviews() {
		List<Review> recentReviews = reviewDao.listMostRecentReview();
		
		assertEquals(2, recentReviews.size());
	}
}
