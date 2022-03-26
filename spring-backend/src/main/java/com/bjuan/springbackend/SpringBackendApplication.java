package com.bjuan.springbackend;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.ProductService;
import com.bjuan.springbackend.service.implementation.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(SpringBackendApplication.class, args);

		//Just for testing, delete later
		UserService us = c.getBean(UserService.class);
		ProductService ps = c.getBean(ProductService.class);
		User u = new User();
		u.setName("Juan");
		u.setSurname("Bustamante");
		u.setEmail("email@email.com");
		u.setPassword("ffff");
		us.save(u);

		for(int i = 0; i < 10; i++){
			Product p = new Product();
			p.setOwner(u);
			p.setName("Product #" + i+1);
			p.setDescription("#" + (i+1) + " is an awesome product!");
			ps.save(p);
		}
	}

}
