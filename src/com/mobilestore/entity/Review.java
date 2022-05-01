package com.mobilestore.entity;
// Generated 13-Jul-2021, 10:07:40 pm by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.NamedQueries;

@Entity
@Table(name = "review", catalog = "mobilestoredb")
@NamedQueries({
	@NamedQuery(name= "Review.listAll", query="SELECT r FROM Review r ORDER BY r.reviewTime DESC"),
	@NamedQuery(name= "Review.countAll", query="SELECT COUNT(r) FROM Review r"),
	@NamedQuery(name= "Review.findByCustomerAndMobile",
			query= "SELECT r FROM Review r WHERE r.customer.customerId =:customerId" 
					+ " AND r.mobile.mobileId =:mobileId"),
	@NamedQuery(name="Review.mostFavoredMobiles",
			query="SELECT r.mobile, COUNT(r.mobile.mobileId) AS ReviewCount, AVG(r.rating) as AvgRating FROM Review r "
					+ "GROUP BY r.mobile.mobileId HAVING AVG(r.rating) >= 3.5 "
					+ "ORDER BY ReviewCount DESC, AvgRating DESC")
	
})
public class Review implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer reviewId;
	private Customer customer;
	private Mobile mobile;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public Review() {
	}

	public Review(Customer customer, Mobile mobile, int rating, String headline, String comment, Date reviewTime) {
		this.customer = customer;
		this.mobile = mobile;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "review_id", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mobile_id", nullable = false)
	public Mobile getMobile() {
		return this.mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	@Column(name = "rating", nullable = false)
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "headline", nullable = false, length = 45)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time", nullable = false, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	@Transient
	public String getStars() {
		String result="";
		
		int numberOfStarsOn = (int)rating;
		
		for(int i=1;i<= numberOfStarsOn;i++) {
			result+="on,";
		}
		
		for(int i=numberOfStarsOn+1;i<=5;i++) {
			result+="off,";
		}
		
		return result.substring(0, result.length()-1);
	}

}
