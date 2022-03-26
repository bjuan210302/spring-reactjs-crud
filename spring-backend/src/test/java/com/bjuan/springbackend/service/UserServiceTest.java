package com.bjuan.springbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
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
		userService.save(u);
		long id = u.getId();

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals(id, retrived.get().getId());
	}

	@Test
	void saveDuplicatedEmailTest(){
		User u = new User();
		u.setEmail("goku.egoista@gmai.com");
		userService.save(u);
		long id = u.getId(); // Important to place getId() AFTER saving the object

		final User u2 = new User(); //final because lambda expression
		u2.setEmail("goku.egoista@gmai.com");
		assertThrows(IllegalArgumentException.class, () -> {
			userService.save(u2);
		});
		long id2 = u2.getId();

		assertEquals(1, userService.findAll().size());
		
		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());

		retrived = userService.findById(id2);
		assertTrue(retrived.isEmpty());
	}

	@Test
	void saveDuplicatedIdTest(){
		User u = new User();
		userService.save(u);
		long id = u.getId(); // Important to place getId() AFTER saving the object

		final User u2 = new User(); //final because lambda expression
		u2.setId(id);
		assertThrows(IllegalArgumentException.class, () -> {
			userService.save(u2);
		});

		assertEquals(1, userService.findAll().size());
		
		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
	}

	@Test
	void saveMultipleTest(){
		User u = new User();
		userService.save(u);
		long id = u.getId();
	
		u = new User();
		userService.save(u);
		long id2 = u.getId();

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals(id, retrived.get().getId());

		retrived = userService.findById(id2);
		assertTrue(retrived.isPresent());
		assertEquals(id2, retrived.get().getId());

		assertEquals(2, userService.findAll().size());
	}

	// TODO: Dont really need a save replace, as users won't be updated.
	@Disabled
	@Test
	void saveReplaceTest(){
		User u = new User();
		userService.save(u);
		long id = u.getId();

		assertEquals(1, userService.findAll().size());

		Optional<User> retrived = userService.findById(id);
		assertNull(retrived.get().getName()); // First user has no name
		
		u = new User();
		u.setId(id);
		u.setName("name"); //Updated one has name
		System.out.println(u.getId());
		userService.save(u);
		System.out.println(u.getId());
		
		assertEquals(1, userService.findAll().size());

		retrived = userService.findById(id);
		assertTrue(retrived.isPresent());
		assertEquals("name", retrived.get().getName());

	}

	@Test
	void findAllTest(){
		User u = new User();
		userService.save(u);
		assertEquals(1, userService.findAll().size());

		u = new User();
		userService.save(u);		
		assertEquals(2, userService.findAll().size());

		// Add update 
		// Wont need, commented out
		// u = new User();
		// userService.save(u);
		// long id = u.getId();
		// assertEquals(3, userService.findAll().size());

		// u = new User();
		// u.setId(id);
		// userService.save(u);
		// assertEquals(3, userService.findAll().size());

		userService.delete(u);

		assertEquals(1, userService.findAll().size());

	}

	@Test
	void deleteTest(){
		User u = new User();
		userService.save(u);
		long id = u.getId();

		userService.delete(u);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isEmpty());
		assertEquals(0, userService.findAll().size());
	}

	@Test
	void deleteTestInvalidArg(){
		User u = new User();
		assertThrows(IllegalArgumentException.class, () -> {
			userService.delete(u);
		});
	}

	@Test
	void deleteByIdTest(){
		User u = new User();
		userService.save(u);
		long id = u.getId();

		userService.deleteById(id);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isEmpty());
		assertEquals(0, userService.findAll().size());
	}

	@Test
	void deleteByIdTestInvalidArg(){
		assertThrows(IllegalArgumentException.class, () -> {
			userService.deleteById((long)1);
		});
	}

}
