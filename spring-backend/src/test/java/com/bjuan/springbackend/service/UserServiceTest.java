package com.bjuan.springbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest{

	UserService userService;

	@Autowired
	public UserServiceTest(UserService userService){
		this.userService = userService;
	}

	@AfterEach
	void cleanState(){
		userService.deleteAll();
	}

	@Test
	void saveTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals(id, retrived.get().getId());
	}

	@Test
	void saveMultipleTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals(id, retrived.get().getId());

		id = 2;
		u = new User();
		u.setId(id);
		userService.save(u);

		retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals(id, retrived.get().getId());
		assertEquals(2, userService.findAll().size());
	}

	@Test
	void saveReplaceTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);

		assertEquals(1, userService.findAll().size());
		
		u = new User();
		u.setId(id);
		u.setName("name");
		userService.save(u);
		
		assertEquals(1, userService.findAll().size());

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals("name", retrived.get().getName());

	}
	@Test
	void findAllTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);
		assertEquals(1, userService.findAll().size());

		id = 2;
		u = new User();
		u.setId(id);
		userService.save(u);		
		assertEquals(2, userService.findAll().size());

		id = 3;
		u = new User();
		u.setId(id);
		userService.save(u);
		assertEquals(3, userService.findAll().size());

		u = new User();
		u.setId(id);
		userService.save(u);
		assertEquals(3, userService.findAll().size());

		userService.delete(u);

		assertEquals(2, userService.findAll().size());

	}
	@Test
	void deleteTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);

		userService.delete(u);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isEmpty());
		assertEquals(0, userService.findAll().size());
	}
	@Test
	void deleteTestInvalidArg(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.delete(u);
	}

}
