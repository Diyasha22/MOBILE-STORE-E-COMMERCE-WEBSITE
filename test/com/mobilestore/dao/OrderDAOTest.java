package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Customer;
import com.mobilestore.entity.Mobile;
import com.mobilestore.entity.MobileOrder;
import com.mobilestore.entity.OrderDetail;

public class OrderDAOTest {

	private static OrderDAO orderDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@Test
	public void testCreateMobileOrder() {
		MobileOrder order=new MobileOrder();
		Customer customer=new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setReceipentName("Sunny Roy");
		order.setReceipentPhoneno("7890567845");
		order.setShippingAddress("K.S. Road, Kolkata");
		order.setPaymentMethod("Cash On Delivery");
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		OrderDetail orderDetail=new OrderDetail();
		
		Mobile mobile=new Mobile(12);
		orderDetail.setMobile(mobile);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(40.0f);
		orderDetail.setMobileOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		order.setTotal(50.0f);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId()>0);
	}
	
	@Test
	public void testCreateMobileOrder2() {
		MobileOrder order=new MobileOrder();
		Customer customer=new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setReceipentName("Mita Dey");
		order.setReceipentPhoneno("1226527637");
		order.setShippingAddress("Barasat, K.S. Road");
		order.setPaymentMethod("Cash On Delivery");
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		
		OrderDetail orderDetail1=new OrderDetail();
		
		Mobile mobile1 =new Mobile(12);
		orderDetail1.setMobile(mobile1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(100000.0f);
		orderDetail1.setMobileOrder(order);
		
		orderDetails.add(orderDetail1);
		
		OrderDetail orderDetail2=new OrderDetail();
		Mobile mobile2 =new Mobile(18);
		orderDetail2.setMobile(mobile2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(100000.0f);
		orderDetail2.setMobileOrder(order);
		orderDetails.add(orderDetail2);
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderDetails().size()>0);
	}
	@Test
	public void testUpdateMobileOrderDetail() {
		Integer orderId=25;
		MobileOrder order=orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator=order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail= iterator.next();
			if(orderDetail.getMobile().getMobileId()==12) {
				orderDetail.setQuantity(2);
				orderDetail.setSubtotal(100);
			}
		}
		
		orderDAO.update(order);
		
		MobileOrder updatedOrder= orderDAO.get(orderId);
		
		iterator=order.getOrderDetails().iterator();
		
		int expectedQuantity=2;
		float expectedSubtotal=100;
		int actualQuantity=2;
		float actualSubtotal=100;
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail= iterator.next();
			if(orderDetail.getMobile().getMobileId()==12) {
				actualQuantity=orderDetail.getQuantity();
				actualSubtotal=orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity,actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}

	
	@Test
	public void testGet() {
		Integer orderId=28;
		MobileOrder order=orderDAO.get(orderId);
		System.out.println(order.getReceipentName());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(2, order.getOrderDetails().size());
	}
//
//	@Test
//	public void testGetIntegerInteger() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testDeleteObject() {
		int orderId=26;
		orderDAO.delete(orderId);
		
		MobileOrder order=orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<MobileOrder> listOrders =orderDAO.listAll();
		assertTrue(listOrders.size()>0);
	}

	@Test
	public void testCount() {
		long totalOrders=orderDAO.count();
		
		assertEquals(4, totalOrders);
	}

	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId =1;
		List <MobileOrder> listOrders=orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId =8;
		List <MobileOrder> listOrders=orderDAO.listByCustomer(customerId);
		  
		assertTrue(listOrders.size() > 0);
	}
//	@Test
//	public void testListByCustomer() {
//		fail("Not yet implemented");
//	}
	@Test
	public void testListMostRecentSales() {
		List<MobileOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

}
