package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Category;
import com.mobilestore.entity.Users;

public class CategoryDAOTest{
	
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		  categoryDAO= new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat=new Category("Headphones");
		Category category=categoryDAO.create(newCat);
		assertTrue(category!=null && category.getCategoryId()>0);
	}

	@Test
	public void testUpdateCategory() {	
		Category cat=new Category("Tablet");
		cat.setCategoryId(2);
		
		Category category= categoryDAO.update(cat);
		 assertEquals(category.getName(),cat.getName());
	}

	@Test
	public void testGetCategory() {
	Integer catId=2;
	Category cat=categoryDAO.get(catId);
	
	assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId=5;
		categoryDAO.delete(catId);
		Category cat=categoryDAO.get(catId);
		
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory=categoryDAO.listAll();
		listCategory.forEach(c-> System.out.println(c.getName()+", "));
		assertTrue(listCategory.size()>0);
	}

	@Test
	public void testCount() {
		long countCategory=categoryDAO.count();
		assertEquals(2, countCategory);
	}

}
