/**
 * 
 */
package com.mobilestore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Mobile;

/**
 * @author Diyasha
 *
 */
public class ShoppingCartTest {

public static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart=new ShoppingCart();
		
		Mobile mobile=new Mobile(11);
		mobile.setPrice(10000);
		cart.addItem(mobile);
		cart.addItem(mobile);
	}
	
	@Test
	public void testAddItem() {
		
		Map<Mobile, Integer> items = cart.getItems();
		int quantity=items.get(new Mobile(11));
		
		assertEquals(2, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Mobile(11));
		
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Mobile mobile2=new Mobile(17);
		cart.addItem(mobile2);
		
		cart.removeItem(new Mobile(17));
		
		assertNull(cart.getItems().get(mobile2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Mobile mobile3=new Mobile(16);
		cart.addItem(mobile3);
		cart.addItem(mobile3);
		cart.addItem(mobile3);
		
		assertEquals(5, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount1() {
		ShoppingCart cart=new ShoppingCart();
		assertEquals(0.0f, cart.getTotalAmount(),0.0f);
	}
	
	@Test
	public void testGetTotalAmount2() {
		assertEquals(20000.0f, cart.getTotalAmount(),0.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart cart=new ShoppingCart();
		Mobile mobile1=new Mobile(8);
		Mobile mobile2=new Mobile(11);
		
		cart.addItem(mobile1);
		cart.addItem(mobile2);
		
		int mobileIds[]= {8,11};
		int quantities[]= {3,4};
		
		cart.updateCart(mobileIds, quantities);
		
		assertEquals(7, cart.getTotalQuantity());
		
	}
}
