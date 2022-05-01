package com.mobilestore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mobilestore.entity.Category;
import com.mobilestore.entity.Mobile;

public class MobileDAOTest{
	private static MobileDAO mobileDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		mobileDao=new MobileDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mobileDao.close();
	}

	@Test
	public void testCreateMobile() throws ParseException, IOException {
		Mobile newMobile = new Mobile();
		
		Category category=new Category("Samsung Galaxy");
		category.setCategoryId(10);
		newMobile.setCategory(category);
		
		newMobile.setModel("Samsung Galaxy M21");
		newMobile.setCompany("Samsung");
		newMobile.setDescription("Samsung Galaxy M21");
		newMobile.setPrice(20f);
		newMobile.setImei("34567890888863");
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate=  dateFormat.parse("01/05/2021");
		newMobile.setPublishDate(publishDate);
		
		String imagePath="D:\\local disc 2\\Documents\\servlet\\MobileStoreWebsite\\WebContent\\images\\samsung-galaxy-m21-3.png";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newMobile.setImage(imageBytes);
		
		Mobile createdMobile = mobileDao.create(newMobile);
		
		assertTrue(createdMobile.getMobileId() > 0);
	}

	@Test
	public void testUpdateMobile() throws ParseException, IOException {
		Mobile existMobile = new Mobile();
		existMobile.setMobileId(6);
		
		Category category=new Category("Samsung Galaxy");
		category.setCategoryId(10);
		existMobile.setCategory(category);
		
		existMobile.setModel("Samsung Galaxy M21 32GB");
		existMobile.setCompany("Samsung");
		existMobile.setDescription("Samsung Galaxy M21 32GB");
		existMobile.setPrice(15000f);
		existMobile.setImei("34567890888823");
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate=  dateFormat.parse("01/05/2021");
		existMobile.setPublishDate(publishDate);
		
		String imagePath="D:\\local disc 2\\Documents\\servlet\\MobileStoreWebsite\\WebContent\\images\\samsung-galaxy-m21-3.png";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existMobile.setImage(imageBytes);
		
		Mobile updatedMobile = mobileDao.update(existMobile);
		
		assertEquals(updatedMobile.getModel(), "Samsung Galaxy M21 32GB");
		
	}
	
	@Test(expected= EntityNotFoundException.class)
	public void testDeleteMobileFail() {
		Integer mobileId=100;
		mobileDao.delete(mobileId);
		
	}
	
	@Test
	public void testDeleteMobileSuccess() {
		Integer mobileId=7;
		mobileDao.delete(mobileId);
		assertTrue(true);
	}
	
	@Test
	public void testGetMobileFail() {
		Integer mobileId=100;
		Mobile mobile = mobileDao.get(mobileId);
		
		assertNull(mobile);
	}
	
	@Test
	public void testGetMobileSuccess() {
		Integer mobileId=1;
		Mobile mobile = mobileDao.get(mobileId);
		
		assertNotNull(mobile);
	}
	
	@Test
	public void testListAll() {
		List<Mobile> listMobiles = mobileDao.listAll();
		
		assertFalse(listMobiles.isEmpty());
	}
	
	@Test
	public void testFindByModelNotExist() {
		String model="not exist";
		Mobile mobile= mobileDao.findByModel(model);
		
		assertNull(mobile);
	}
	
	@Test
	public void testFindByModelExist() {
		String model="Samsung Galaxy M21 32GB";
		Mobile mobile=mobileDao.findByModel(model);
		
		System.out.println(mobile.getCompany());
		
		assertNotNull(mobile);
	}
	
	@Test
	public void testCount() {
		long totalMobiles= mobileDao.count();
		
		assertEquals(2, totalMobiles);
	}
	
	@Test
	public void testListByCategory() {
		int categoryId=10;
		
		List <Mobile> listMobiles=mobileDao.listByCategory(categoryId);
		
		assertTrue(listMobiles.size() > 0);
	}
	
	@Test
	public void testListNewMobiles() {
		List<Mobile> listNewMobiles = mobileDao.listNewMobiles();
		
		assertEquals(4, listNewMobiles.size());
	}
	
	
	@Test
	public void testSearchMobileInModel() {
		String keyword="Galaxy";
		List<Mobile> result = mobileDao.search(keyword);
		
		assertEquals(5, result.size());
	}
	
	@Test
	public void testSearchMobileInCompany() {
		String keyword="Apple";
		List<Mobile> result = mobileDao.search(keyword);
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchMobileInDescription() {
		String keyword="About this item";
		List<Mobile> result = mobileDao.search(keyword);
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testListBestSellingMobiles() {
		List<Mobile> topBestSellingMobiles=mobileDao.listBestSellingMobiles();
		
		for(Mobile mobile: topBestSellingMobiles ) {
			System.out.println(mobile.getModel());
		}
		
		assertEquals(4, topBestSellingMobiles.size());
	}
	
	@Test
	public void testListMostFavoredMobiles() {
		List<Mobile> topFavoredMobiles=mobileDao.listMostFavoredMobiles();
		
		for(Mobile mobile: topFavoredMobiles ) {
			System.out.println(mobile.getModel());
		}
		
		assertEquals(4, topFavoredMobiles.size());
	}
}
