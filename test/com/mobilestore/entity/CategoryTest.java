package com.mobilestore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import com.mobilestore.entity.Users;

public class CategoryTest {

	public static void main(String[] args) {
	
		Category newCat=new Category("Samsung");
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MobileStoreWebsite");
    EntityManager entityManager= entityManagerFactory.createEntityManager();
	
    entityManager.getTransaction().begin();
    entityManager.persist(newCat);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
    System.out.println("A category object was persisted");
	}

}
