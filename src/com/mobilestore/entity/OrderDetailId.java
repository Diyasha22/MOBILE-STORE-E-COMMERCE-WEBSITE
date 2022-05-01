package com.mobilestore.entity;
// Generated 13-Jul-2021, 10:07:40 pm by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class OrderDetailId implements java.io.Serializable {

	private Mobile mobile;
	private MobileOrder mobileOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_id", insertable = false, updatable = false,  nullable=false)
	public Mobile getMobile() {
		return this.mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable=false)
	public MobileOrder getMobileOrder() {
		return this.mobileOrder;
	}

	public void setMobileOrder(MobileOrder mobileOrder) {
		this.mobileOrder = mobileOrder;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((mobileOrder == null) ? 0 : mobileOrder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailId other = (OrderDetailId) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobileOrder == null) {
			if (other.mobileOrder != null)
				return false;
		} else if (!mobileOrder.equals(other.mobileOrder))
			return false;
		return true;
	}
	
}