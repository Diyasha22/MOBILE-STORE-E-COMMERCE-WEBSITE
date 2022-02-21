package com.mobilestore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import com.mobilestore.entity.Users;

public class UserTest {

	public static void main(String[] args) {
	
		Users user1=new Users();
		user1.setEmail("ankita35@gmail.com");
		user1.setFullName("Ankita Dey");
		user1.setPassword("3456");
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MobileStoreWebsite");
    EntityManager entityManager= entityManagerFactory.createEntityManager();
	
    entityManager.getTransaction().begin();
    entityManager.persist(user1);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
    System.out.println("A users object was persisted");
	}

}
