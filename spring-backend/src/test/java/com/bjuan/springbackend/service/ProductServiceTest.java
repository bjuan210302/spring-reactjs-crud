package com.bjuan.springbackend.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.service.implementation.ProductService;
import com.bjuan.springbackend.service.implementation.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

	UserService userService;
	ProductService producService;

	@Autowired
	public ProductServiceTest(ProductService producService, UserService userService){
		this.producService = producService;
		this.userService = userService;
	}
	
	@AfterEach
	void cleanState(){
		producService.deleteAll();
		userService.deleteAll();
	}

	void singleUserSetup(){
	}

	void multipleUserSetup(){
	}

	@Test
	void saveUserNotExistsTest(){
		Product p = new Product();
		p.setOwnerId(1);

		assertThrows(IllegalArgumentException.class, () -> {
			producService.save(p);
		});
	}

	@Test
	void findbyIdTest(){

	}
	
	@Test
	void deleteTest(){
		
	}

}
