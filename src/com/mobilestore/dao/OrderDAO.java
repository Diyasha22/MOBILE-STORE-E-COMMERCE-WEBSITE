package com.mobilestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobilestore.entity.MobileOrder;

public class OrderDAO extends JpaDAO<MobileOrder> implements GenericDAO<MobileOrder> {

	@Override
	public MobileOrder create(MobileOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		return super.create(order);
	}

	@Override
	public MobileOrder update(MobileOrder order) {
		return super.update(order);
	}

	@Override
	public MobileOrder get(Object orderId) {
		return super.find(MobileOrder.class, orderId);
	}

	public MobileOrder get(Integer orderId,Integer customerId) {
		Map<String, Object> parameters=new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		
		List <MobileOrder> result=super.findWithNamedQuery("MobileOrder.findByIdAndCustomer", parameters);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(Object orderId) {
		super.delete(MobileOrder.class, orderId);
	}

	@Override
	public List<MobileOrder> listAll() {
		return super.findWithNamedQuery("MobileOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("MobileOrder.countAll");
	}

	public List<MobileOrder> listByCustomer(Integer customerId) {
		return super.findWithNamedQuery("MobileOrder.findByCustomer", 
				"customerId", customerId);
	}
	
	public List<MobileOrder> listMostRecentSales(){
		return super.findWithNamedQuery("MobileOrder.findAll", 0, 3);
	}
}
