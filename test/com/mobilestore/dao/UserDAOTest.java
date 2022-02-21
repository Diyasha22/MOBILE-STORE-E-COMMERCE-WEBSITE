package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.proxy.EntityNotFoundDelegate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Users;

public class UserDAOTest {

	private static  EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass()
	{
	  entityManagerFactory = Persistence.createEntityManagerFactory("MobileStoreWebsite");
	  entityManager= entityManagerFactory.createEntityManager();
      userDAO= new UserDAO(entityManager);
	}
	
	@Test
	public void testCreateUsers() {
		Users user1=new Users();
		user1.setEmail("paras22@gmail.com");
		user1.setFullName("Parash Mandal");
		user1.setPassword("heyyyy");
    
    user1= userDAO.create(user1);
    
    assertTrue(user1.getUserId()>0);
   
    }
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		  Users user1=new Users();
		 
	      user1= userDAO.create(user1);  
	     
	}
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("sania1999@gmail.com");
		user.setFullName("Sania Dey");
		user.setPassword("pass");
		
		 user= userDAO.update(user);  
		 String actual=user.getFullName();
		 String expected="Sania Dey";
		 assertEquals(expected,actual);
		 
	}
	
	
	@Test
	public void testGetUsersFound() {
		Integer userId=1;
		Users user=userDAO.get(userId);
		if(user!=null)
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	@Test
	public void testGetUsersNotFound() {
		Integer userId=100;
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test
	public void testDeleteUsers() {
		Integer userId=1;
		userDAO.delete(userId);
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsersUsers() {
		Integer userId=100;
		userDAO.delete(userId);
	}
	@Test
	public void testListAll()
	{
		List<Users> listUsers=userDAO.listAll();
		assertTrue(listUsers.size()>0);
	}
	@Test
	public void testCount()
	{
		long totalUsers=userDAO.count();
		assertEquals(9, totalUsers);
	}
	
	@Test
	public void testFindByEmail()
	{
		String email="rahul98@gmail.com";
		Users user=userDAO.findByEmail(email);
	     assertNotNull(user);
	}
@AfterClass
public static void tearDownClass()
{
	entityManager.close();
    entityManagerFactory.close();
}

}
