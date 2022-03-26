package com.bjuan.springbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
	void saveDuplicatedEmailTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		u.setEmail("goku.egoista@gmai.com");
		userService.save(u);

		final User u2 = new User();
		id = 2;
		u2.setId(id);
		u2.setEmail("goku.egoista@gmai.com");
		assertThrows(IllegalArgumentException.class, () -> {
			userService.save(u2);
		});

		assertEquals(1, userService.findAll().size());
		
		Optional<User> retrived = userService.findById(1);
		assertTrue(retrived.isPresent());

		retrived = userService.findById(2);
		assertTrue(retrived.isEmpty());
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

	// TODO: Dont really need a save replace, as users won't be updated.
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
		assertThrows(IllegalArgumentException.class, () -> {
			userService.delete(u);
		});
	}

	@Test
	void deleteByIdTest(){
		User u = new User();
		long id = 1;
		u.setId(id);
		userService.save(u);

		userService.deleteById(id);

		Optional<User> retrived = userService.findById(id);
		assertTrue(retrived.isEmpty());
		assertEquals(0, userService.findAll().size());
	}
	@Test
	void deleteByIdTestInvalidArg(){
		User u = new User();
		long id = 1;
		u.setId(id);
		assertThrows(IllegalArgumentException.class, () -> {
			userService.deleteById(id);
		});
	}

}
