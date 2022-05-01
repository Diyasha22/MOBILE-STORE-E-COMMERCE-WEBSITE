package com.mobilestore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mobilestore.entity.Mobile;

public class ShoppingCart {

private Map<Mobile, Integer> cart= new HashMap<>();
	
	public void addItem(Mobile mobile) {
		if(cart.containsKey(mobile)) {
			cart.put(mobile, cart.get(mobile)+1);
		}else {
			cart.put(mobile, 1);
		}
	}
	
	public void removeItem(Mobile mobile) {
		cart.remove(mobile);
	}
	
	public int getTotalQuantity() {
		int total=0;
		
		Iterator <Mobile> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Mobile next=iterator.next();
			Integer quantity=cart.get(next);
			total+=quantity;
		}
		
		return total;
	}
	
	public float getTotalAmount() {
		float total=0.0f;
		
		Iterator <Mobile> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Mobile mobile=iterator.next();
			Integer quantity=cart.get(mobile);
			double subTotal=quantity * mobile.getPrice();
			total+=subTotal;
		}
		
		return total;
	}
	
	public void updateCart(int[] mobileIds,int[] quantities) {
		for(int i=0;i<mobileIds.length;i++) {
			Mobile key=new Mobile(mobileIds[i]);
			Integer value =quantities[i];
			cart.put(key, value);
		}
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public Map<Mobile, Integer> getItems(){
		return this.cart;
	}
}
