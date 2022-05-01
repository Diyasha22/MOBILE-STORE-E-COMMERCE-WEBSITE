package com.mobilestore.entity;
// Generated 13-Jul-2021, 10:07:40 pm by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * OrderDetail generated by hbm2java
 */
@Entity
@Table(name = "order_detail", catalog = "mobilestoredb")
@NamedQueries({
	@NamedQuery(name="OrderDetail.bestSelling",
			query="SELECT od.mobile FROM OrderDetail od GROUP by od.mobile.mobileId "
				+ "ORDER BY SUM(od.quantity) DESC")
})
public class OrderDetail implements java.io.Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private OrderDetailId id=new OrderDetailId();
	private Mobile mobile;
	private MobileOrder mobileOrder;
	private int quantity;
	private float subtotal;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Mobile mobile, MobileOrder mobileOrder,int quantity, float subtotal) {
		this.id = id;
		this.mobile = mobile;
		this.mobileOrder = mobileOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id", nullable=false)),
			@AttributeOverride(name = "mobileId", column = @Column(name = "mobile_id",  nullable=false)),
			 })
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mobile_id", insertable = false, updatable = false,  nullable=false)
	public Mobile getMobile() {
		return this.mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
		this.id.setMobile(mobile);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable=false)
	public MobileOrder getMobileOrder() {
		return this.mobileOrder;
	}

	public void setMobileOrder(MobileOrder mobileOrder) {
		this.mobileOrder = mobileOrder;
		this.id.setMobileOrder(mobileOrder);
	}
	
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
	public float getSubtotal() {
		return this.subtotal;
	}
	

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

}