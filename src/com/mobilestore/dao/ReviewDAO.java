package com.mobilestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobilestore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}


	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public Review findByCustomerAndMobile(Integer customerId,Integer mobileId) {
		Map<String, Object> parameters= new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("mobileId", mobileId);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndMobile" , parameters);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Review> listMostRecentReview(){
		return super.findWithNamedQuery("Review.listAll", 0, 3);
	}

}
