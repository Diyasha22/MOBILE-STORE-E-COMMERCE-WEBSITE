package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("disha@gmail.com");
		customer.setFullname("Disha Bose");
		customer.setCity("Barasat");
		customer.setCountry("India");
		customer.setAddress("West Bengal, India");
		customer.setPassword("pass");
		customer.setPhoneNo("1234567877");
		customer.setZipcode("700124");
		
		Customer savedCustomer = customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 9;
		Customer customer=customerDao.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer=customerDao.get(1);
		String fullName="Diya Bose";
		customer.setFullname(fullName);
		customer.setPassword("pass1");
		
		Customer updatedCustomer = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullName));
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 2;
		customerDao.delete(customerId);
		Customer customer=customerDao.get(customerId);
		
		assertNull(customer);
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomers= customerDao.count();
		
		assertEquals(1, totalCustomers);
	}

	@Test
	public void testFindByEmail() {
		String email="diya@yahoo.com";
		Customer customer=customerDao.findByEmail(email);
		
		assertNotNull(customer);
		
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="diya@yahoo.com";
		String password="pass1";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email="diy@gmail.com";
		String password="pass1";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNull(customer);
	}

}
