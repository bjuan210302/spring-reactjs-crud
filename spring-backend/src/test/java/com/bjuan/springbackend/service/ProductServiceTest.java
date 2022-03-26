package com.bjuan.springbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.ProductService;
import com.bjuan.springbackend.service.implementation.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

	UserService userService;
	ProductService productService;

	@Autowired
	public ProductServiceTest(ProductService producService, UserService userService){
		this.productService = producService;
		this.userService = userService;
	}
	
	// Tests aux
	User testUser;
	User testUser2;
	User testUser3;

	Product p;
	Product p2;
	Product p3;
	Product p4;

	@AfterEach
	void cleanState(){
		productService.deleteAll();
		userService.deleteAll();
	}

	void singleUserSetup(){
		testUser = new User();
		userService.save(testUser);
	}

	void userWithProductsSetup(){
		
		singleUserSetup();
		p = new Product();
		p2 = new Product();
		p3 = new Product();
		p4 = new Product();
		p.setOwner(testUser);
		p2.setOwner(testUser);
		p3.setOwner(testUser);
		p4.setOwner(testUser);
		System.out.println("oe");
		productService.save(p);
		System.out.println("oe2");
		productService.save(p2);
		System.out.println("oe3");
		productService.save(p3);
		System.out.println("oe4");
		productService.save(p4);
		System.out.println("oe5");
	}

	@Test
	void saveUserNotExistsTest(){
		Product p = new Product();
		p.setOwner(null);

		assertThrows(IllegalArgumentException.class, () -> {
			productService.save(p);
		});
	}

	@Test
	void saveUserExistsTest(){
		singleUserSetup();

		Product p = new Product();
		p.setOwner(testUser);
		productService.save(p);

		long productId = p.getId();

		assertTrue(productService.findById(productId).isPresent());
		List<Product> retrived = productService.findByOwnerId(testUser.getId());
		assertEquals(1, retrived.size());
		assertTrue(retrived.contains(p));
		// assertNotNull(testUser.getProduct(productId));
		// assertEquals(1, testUser.countProducts());
	}

	@Disabled
	@Test
	void saveReplaceTest(){
		//TODO: 
	}

	@Test
	void findByOwnerIdTest(){
		userWithProductsSetup();

		// Of another user
		testUser2 = new User();
		userService.save(testUser2);
	
		p3 = new Product();
		p4 = new Product();
		p3.setOwner(testUser2);
		p4.setOwner(testUser2);
		productService.save(p3);
		productService.save(p4);

		List<Product> retrived = productService.findByOwnerId(testUser.getId());
		// assertEquals(4, testUser.countProducts());
		assertEquals(4, retrived.size());

		retrived = productService.findByOwnerId(testUser2.getId());
		// assertEquals(2, testUser.countProducts());
		assertEquals(2, retrived.size());
		
	}

	@Test
	void findByOwnerIdOwnerNotExistsTest(){
		List<Product> retrived = productService.findByOwnerId(0);
		assertTrue(retrived.isEmpty());
	}

	@Test
	void deleteOKTest(){
		userWithProductsSetup();

		productService.delete(p3);
		
		// assertEquals(3, testUser.getProducts().size());
		assertTrue(productService.findById(p3.getId()).isEmpty());
		List<Product> retrived = productService.findByOwnerId(testUser.getId());
		assertEquals(3, retrived.size());
	}

	@Test
	void deleteInvalidProductTest(){
		userWithProductsSetup();
		assertThrows(IllegalArgumentException.class, () -> {
			p.setId(1000);
			productService.delete(p);
		});
	}

	@Test
	void deleteInvalidUserTest(){
		userWithProductsSetup();
		assertThrows(IllegalArgumentException.class, () -> {
			p.setOwner(null);
			productService.delete(p);
		});
	}

}
