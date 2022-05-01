package com.mobilestore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;

import com.mobilestore.entity.Mobile;

public class MobileDAO extends JpaDAO<Mobile> implements GenericDAO<Mobile> {

	public MobileDAO() {
	}

	@Override
	public Mobile create(Mobile mobile) {
		mobile.setLastUpdateTime(new Date());
		return super.create(mobile);
	}

	@Override
	public Mobile update(Mobile mobile) {
		mobile.setLastUpdateTime(new Date());
		return super.update(mobile);
	}

	@Override
	public Mobile get(Object mobileId) {
		return super.find(Mobile.class, mobileId);
	}

	@Override
	public void delete(Class<Mobile> type, Object id) {
		super.delete(type, id);
	}

	@Override
	public List<Mobile> listAll() {
		return super.findWithNamedQuery("Mobile.findAll");
	}
	public Mobile findByModel(String model) {
		List<Mobile> result = super.findWithNamedQuery("Mobile.findByModel", "model", model);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Mobile> listByCategory(int categoryId){
		return super.findWithNamedQuery("Mobile.findByCategory", "catId", categoryId);
	}
	
	public List<Mobile> search(String keyword){
		return super.findWithNamedQuery("Mobile.search", "keyword", keyword);
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Mobile.countAll");
	}

	@Override
	public void delete(Object mobileId) {
		super.delete(Mobile.class, mobileId);
	}
	
	public List<Mobile> listNewMobiles(){
		return super.findWithNamedQuery("Mobile.listNew", 0, 4);
	}
	
	public long countByCategory(int categoryId) {
		return 0;
//		return super.countWithNamedQuery("Mobile.countByCategory", "catId", categoryId);
	}
	public List<Mobile> listBestSellingMobiles(){
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
	}
	
	public List<Mobile> listMostFavoredMobiles(){
		List<Mobile> mostFavoredMobiles=new ArrayList<>();
		
		List<Object[]> result = super.findWithNamedQueryObjects("Review.mostFavoredMobiles", 0, 4);
		
		if(!result.isEmpty()) {
			for(Object[] elements: result) {
				Mobile mobile= (Mobile) elements[0];
				mostFavoredMobiles.add(mobile);
			}
		}
		
		return mostFavoredMobiles;
	}

	

}
